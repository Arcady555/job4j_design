package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

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
    private String[] works;

    public Subject() { }

    public Subject(boolean orgOrNot, String name, int est, Contact contact, String... works) {
        this.orgOrNot = orgOrNot;
        this.name = name;
        this.est = est;
        this.contact = contact;
        this.works = works;
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

        final Subject subject = new Subject(false, "Arcady", 1972,
                new Contact("11-111"), "Flash", "Run");

        JAXBContext context = JAXBContext.newInstance(Subject.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String firstRsl = "";

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(subject, writer);
            firstRsl = writer.getBuffer().toString();
            System.out.println(firstRsl);
        } catch (Exception e) { }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(firstRsl)) {
            Subject secondRsl = (Subject) unmarshaller.unmarshal(reader);
            System.out.println(secondRsl);
        }
    }
}