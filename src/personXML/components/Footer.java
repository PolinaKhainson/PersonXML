package personXML.components;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorAdjustBuilder;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class Footer {
    public static HBox footer;
    private VBox footerV;
    public Button back;
    public Button next;
    public static int heightFooter = 145;
    public static boolean ExistBack = true;
    public static boolean ExistNext = true;

    public Footer(boolean existBack, boolean existNext, boolean main) {

        footer = new HBox();
        footerV = new VBox();
        footerV.setMaxSize(1280,heightFooter);
        footerV.setMinSize(1280, heightFooter);
        footerV.setAlignment(Pos.CENTER);
       ExistBack = existBack;
        ExistNext = existNext;
        if(main){
            ExistBack=false;
            ExistNext=false;
        }

        footer.setLayoutX(0);
        footer.setLayoutY(1024 - heightFooter);
        footer.setMinWidth(1280);
        footer.setMinHeight(heightFooter);//100
        footer.setMaxHeight(heightFooter);
        footer.setPrefHeight(heightFooter);
        URL image = getClass().getResource("/resources/img/header/green.png");
        footer.setStyle("-fx-background-image: url('" + image + "');-fx-background-repeat: no-repeat;-fx-background-position: bottom;-fx-background-size:1280,145;");
        footer.setAlignment(Pos.BOTTOM_CENTER);

        HBox left = new HBox();
        if(ExistBack && ExistNext) {

            left.setMinWidth((1280 - 300 - 300) / 3 - 130);//200
            left.setMaxWidth((1280 - 300 - 300) / 3 - 130);//200
        }
        if(ExistBack && !ExistNext) {
            left.setMinWidth((1280 - 300 - 300) / 3 );//200
            left.setMaxWidth((1280 - 300 - 300) / 3 );//200
        }
        if(!ExistBack && ExistNext) {
            left.setMinWidth((1280 - 300 - 300) / 3 );//200
            left.setMaxWidth((1280 - 300 - 300) / 3 );//200
        }

        left.setStyle("-fx-background-color: transparent");

        if(!main) footer.getChildren().add(left);

        if (ExistBack) {
            HBox hBoxBack = new HBox();
            hBoxBack.setAlignment(Pos.CENTER);
            VBox vBoxBack = new VBox(3);
            vBoxBack.setAlignment(Pos.CENTER);//_LEFT
            vBoxBack.setMinHeight(heightFooter - 4);
            vBoxBack.setMaxHeight(heightFooter - 4);


            back = new Button();
            URL image8 = getClass().getResource("/resources/img/footer/back.png");
            back.setStyle("-fx-background-image: url('" + image8 + "');-fx-background-repeat: no-repeat;-fx-background-size: 300, 86;-fx-arc-height: 20;");
            back.setMinWidth(300);
            back.setMaxWidth(300);
            back.setMinHeight(86);
            back.setAlignment(Pos.TOP_LEFT);
            vBoxBack.getChildren().addAll(back);//h1, back, h2
            hBoxBack.getChildren().addAll(vBoxBack);
            footer.getChildren().add(hBoxBack);
        }
        if(!main) {
            HBox middle = new HBox();
            middle.setMinWidth((1280 - 300 - 300) / 3);//300
            middle.setMaxWidth((1280 - 300 - 300) / 3);//300
            middle.setStyle("-fx-background-color: transparent");
            footer.getChildren().add(middle);
        }
        if (ExistNext) {
            HBox hBoxNext = new HBox();
            hBoxNext.setAlignment(Pos.CENTER);
            VBox vBoxNext = new VBox();
            vBoxNext.setAlignment(Pos.CENTER);//_LEFT
            vBoxNext.setMinHeight(heightFooter - 4);
            vBoxNext.setMaxHeight(heightFooter - 4);


            next = new Button();
            URL image9 = getClass().getResource("/resources/img/footer/next.png");
            next.setStyle("-fx-background-image: url('" + image9 + "');-fx-background-repeat: no-repeat;-fx-background-size: 300, 86;-fx-arc-height: 20;");
            next.setMinWidth(300);
            next.setMaxWidth(300);
            next.setMinHeight(86);
            next.setAlignment(Pos.TOP_LEFT);
            vBoxNext.getChildren().addAll(next);//h3, next, h4
            hBoxNext.getChildren().addAll(vBoxNext);
            footer.getChildren().add(hBoxNext);
        }
        if(main){
            VBox langPanelV = new VBox();
            langPanelV.setMaxSize(220,heightFooter);
            langPanelV.setMinSize(220, heightFooter);
            langPanelV.setAlignment(Pos.CENTER);
            HBox langPanel = new HBox();
            langPanel.setAlignment(Pos.CENTER);
            //langPanel.setLayoutX(430);//340
            //langPanel.setLayoutY(930);//910
            langPanel.setMinWidth(220);
            langPanel.setMaxWidth(220);

            langPanel.setCache(false);
            langPanel.setSpacing(5);

            final Map<String, Language> langList = new HashMap<String, Language>();

            String[] arr = new String[]{"EN", "RU", "UA"};
            Language[] arr2 = new Language[]{Language.EN, Language.RU, Language.UA};

            for (int i = 0; i < arr.length; i++)
                langList.put(arr[i], arr2[i]);

            for (int i = 0; i < langList.size(); i++) {
                final ImageView logo = new ImageView(
                        new Image(getClass().getResourceAsStream("/resources" +
                                "/img/footer/" +
                                arr[i] + ".png")));
                logo.setId(arr[i]);
                // logo.setStyle("-fx-border-image-repeat: no-repeat");
                logo.setCache(false);


                final Reflection effect = new Reflection();
                effect.setFraction(0.5);
                logo.setEffect(effect);


                final ColorAdjust effectPressed = ColorAdjustBuilder.create()
                        .brightness(-0.4)
                        .build();

                logo.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        logo.setScaleX(0.95);
                        logo.setScaleY(0.95);
                        effect.setInput(effectPressed);

                        TimelineBuilder.create().keyFrames(
                                new KeyFrame(Duration.valueOf("100ms"),
                                        new EventHandler<ActionEvent>() {
                                            public void handle(ActionEvent event) {
                                                logo.setScaleX(1.0);
                                                logo.setScaleY(1.0);
                                                effect.setInput(null);

                                              //  setLanguage(langList.get(logo.getId()));
                                                //new MainMenu().updateLocale();
                                               // serviceMenu.show();
////										footerMenu.updateLocale();
/*
                                                if (terminalState == TerminalState.INTERNET_CONNECTION_ERROR) {
                                                    showTerminalError("INTERNET CONNECTION ERROR", true);
                                                    if (!Terminal.WIFI && ModemTest.reconnect()) {
                                                        showTerminalMenu();
                                                    }

                                                }
*/
                                            }
                                        })).build().play();
                    }
                });
                logo.setStyle("-fx-background-repeat: no-repeat;-fx-alignment: top-center;-fx-effect:innershadow(one-pass-box, transparent, 10, 1.0, 100, 100)");
                langPanel.getChildren().add(logo);
                langPanel.setStyle("-fx-border-image-repeat: no-repeat;-fx-background-color: transparent");//lightgray


            }
            langPanelV.getChildren().addAll(langPanel);
            // footer


            // clock
            VBox timeFooter = new VBox();
            timeFooter.setAlignment(Pos.CENTER);
            timeFooter.setPrefSize(200, 110);//150
            timeFooter.setMaxSize(200, 110);
            timeFooter.setSpacing(0);

            HBox clockFooterH = new HBox();
            clockFooterH.setAlignment(Pos.CENTER);
            clockFooterH.setMaxSize(200, 71);
            clockFooterH.setMinSize(200, 71);

            HBox clockFooter = new HBox();
            clockFooter.setCache(false);
            clockFooter.setMaxSize(180, 71);
            clockFooter.setMinSize(180, 71);
            clockFooter.setAlignment(Pos.CENTER);
            URL image1 = getClass().getResource("/resources/img/footer/clock_frame.png");
            clockFooter.setStyle("-fx-background-image: url('" + image1 + "');-fx-background-repeat: no-repeat;-fx-background-size: 180,71;-fx-background-position: center");

            final Label digitalClock = new Label();
            digitalClock.setCache(false);
            digitalClock.setStyle("-fx-font-size: 50; -fx-text-fill: #000000;");

            clockFooter.getChildren().addAll(digitalClock);


            final Timeline digitalTime = new Timeline(
                    new KeyFrame(Duration.seconds(0),
                            new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent actionEvent) {
                                    String separator = ":";
                                    Calendar calendar = GregorianCalendar.getInstance();
                                    Date currentDate = new Date();
                                    calendar.setTime(currentDate);
                                    String hourString = pad(2, '0', calendar.get(Calendar.HOUR_OF_DAY) + "");
                                    String minuteString = pad(2, '0', calendar.get(Calendar.MINUTE) + "");
                                    digitalClock.setText("   " + hourString + separator + minuteString /*+ ":" + secondString*/);
                                }
                            }
                    ),
                    new KeyFrame(Duration.seconds(1)));
            digitalTime.setCycleCount(Animation.INDEFINITE);
            digitalTime.play();

            clockFooterH.getChildren().addAll(clockFooter);

            HBox dateFooter = new HBox(5);
            dateFooter.setAlignment(Pos.CENTER);
            dateFooter.setCache(false);
            dateFooter.setMaxSize(200, 25);
            dateFooter.setMinSize(200, 25);

            final Label timeClock = new Label();
            timeClock.setCache(false);
            timeClock.setStyle("-fx-font-size: 25; -fx-text-fill: #000000;-fx-alignment: bottom-center");
            final Timeline digitalTime1 = new Timeline(
                    new KeyFrame(Duration.seconds(0)
                            ,
                            new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent actionEvent) {
                                    String separator = ".";
                                    Calendar calendar = GregorianCalendar.getInstance();
                                    Date currentDate = new Date();
                                    calendar.setTime(currentDate);
                                    String dayString = pad(2, '0', calendar.get(Calendar.DAY_OF_MONTH) + "");
                                    String monthString = pad(2, '0', calendar.get(Calendar.MONTH) + "");
                                    String yearString = pad(4, '0', calendar.get(Calendar.YEAR) + "");
                                    timeClock.setText("     " + dayString + separator + monthString + separator + yearString.substring(2)); /*+ ":" + secondString*/
                                }
                            }
                    ),
                    new KeyFrame(Duration.seconds(1)));
            digitalTime1.setCycleCount(Animation.INDEFINITE);
            digitalTime1.play();
            dateFooter.getChildren().addAll(timeClock);

            timeFooter.getChildren().addAll(clockFooterH, dateFooter);
           footer.setSpacing(5);
           footer.getChildren().addAll(
                   langPanelV,
                   timeFooter);//, loginBox
        }

    }
    private String pad(int fieldWidth, char padChar, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < fieldWidth; i++) {
            sb.append(padChar);
        }
        sb.append(s);

        return sb.toString();
    }

    public static Footer getInstance(boolean existBack, boolean existNext, boolean main) {
        return new Footer(existBack, existNext, main);
    }

    public static Node getNode() {
        return footer;
    }

}
