package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Subject {
    private final boolean orgOrNot;
    private final String name;
    private final int est;
    private final Contact contact;
    private final String[] work;

    public Subject(boolean orgOrNot, String name, int est, Contact contact, String[] work) {
        this.orgOrNot = orgOrNot;
        this.name = name;
        this.est = est;
        this.contact = contact;
        this.work = work;
    }

    @Override
    public String toString() {
        return "Subject{"
                + "orgOrNot=" + orgOrNot
                + ", name='" + name + '\''
                + ", est=" + est
                + ", contact=" + contact
                + ", work=" + Arrays.toString(work)
                + '}';
    }

    public static void main(String[] args) {
        final Subject subject = new Subject(false, "Arcady", 1972, new Contact("11-111"),
                new String[]{"Flash", "Run"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(subject));

        final String subjectJson =
                "{"
                        + "\"orgOrNot\":true,"
                        + "\"name\":ArtFederation,"
                        + "\"est\":2000,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)222-111-11-11\""
                        + "},"
                        + "\"work\":"
                        + "[\"Rainbow\",\"Fire on the table\",\"Cry\"]"
                        + "}";
        final Subject subjectMod = gson.fromJson(subjectJson, Subject.class);
        System.out.println(subjectMod);
    }
}
