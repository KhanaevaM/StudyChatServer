package ru.levelp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static ru.levelp.ServerExample.manager;

public class ClientHandler extends Thread {
    private Socket socket;
    private SenderWorker senderWorker;
    private ServerExample server;

    public ClientHandler(ServerExample server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {

        Gson gson = new GsonBuilder()
                //.setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        try {




            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            senderWorker = new SenderWorker(writer);
            senderWorker.start();

            String namex = reader.readLine();
            User u = new User(namex, "sd","rfgfd");
            manager.addUser(u);


            String inputMessage;
            while ((inputMessage = reader.readLine()) != null) {
               // System.out.println(inputMessage);

                //System.out.println(inputMessage);
                Message message = gson.fromJson(inputMessage, Message.class);

                if (message.getReceiver().equals("all")) {
                    server.sendToAll(inputMessage, this);
                }else if (message.getReceiver().equals("server")){

                }
                else {
                    //server.sendPrivate(inputMessage, User.name.);
                }

                writer.flush();
            }
            System.out.println("no messages");
            server.disconnectClient(this);
            senderWorker.stopWorker();
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        if (senderWorker != null) {
            try{
              //  System.out.println("message "+ message.toString());
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.println(message);
                writer.flush();
            //senderWorker.addMessage(message);
              //  System.out.println("adde message "+ message.toString());
        }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}