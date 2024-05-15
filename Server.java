package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket= new ServerSocket(12345);
			// Mở cổng kết nối cho client
			System.out.println("Server is running and waiting for client...");
			while(true) {// tạo vòng lặp nếu đúng thì chấp nhận kết nối
				Socket socket = serverSocket.accept();
				//chấp nhận client
				System.out.println("Client conected");
				//Luồng đọc dữ liệu từ cient
				Thread readThread = new Thread(() ->{
					try {
						BufferedReader reader = new BufferedReader
								( new InputStreamReader(socket.getInputStream()));
						String message;
						while((message = reader.readLine())!=null) {
							System.out.println("Client"+message);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				});
				readThread.start();
				// Luồng ghi dữ liệu
				PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
				BufferedReader consoleReader = new BufferedReader
						(new InputStreamReader(System.in));
				String userInput;
				while((userInput= consoleReader.readLine())!=null) {
					writer.println(userInput);//Gửi tới client
				}
				
		//đóng kết nối
				socket.close();
				serverSocket.close();
			}
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
	
