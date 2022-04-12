package ru.job4j.serialization.xml;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "subject")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subject {

    @XmlAttribute
    private boolean orgOrNot;

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int est;
    private Contact contact;

    @XmlElementWrapper(name = "works")
    @XmlElement(name = "work")
    private String[] works;

    public Subject() { }

    public Subject(boolean orgOrNot, String name, int est, Contact contact, String... works) {
        this.orgOrNot = orgOrNot;
        this.name = name;
        this.est = est;
        this.contact = contact;
        this.works = works;
    }

    public boolean isOrgOrNot() {
        return orgOrNot;
    }

    public String getName() {
        return name;
    }

    public int getEst() {
        return est;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getWorks() {
        return works;
    }

    @Override
    public String toString() {
        return "Subject{"
                + "orgOrNot=" + orgOrNot
                + ", name='" + name + '\''
                + ", est=" + est
                + ", contact=" + contact
                + ", works=" + Arrays.toString(works)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        JSONObject jsonContact = new JSONObject("{\"phone\":\"11-111\"}");

        List<String> list = new ArrayList<>();
        list.add("Flash");
        list.add("Run");
        JSONArray jsonWorks = new JSONArray(list);

        final Subject subject = new Subject(true, "ArtFederation",
                2000, new Contact("+7(924)222-111-11-11"), "Rainbow", "Fire on the table", "Cry");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orgOrNot", subject.isOrgOrNot());
        jsonObject.put("name", subject.getName());
        jsonObject.put("est", subject.getEst());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("works", jsonWorks);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(subject));
    }
}