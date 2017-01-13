package util;

import java.io.*;

public class Authentication {

    public boolean login(String login, String password) throws Exception {
        File file = new File("D:\\addressbook/clients.dat");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        try {
            Contact[] f = (Contact[]) ois.readObject();
            for (int i = 0; i < f.length; i++) {
                if (f[i].getPassword().equals(password) && f[i].getEmail().equals(login)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ois.close();
        return false;
    }
}
