package util;

import java.io.*;
import java.util.Arrays;

public class Process implements Serializable {

    // Проверка и регистрацыя нового клиента
    public String registration(Contact firstUser, String password, String password2) throws Exception {
        Contact[] contacts = new Contact[1];
        String check = null;
        int email = 1;
        File file = new File("D:\\addressbook/clients.dat");
        if (file.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            try {
                Contact[] f = (Contact[]) ois.readObject();
                for (int i = 0; i < f.length; i++) {
                    if (f[i].getEmail().equals(firstUser.getEmail())) {
                        email++;
                    }
                }
                if (email == 2) {
                    return check = "1";
                } else {
                    if (password.equals(password2)) {
                        Contact[] v = new Contact[f.length + 1];
                        for (int i = 0; i < f.length; i++) {
                            v[i] = f[i];
                        }
                        f = v;
                        f[f.length - 1] = firstUser;
                        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                            oos.writeObject(f);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return check = "2";
                    } else {
                        return check = "3";
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
        } else {
            if (password.equals(password2)) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                    oos.writeObject(contacts);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                try {
                    Contact[] f1 = (Contact[]) ois.readObject();
                    f1[0] = firstUser;
                    try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                        oos.writeObject(f1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                ois.close();
                return check = "2";
            } else {
                return check = "3";
            }
        }
        return check;
    }

    // Добавить юзера или создать масив и добавить юзера
    public Contact[] addUser(Contact contact, Contact[] contmas, String filename) throws Exception {
        File file = new File("D:\\addressbook/" + filename + ".dat");
        if (file.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            try {
                Contact[] f = (Contact[]) ois.readObject();
                Contact[] v = new Contact[f.length + 1];
                for (int i = 0; i < f.length; i++) {
                    v[i] = f[i];
                }
                f = v;
                f[f.length - 1] = contact;
                try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                    oos.writeObject(f);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ois.close();
        } else {
            try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                oos.writeObject(contmas);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            try {
                Contact[] f1 = (Contact[]) ois.readObject();
                f1[0] = contact;
                try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                    oos.writeObject(f1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ois.close();
        }
        return null;
    }

    // Редактируем
    public String edit(Contact redaktUs, int index, String filename) throws Exception {
        File file = new File("D:\\addressbook/" + filename + ".dat");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        try {
            Contact[] f = (Contact[]) ois.readObject();
            f[index] = redaktUs;
            try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                oos.writeObject(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ois.close();
        return null;
    }

    // Удалить юзера
    public void remove(int index, String filename) throws Exception {
        File file = new File("D:\\addressbook/" + filename + ".dat");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        try {
            Contact[] f = (Contact[]) ois.readObject();
            Contact[] fnew = new Contact[f.length - 1];
            int a = 0;
            for (int j = 0; j < fnew.length; j++) {
                if (index == j) {
                    fnew[j] = f[j + 1];
                    a = 1;
                } else {
                    fnew[j] = f[j + a];
                }
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                oos.writeObject(fnew);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ois.close();
    }

    //  Масив контактов клиента
    public Contact[] viweAll(String filename) throws Exception {
        File file = new File("D:\\addressbook/" + filename + ".dat");
        if (file.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            try {
                Contact[] f = (Contact[]) ois.readObject();
                return f;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ois.close();
        } else {
            Contact[] f1 = new Contact[0];
            return f1;
        }
        return null;
    }


    // Сортировка по фамилии
    public String[] sortedLastName(String filename) throws Exception {
        File file = new File("D:\\addressbook/" + filename + ".dat");
        if (file.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            try {
                Contact[] f = (Contact[]) ois.readObject();
                String[] a = new String[f.length];
                for (int i = 0; i < f.length; i++) {
                    f[i].getLastName();
                    a[i] = f[i].getLastName();
                }
                Arrays.sort(a);
                return a;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ois.close();
        } else {
        }
        return null;
    }

    // Сортировка по имени
    public String[] sortedFirstName(String filename) throws Exception {
        File file = new File("D:\\addressbook/" + filename + ".dat");
        if (file.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            try {
                Contact[] f = (Contact[]) ois.readObject();
                String[] a = new String[f.length];
                for (int i = 0; i < f.length; i++) {
                    f[i].getFirstName();
                    a[i] = f[i].getFirstName();
                }
                Arrays.sort(a);
                return a;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ois.close();
        } else {
        }
        return null;
    }
}
