package personXML.components;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;

/**
 * Created by PROGRAMMER II on 24.10.2014.
 */
public class Header {
    public static HBox header;
    public static int heightHeader=145;
public Header(String pathToImage, String mainImage){
    // Header
    header = new HBox();
    header.setAlignment(Pos.TOP_CENTER);
    header.setLayoutX(0);
    header.setLayoutY(0);
    header.setMinWidth( 1280);
    header.setMinHeight(heightHeader);//100
    header.setMaxHeight(heightHeader);
    header.setPrefHeight(heightHeader);
    URL image = getClass().getResource(mainImage);//"/skins/img/header/headerPayment.png");//Input
    header.setStyle("-fx-background-color:transparent;-fx-background-image: url('" + image + "');-fx-background-repeat: stretch;-fx-background-position: bottom;-fx-background-size:1280,145;");

    if(!(pathToImage==null || pathToImage.isEmpty())) {

    VBox hBox = new VBox();
    hBox.setPrefWidth(heightHeader);
    hBox.setMinHeight(heightHeader);//100
    hBox.setStyle("-fx-background-color: transparent");

    VBox hBox1 = new VBox();
    hBox1.setAlignment(Pos.CENTER);
    hBox1.setPrefWidth(600);
    hBox1.setMinHeight(heightHeader);//100
    hBox1.setStyle("-fx-background-color: transparent");
if(pathToImage!=null && !pathToImage.isEmpty()) {
    ImageView logo = new ImageView();
    try {
        logo = new ImageView(new Image(pathToImage));//"file:skins/img/header/"

        //+ "quick_dial_icon.png"));
        logo.setCache(false);
    } catch (Exception ex) {
        ex.printStackTrace();
    }


    logo.setStyle("-fx-alignment: center");
    hBox1.getChildren().addAll(logo);
}
    header.getChildren().addAll(hBox, hBox1);
}
    header.setAlignment(Pos.BOTTOM_CENTER);

}
public static Header getInstance(String pathToImage, String mainImage){
    return new Header(pathToImage, mainImage);
}
public static Node getNode(){
    return header;
}
}
