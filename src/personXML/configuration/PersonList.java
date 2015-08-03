package personXML.configuration;
import myFiler.MyFiler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Полина on 27.07.2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "personList")
public class PersonList {

    @XmlElement(name = "person", type = Person.class)
    List<Person> listPerson = new ArrayList<Person>();

    public PersonList(){

    }

    public PersonList(List<Person> listPerson){
        this.listPerson = listPerson;
    }

    public List<Person> getListPerson() {
        return listPerson;
    }

    public void setListPerson(List<Person> listPerson) {
        this.listPerson = listPerson;
    }

    public static final String configurationFileName= MyFiler.getCurrentDirectory()+File.separator+"configuration"+File.separator+
            "persons_info.xml";

    public static void marshal(List<Person> personlist, File selectedFile) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(PersonList.class);
        BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
        Marshaller marshaler = context.createMarshaller();
        marshaler.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaler.marshal(new PersonList(personlist), writer);
        writer.close();
    }

    public static List<Person> unmarshal(File importFile) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(PersonList.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        PersonList personList = (PersonList)unmarshaller.unmarshal(importFile);
        return personList.getListPerson();
    }
    public static String getConfigurationFileName() {
        return configurationFileName;
    }
}
