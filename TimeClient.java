package socket;
import java.io.*;
import java.net.*;

public class TimeClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                out.println("time");
                String response = in.readLine();
                System.out.println("Server time: " + response);

                Thread.sleep(1000); 
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}