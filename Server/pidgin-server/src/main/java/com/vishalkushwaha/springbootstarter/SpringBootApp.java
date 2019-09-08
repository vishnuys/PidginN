package com.vishalkushwaha.springbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
public class SpringBootApp {

	private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SpringApplication.run(SpringBootApp.class, args);
		
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        System.out.println("Waiting for the client request");
        //creating socket and waiting for client connection
        Socket socket = server.accept();
        //read from socket to ObjectInputStream object
        DataInputStream ois = new DataInputStream(socket.getInputStream());
        //convert ObjectInputStream object to String
        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        while(true){
            //convert ObjectInputStream object to String
            String message = ois.readUTF();
            System.out.println("Message Received: " + message);
            //create ObjectOutputStream object
            //write object to Socket
            oos.writeUTF("Hi Client "+message);
            //close resources
            //terminate the server if client sends exit request
            if(message.equalsIgnoreCase("Over")) break;
        }
        ois.close();
        oos.close();
        socket.close();
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }

}
