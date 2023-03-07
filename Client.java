import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Calc implements ChekType {

    public Client(int type) {
        super(type);
    }
    public void start(){
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket("localhost", 1234)) {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            if (chekType()) {
                while (true) {
                    System.out.println("Введите пример через пробел: ");
                    String request = scanner.nextLine();
                    dataOutputStream.writeUTF(request);
                    if (request.equals("end")) break;
                    System.out.println("Получили от сервера: " + dataInputStream.readUTF());
                }
            }else{
                System.out.println("Некорректный тип!");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean chekType() {
        if (type == 1) {
            return true;
        }else{
            return false;
        }
    }
    
}
