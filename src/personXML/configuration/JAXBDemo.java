package personXML.configuration;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Мила on 27.07.2015.
 */
public class JAXBDemo {
    public static void main(String[] args) {

        List<Person> list = new ArrayList<Person>();
/*
        list.add("122");
        list.add("123");
*/
        //Marshalling: Writing Java objects to XMl file
/*
        try {
            CompanyListJAXBHandler.marshal(list, new File(HorseList.configurationFileName));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
*/

        //Unmarshalling: Converting XML content to Java objects
        try {
            list = PersonList.unmarshal(new File(PersonList.configurationFileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println(list);
    }
}
