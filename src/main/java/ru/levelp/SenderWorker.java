package ru.levelp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class SenderWorker extends Thread {
    private PriorityQueue<String> queue;
    private PrintWriter writer;
    private boolean alive;



    public SenderWorker(PrintWriter writer) {
        this.writer = writer;
        queue = new PriorityQueue<String>();
    }

    @Override
    public void run() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        alive = true;
        while (alive) {
            if (!queue.isEmpty()) {
                String message = queue.poll();
                writer.println(message.toString());
                writer.flush();
            } else {
                Thread.yield();
            }
        }
//
    }

    public void addMessage(String message) {

        queue.add(message);

    }

    public void stopWorker() {

        alive = false;
    }
}