package snippet;

public class Snippet {
	public TitledPane accord(){
		TitledPane oneTab = new TitledPane();
		Label day = new Label("Monday");
		Image weather = new Image( "https://cdn.pixabay.com/photo/2012/04/18/13/21/clouds-37009_960_720.png", 0, 24, true, true);
		
		VBox errorPane = new VBox();
		errorPane.setStyle("-fx-padding: 10");
		errorPane.setSpacing(10);
		errorPane.getChildren().add(new Label("weatherInfo 1"));
	
		Label nErrors = new Label();
	    nErrors.getStyleClass().add("nerrors");
	    nErrors.textProperty().bind(Bindings.size(errorPane.getChildren()).asString());
	
	    HBox header = new HBox();
	    header.setSpacing(2);
	    header.setAlignment(Pos.CENTER);
	    header.setStyle("header");
	    header.getChildren().add(nErrors);
	    header.getChildren().add(new ImageView(weather));
	    header.getChildren().add(day);
		oneTab.setGraphic(header);
		return oneTab;
	}
}

