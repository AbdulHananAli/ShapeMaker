package ShapeMaker;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class GUI extends Application {

	public static void main(String[] args)  {
		//launch links to the start method
		launch(args);
	}
	//make global variable
	Shape Circle;
	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	
	public void start(Stage primaryStage) throws Exception {
	
		//changes the name of primaryStage to window for ease of understanding
		Stage Window;
		Window = primaryStage;
		
		//make a scene and container
		Scene beta;
		StackPane container;
		
		//make an object of shape maker 
		//you can make others with different sized 
		shape_maker sm = new shape_maker(30);
		
		//Initialise container
		container = new StackPane(); // StackPane() piles all the nodes  to the centre and on top of each other
		
		//make a button with the label button
		Button plus = new Button("Button");
		
		//size the button
		plus.setPrefSize(100, 20);
		
		//lambda i.e. "e->" set the event handling if the button is pushed
		plus.setOnAction( e->{container.getChildren().add(Circle = sm.getShape());//makes a circle on the interface
		
		//add event handlers hook-up for both mouse pressed and Dragged 
		Circle.setOnMousePressed(MousePressed);
		Circle.setOnMouseDragged(MouseDragged);});
		
		//add button to the pane
		container.getChildren().add(plus);
		
		//add the pane to the scene
		beta = new Scene(container, 1000, 500);
		
		//add the scene to the window
		Window.setScene(beta);
		
		//add title to window
		Window.setTitle("ShapeMaker");
		
		//make window visible 
		Window.show();
	}

	//event handler for mouse pressed
	EventHandler<MouseEvent> MousePressed = new EventHandler<MouseEvent>()
	    {
	        @Override
	        public void handle(MouseEvent mouseEvent)
	        {
	            //Initialise the original coordinates 
	            orgSceneX = mouseEvent.getSceneX();
	            orgSceneY = mouseEvent.getSceneY();
	            
	            //gets the coordinates for the movement made
	            orgTranslateX = (((javafx.scene.shape.Circle) mouseEvent.getSource())).getTranslateX();
	            orgTranslateY = (((javafx.scene.shape.Circle) mouseEvent.getSource())).getTranslateY();
	        }
	    };
	    
	//event handler for mouse dragged
	EventHandler<MouseEvent> MouseDragged = new EventHandler<MouseEvent>()
	    {
	        @Override
	        public void handle(MouseEvent mouseEvent)
	        {
	            //gets the total of the coordinates difference 
	            double offsetX = mouseEvent.getSceneX() - orgSceneX;
	            double offsetY = mouseEvent.getSceneY() - orgSceneY;
	            
	            //gets the current location coordinates according to previous original location 
	            double newTranslateX = orgTranslateX + offsetX;
	            double newTranslateY = orgTranslateY + offsetY;
	            
	            //Dynamically moves the shape from previous location by coordinates of the movement 
	            ((javafx.scene.shape.Circle) (mouseEvent.getSource())).setTranslateX(newTranslateX);
	            ((javafx.scene.shape.Circle) (mouseEvent.getSource())).setTranslateY(newTranslateY);
	        }
	    };
}
