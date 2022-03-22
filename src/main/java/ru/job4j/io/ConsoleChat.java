package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    List<String> log = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> listBot = readPhrases();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        boolean botCanAnswer = true;
        while (scanner.hasNext()) {
            String userAsk = scanner.nextLine();
            String phrase = listBot.get(random.nextInt(listBot.size()));
            if (userAsk.equals(OUT)) {
                log.add(userAsk);
                saveLog(log);
                System.out.println("Our chat is over. Bye!");
                return;
            } else if (userAsk.equals(STOP)) {
                log.add(userAsk);
                botCanAnswer = false;
            } else if (userAsk.equals(CONTINUE)) {
                log.add(userAsk);
                botCanAnswer = true;
            }
            if (botCanAnswer) {
                log.add(userAsk);
                System.out.println(phrase);
                log.add(phrase);
            }
        }
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().map(s -> s + System.lineSeparator()).forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            pw.println(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./consoleTo.txt", "./consoleOut.txt");
        cc.run();
    }
}
