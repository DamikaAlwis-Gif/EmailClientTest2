package com.company;

import java.io.*;
import java.util.ArrayList;

public abstract class FileManager {
    // write content to a file
    public static void writeToFile(String fileName, String message) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        }
        catch (IOException e){
            throw new RuntimeException();
        }
        try (FileWriter fw = new FileWriter(fileName, true);
             BufferedWriter writer = new BufferedWriter(fw)) {
            writer.write(message);
            writer.newLine();
        }
        catch (IOException e) {
            throw new RuntimeException(e);}
    }
    // read from a file
    public static ArrayList<String> readFile(String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        }
        catch (IOException e){
            throw new RuntimeException();
        }
        try (FileReader reader1 = new FileReader(file);
             BufferedReader reader = new BufferedReader(reader1)) {
            String line;
            ArrayList<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null)
                list.add(line);
            return list;
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);}
    }
    // serialize a file
    public static void serializeFile(File file ,String emailFile, Email email) {
        try {
            FileOutputStream outputStream = new FileOutputStream(emailFile, true);
            if (file.length() == 0) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(email);
                objectOutputStream.close();
            }
            else {
                ObjOutputStream oos ;
                oos = new ObjOutputStream(outputStream);
                oos.writeObject(email);
                oos.close();
            }
        }
        catch (IOException e) {
            throw new RuntimeException();
        }
    }
    // deseralize a file
    public static ArrayList<Email> deserializeFile(String emailFile) {
        File file = new File(emailFile);
        ArrayList<Email> emailList = new ArrayList<>();
        try {
            if (file.length() != 0) {
                FileInputStream inputStream = new FileInputStream(emailFile);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Email mail;
                while (inputStream.available() != 0) {
                    mail = (Email) objectInputStream.readObject();
                    emailList.add(mail);
                }
                objectInputStream.close();
                inputStream.close();
            }
        }
        catch (IOException | ClassNotFoundException e){
            throw new RuntimeException();
        }
        return emailList;
    }
}
