import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Сервер запущен, ожидаем соединение....");
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился к серверу!");
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            double res = 0;
            
            while (true) {
                String[] clientRequest = dataInputStream.readUTF().split(" ");
                if (clientRequest.equals("end")) break;
                System.out.println("Мы получили строку: " + Arrays.toString(clientRequest));
                double first = Double.parseDouble(clientRequest[0]);
                double second = Double.parseDouble(clientRequest[2]);
                switch (clientRequest[1]) {
                    case "+":
                        res = first + second;
                        break;
                    case "-":
                        res = first - second;
                        break;
                    case "*":
                        res = first * second;
                        break;
                    case "/":
                        res = first / second;
                        break;
                }
                dataOutputStream.writeUTF("Результат: " + res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
