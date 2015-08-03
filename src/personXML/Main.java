package personXML;

import personXML.components.MainScreen;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

   @Override
    public void start(Stage stage) throws Exception{
        final Terminal terminal = Terminal.getInstance();
        terminal.init();
        Scene scene = new Scene( terminal.getMainScreen().getRoot());
        scene.setCursor(Cursor.HAND);
        scene.setFill(Color.GREEN);

        stage.setWidth(MainScreen.width);
        stage.setHeight(MainScreen.height);

        stage.setScene(scene);
        terminal.getMainScreen().setStage( stage );

        stage.setTitle( "HorseParkRaces Application" );
        stage.setFullScreen( true );
        String css = this.getClass().getResource("/resources/css/main.css").
                toExternalForm();
        stage.getScene().getStylesheets().add(css);
        stage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
