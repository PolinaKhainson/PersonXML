package personXML.components;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.util.Duration;

public class Loader extends Group
{
	private HBox container = new HBox( 50 );
	private int delay = 600;
	
	public Loader(int value)
	{
		super();
		
		delay = value;
		
		for ( int i = 0; i < 5; i++ )
		{
			Circle circle = new Circle();
			circle.setRadius( 7 );
			circle.setFill( Color.GRAY );
			container.getChildren().add( circle );
		}
		
		final Circle circle = CircleBuilder.create()
	            .radius( 7 )
	            .layoutX( 7 )
	            .layoutY( 7 )
	            .fill( Color.PURPLE )
	            .build();
		
		Timeline anim = TimelineBuilder.create().keyFrames(
				new KeyFrame(Duration.valueOf( Integer.toString( delay ) + "ms" ),
						new EventHandler<ActionEvent>() 
						{
							public void handle(ActionEvent event) 
					        {
								if ( circle.getTranslateX() > 200 )
								{
									circle.setTranslateX( -64 );
								}
								circle.setTranslateX( circle.getTranslateX() + 64 );
					        }
						})).build();
		anim.setCycleCount( Animation.INDEFINITE );
		anim.play();
		
		getChildren().addAll( container, circle );
	}
}
