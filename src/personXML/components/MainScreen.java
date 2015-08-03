package personXML.components;

import personXML.Terminal;
import personXML.TerminalState;
import personXML.components.table.GridTable;
import personXML.configuration.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainScreen {
    private static Group rootMain;
    private static Group root;

    private static Stage stage;
    public static Language language;

    public static double width = com.sun.glass.ui.Screen
            .getMainScreen().getWidth();
    public static double height = com.sun.glass.ui.Screen
            .getMainScreen().getHeight();

    private Timeline watchTimer;
    private static final int UPDATE_INTERVAL = 10;

    private VBox rootErrorMesage = null;
    //private Button mainMenuButton;
    public static ResourceBundle currentLocale;
    public TerminalState terminalState=TerminalState.INIT_TERMINAL;
    private Label resultMessage;
    public MainScreen() {

        initLanguage();
        rootMain = new Group();
        root = new Group();
        try {

            String css = this.getClass().getResource("/resources/css/main.css").
                    toExternalForm();

            root.getStylesheets().add(css);
            root.setId("background");
            root.setCache(false);
            root.setOnMouseReleased(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    mouseClicked();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        watchTimer = TimelineBuilder.create().keyFrames(
                new KeyFrame(Duration.seconds(UPDATE_INTERVAL), "updateScreen",
                        new EventHandler<ActionEvent>() {
                            public synchronized void handle(ActionEvent event) {
                                terminalEvent();
                            }
                        })).build();
        watchTimer.setCycleCount(Animation.INDEFINITE);
        watchTimer.play();

    }
    public static void initLanguage() {
        try {
            language = Language.stringToLanguage(
                    Locale.getDefault().getCountry());
            Locale locale = null;
            switch (language) {
                case EN:
                    locale = new Locale("en", "US");
                    break;
                case RU:
                    locale = new Locale("ru", "RU");
                    break;
                case UA:
                    locale = new Locale("uk", "UA");
                    break;
                default:
                    locale = new Locale("ru", "RU");
            }

            currentLocale = Terminal.getResourceBundle();

        } catch (Exception ex) {
            language = Language.RU;
            ex.printStackTrace();
        }
    }

    private void terminalEvent() {
        switch (terminalState) {
            case WAIT: {
                stopWatchTimer();
                showTerminalMenu();

            }
            break;
            case INPUT_PROCESS: {
            }
            break;
            case INIT_TERMINAL: {
                terminalState = TerminalState.WAIT;
                break;
            }
            case ERROR: {
                showTerminalError("Terminal disabled.");
                break;
            }
        }
    }
    public void showTerminalError(String message) {

        getRoot().getChildren().clear();

        rootErrorMesage = new VBox(100);
        rootErrorMesage.setAlignment(Pos.CENTER);
        rootErrorMesage.setLayoutX(500);
        rootErrorMesage.setLayoutY(250);

        ImageView errorView = new ImageView(new Image(
                getClass().getResource("/resources/img/" + "error.jpg").toString()));
        errorView.setCache(false);

        Text text = new Text(message);
        text.setStyle("-fx-font-size: 30px; -fx-fill: black;");
        rootErrorMesage.getChildren().addAll(errorView, text);

        getRoot().getChildren().add(rootErrorMesage);

    }

    public void mouseClicked() {

        stopWatchTimer();
        startWatchTimer();
    }
    private void stopWatchTimer() {

        watchTimer.stop();
    }

    private void startWatchTimer() {

        watchTimer.playFromStart();
    }
    public void showTerminalMenu() {
        root.getChildren().clear();

        Header header = Header.getInstance("/resources/img/header/writers.jpg", "/resources/img/header/green.png");

        header.getNode().setLayoutX(0);
        header.getNode().setLayoutY(0);

        VBox container = new VBox(100);
        container.setLayoutX(5);
        container.setLayoutY(Header.heightHeader);//145
        container.setMaxSize(width,height-Header.heightHeader-Footer.heightFooter);
        container.setMinSize(width, height - Header.heightHeader - Footer.heightFooter);
        container.setCache(false);
        container.setSpacing(2);
        container.setId("background");

        GridTable table= new GridTable();
        container.getChildren().add(table.show());

        Label label=new Label("command>");
        label.setMinSize(300, 50);
        label.setMaxSize(300, 50);
        label.setStyle("-fx-alignment: center-left;-fx-background-color: blue;-fx-font-size: 40;");

        container.getChildren().add((label));

        TextField command= new TextField();
        command.setMinSize(width, 50);
        command.setMaxSize(width, 50);
        command.setEditable(true);
        command.setStyle("-fx-font-weight:bold;-fx-font-size: 30 ");
        command.setCursor(Cursor.HAND);
        container.getChildren().add((command));

        resultMessage=new Label("");
        resultMessage.setMinSize(width, 50);
        resultMessage.setStyle("-fx-alignment: center;-fx-background-color: palevioletred;-fx-font-size: 40;");

        container.getChildren().add((resultMessage));


        Footer footer = Footer.getInstance(false, false, true);
        footer.getNode().setLayoutX(0);
        footer.getNode().setLayoutY(height - Footer.heightFooter);
        root.getChildren().addAll(
                header.getNode(),
                container,
                footer.getNode()
        );
        setRootMain(root);

    }
    public void showTerminalInit(final String message) {
        getRoot().getChildren().clear();

        VBox root = new VBox();
        root.setCache(false);
        root.setAlignment(Pos.CENTER);
        root.setMaxSize(1280, 1024);
        root.setMinSize(1280, 1024);

        HBox middle=new HBox();
        middle.setAlignment(Pos.CENTER);

        Label text = new Label(message);
        text.setStyle("-fx-font-size: 50px; -fx-fill: black;");

        Loader loader = new Loader(600);
        root.setCache(false);
        middle.getChildren().addAll(loader);
        root.getChildren().addAll(text,middle);
        getRoot().getChildren().addAll(root);
    }

    public Group getRoot() {
        return root;
    }
    public Group getRootMain() {
        return rootMain;
    }

    public void setRootMain(Group rootMain) {
        this.rootMain = rootMain;
    }

    public static Stage getStage() {
        return stage;
    }

    public static double getWidth() {
        return width;
    }

    public static double getHeight() {
        return height;
    }

    public static void setStage(Stage stage) {
        MainScreen.stage = stage;
    }

    public static long roundToLong(double value){
        Locale locale  = new Locale( "en", "US" );
        String pattern = "###";

        DecimalFormat decimalFormat = (DecimalFormat)
                NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);

        String format = decimalFormat.format(value);
        System.out.println(format);
        return Long.valueOf(format);
    }

}
