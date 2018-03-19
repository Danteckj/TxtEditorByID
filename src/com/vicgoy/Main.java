package com.vicgoy;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static HashMap<String, String> idNames;
    static File file;


    public static void main(String[] args) {

        String whyle = " ";

        while (!whyle.equals("E")) {
            System.out.println("Введите что надо сделать: O - открыть файл, E - редактировать файл, S - Сохранить в файл, A - закончить работу, L - показать записи из файла ");
            Scanner scanner = new Scanner(System.in);
            String temp = scanner.nextLine();

            switch (temp.toUpperCase()) {
                case "O": {
                    file = openFile();
                    idNames = readFile(file);
                    for (Map.Entry<String, String> entry : idNames.entrySet()) {
                        System.out.println(entry.getKey() + " " + entry.getValue());
                    }
                    break;
                }

                case "E": {
                    try {
                        if (file == null || idNames == null) break;
                        else idNames = editFile(idNames);
                    } catch (NullPointerException e) {
                    }
                    for (Map.Entry<String, String> entry : idNames.entrySet()) {
                        System.out.println(entry.getKey() + " " + entry.getValue());
                    }

                    break;
                }
                case "S": {
                    closeFile(file, idNames);


                    break;
                }


                case "L": {
                    FileReader fileReader;
                    try {
                        fileReader = new FileReader(file);
                        BufferedReader reader = new BufferedReader(fileReader);
                        String line = reader.readLine();
                        while (line != null) {
                            System.out.println(line);
                            line = reader.readLine();
                        }
                    } catch (IOException e) {
                    } catch (NullPointerException e) {
                    }
                    break;


                }
                case "A": {
                    whyle = "E";
                    break;
                }

                case "P": {
                    for (Map.Entry<String, String> entry : idNames.entrySet()) {
                        System.out.println(entry.getKey().toString() + " " + entry.getValue().toString());
                    }
                }
                default:
                    System.out.println("Введите команду");
            }
        }

    }


    public static File openFile() {
        System.out.println("Укажите путь до файла");
        Scanner scanner = new Scanner(System.in);
        String fileDestination = scanner.nextLine();
        File editngFile = new File(fileDestination);
        return editngFile;

    }


    public static HashMap<String, String> readFile(File editngFile) {

        HashMap<String, String> idFio = new HashMap<>();
        try {
            FileReader fileReader = new FileReader(editngFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null) {
                String[] temp = line.split(" ", 2);
                idFio.put(temp[0], temp[1]);
                line = reader.readLine();
            }
            fileReader.close();
            reader.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
        }

        return idFio;
    }


    public static HashMap<String, String> editFile(HashMap<String, String> map) {
        System.out.println("Введите id");
        Scanner id = new Scanner(System.in);
        String ids = id.nextLine();
        String nameAndSecondName = map.get(ids);
        String[] temp = nameAndSecondName.split(" ", 2);
        String name = temp[0];
        String secondName = temp[1];
        String whyle = " ";
        while (!whyle.equals("E")) {
            System.out.println("N - изменить имя, S - изменить Фамилию, E - закончить работу");
            Scanner scanner = new Scanner(System.in);
            String enter = scanner.nextLine();
            switch (enter.toUpperCase()) {
                case "N": {
                    System.out.println("Введите новое имя");
                    Scanner nameEnter = new Scanner(System.in);
                    name = nameEnter.nextLine();
                    break;
                }
                case "S": {
                    System.out.println("Введите новою Фамилию");
                    Scanner snameEnter = new Scanner(System.in);
                    secondName = snameEnter.nextLine();
                    break;
                }
                case "E":
                    whyle = "E";
                default:
                    System.out.println("Введите команду");
            }
        }
        nameAndSecondName = name + " " + secondName;
        System.out.println("Имя и фамиля изменины на: " + nameAndSecondName);
        map.replace(ids, nameAndSecondName);
        return map;
    }


    public static void closeFile(File file, HashMap<String, String> hashMap) {
        try {
            FileWriter writer = new FileWriter(file, true);
            String lineSeparator = System.getProperty("line.separator");
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                writer.write(lineSeparator + entry.getKey() + " " + entry.getValue());
                writer.flush();
            }
            writer.close();

        } catch (NullPointerException e) {
        } catch (IOException e) {
        }
    }
}
