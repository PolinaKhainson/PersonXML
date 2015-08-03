package personXML.components.table;

import personXML.Terminal;
import personXML.components.Footer;
import personXML.components.Header;
import personXML.components.MainScreen;
import personXML.configuration.*;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class GridTable {
    public static TableView<Person> tableViewHorses;

    private HBox container;
    HBox separator,separator1;
    ObservableList<Person> listPersons;
    public static List<Person> persons = new ArrayList<Person>();


    public GridTable() {

      container=new HBox();

      container.setMaxSize(MainScreen.width, MainScreen.height-Header.heightHeader-Footer.heightFooter-150);
      container.setMinSize(MainScreen.width, MainScreen.height-Header.heightHeader-Footer.heightFooter-150);
      container.setAlignment(Pos.TOP_LEFT);

      separator=new HBox();separator.setMinSize(MainScreen.width,25);
      separator1=new HBox();separator1.setMinSize(MainScreen.width,30);


      final VBox part1 = new VBox();
      part1.setMaxSize(MainScreen.width,MainScreen.height-Header.heightHeader-Footer.heightFooter-150);
      part1.setMinSize(MainScreen.width, MainScreen.height - Header.heightHeader - Footer.heightFooter-150);
       try {
            persons = PersonList.unmarshal(new File(PersonList.configurationFileName));
       }catch(Throwable ex){
            System.out.println(ex.getMessage());
            Terminal.getMainScreen().showTerminalError("Can't read dataBase!");
        }
         listPersons = FXCollections.observableArrayList();
        if(persons!=null) {
            for (Person person : persons) {
                listPersons.add(person);
            }
        }

        buildTablePerson(part1);
        container.getChildren().add(part1);
    }

   private void buildTablePerson(VBox partDisplay){

       partDisplay.getChildren().clear();

       partDisplay.getChildren().add(separator);
       Label name = new Label("Persons");
       name.setStyle("-fx-background-color: brown;-fx-font-size: 25");
       partDisplay.getChildren().add(name);
       partDisplay.setAlignment(Pos.CENTER_LEFT);

       tableViewHorses = new TableView<Person>(listPersons);
       tableViewHorses.setTableMenuButtonVisible(true);
       tableViewHorses.setCursor(Cursor.TEXT);
       tableViewHorses.setPrefHeight(MainScreen.height- Header.heightHeader- Footer.heightFooter-150);
       tableViewHorses.setMaxHeight(MainScreen.height - Header.heightHeader - Footer.heightFooter - 150);;
       tableViewHorses.setPrefWidth(MainScreen.width);
       tableViewHorses.setMaxWidth(MainScreen.width);
       tableViewHorses.setTooltip(new Tooltip("persons table"));
       tableViewHorses.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
       tableViewHorses.setStyle("-fx-background-color: yellow; -fx-padding: 10; -fx-font-size: 16;-fx-font-weight: bold;");
       tableViewHorses.setEditable(true);

       TableColumn nameColumn1 = new TableColumn("FirstName");
       nameColumn1.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));

       nameColumn1.setSortable(true);
       nameColumn1.setPrefWidth(160);
       nameColumn1.setResizable(false);
       nameColumn1.setEditable(true);

       TableColumn nameColumn2=new TableColumn("LastName");
       nameColumn2.setCellValueFactory(new PropertyValueFactory<Person,String>("lastName"));
       nameColumn2.setSortable(true);
       nameColumn2.setPrefWidth(MainScreen.width);
       nameColumn2.setResizable(false);
       nameColumn2.setEditable(true);



       tableViewHorses.setItems(listPersons);
       tableViewHorses.getColumns().addAll(nameColumn1,nameColumn2);

       partDisplay.getChildren().add(tableViewHorses);
       partDisplay.getChildren().add(separator1);



   }


    public Node show() {
        return container;
    }

    public static TableView<Person> getTableViewHorses() {
        return tableViewHorses;
    }
}
