package com.screen;

import com.core.Config;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Group root = new Group();
		//ImageView backview=Config.BKIMAGE;
		//root.getChildren().add(backview);
		GamePane game=new GamePane(Config.SWIDTH,Config.SHEIGHT);
		ButtonPanel buttonPanel = new ButtonPanel(game);
		buttonPanel.setTranslateX(Config.SWIDTH-Config.PaneWidth);
		
		root.getChildren().addAll(game,buttonPanel);
		
		Scene scene=new Scene(root,Config.SWIDTH,Config.SHEIGHT);
		stage.setScene(scene);
		stage.setTitle("LinkUp");
		stage.setResizable(false);
		stage.show();	
		game.requestFocus();
		game.initEvents();
		
	}

}
