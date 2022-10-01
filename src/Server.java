import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        // использую класс ServerSocket тк он используется сервером для объявления  объектов, которые нужны серверу
        // исп класс Socket тк он тоже используется сервером для отправки и получ данных от клиентов
        //далее объявляю контстанты
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        // далее объявила объект in класса BufferReader Он используется для чтения данных из объекта clientSocket.
        BufferedReader in = null;
        // объект out класса PrintWrite нужен чтобы записывать данные в объект clientSocket
        PrintWriter out = null;
        new Scanner(System.in);
        try {
            serverSocket = new ServerSocket(5000);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  в этой части буду считывать данные от пользователя и отправлять эти данные клиенту
        BufferedReader finalIn = in;
        PrintWriter finalOut = out;
        Socket finalClientSocket = clientSocket;
        ServerSocket finalServerSocket = serverSocket;
        new Thread(new Runnable() {
            String msg; //переменная, которая будет содержать данные, записываемые пользователем

            @Override
            public void run() {
                try {
                    msg = finalIn.readLine(); //считывание данных из clientSocket используя  объект in
                    while (msg != null) {
                        System.out.println("Client : " + msg);
                        msg = finalIn.readLine();
                    }
                    //если msg==null, значить клиент боольше не в сети
                    System.out.println("Offline");
                    //закрываю передачу данных
                    finalOut.close();
                    finalClientSocket.close();
                    finalServerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        ProcessBuilder recieve = null;
        assert false;
        recieve.start();
    }
}
    

