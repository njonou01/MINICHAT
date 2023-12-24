package business.sender;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import business.general.AdressBook;
import business.general.Message;

class Sender {
    String senderName;
    AdressBook book;
    Message msg;
    final int PORT = 2023;

    public Sender(String senderName) {
        this.book = new AdressBook("../../contact/IP.txt");
        this.senderName = senderName;
        this.msg = new Message(senderName);
    }

    public void start() {
        BufferedReader standartInBuffer = new BufferedReader(new InputStreamReader(System.in));
        String bufferLine;
        try {
            while ((bufferLine = standartInBuffer.readLine()).equalsIgnoreCase("exit")) {
                msg.setBody(bufferLine);
                for (String contact : book.getAllContacts()) {
                    sendMessageTo(contact, msg);
                }
            }
        } catch (IOException e) {
            System.out.println("Error in sending message");

        }

    }

    private void sendMessageTo(String contact, Message message) {
        try {
            Socket soc = new Socket(contact, PORT);

            OutputStream output = soc.getOutputStream();
            BufferedWriter buffer_writer = new BufferedWriter(new OutputStreamWriter(output));

            InputStream input = soc.getInputStream();
            BufferedReader buffer_reader = new BufferedReader(new InputStreamReader(input));

            buffer_writer.write(message.getSender());
            buffer_writer.newLine();
            buffer_writer.flush();

            buffer_writer.write(message.getDate());
            buffer_writer.newLine();
            buffer_writer.flush();

            buffer_writer.write(message.length());
            buffer_writer.newLine();
            buffer_writer.flush();

            buffer_writer.write(message.getBody());
            buffer_writer.newLine();
            buffer_writer.flush();

            

            input.close();
            buffer_reader.close();
            buffer_writer.close();
            output.close();
            soc.close();
        } catch (java.net.ConnectException e) {
            System.out.println("Connection refused , this server is not launched");
        } catch (java.net.SocketException e) {
            System.out.println("Server is turned off, make sure to turn it back on");
        } catch (IOException e) {
            System.out.println("Error in sending message");
        }
    }
}