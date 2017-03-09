package com.screen;

import com.beans.Background;
//import com.beans.Card;
import com.beans.CardGroup;
import com.core.Config;
import com.core.GameScreen;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class GamePane extends GameScreen {
	
//	private Card testCard = new Card(2, 1, 1);
	private CardGroup myCardGroup = CardGroup.getInstance();
	private Background myBackground = Background.getInstance();
	private int Gametime;
	private String Information;
	private Timeline timeline;
	private KeyFrame keyFrame;
	{
		int duration = 1000;
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		keyFrame = new KeyFrame(Duration.millis(duration), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(Gametime==0){
					mGameState = GameState.GAME_OVER;
					Information = "      Game Over!!!";
				}else{
					Gametime--;	
					Information = Integer.toString(Gametime);
				}
			}
		});
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
	}
	
	public GamePane(double width, double height) {
		super(width, height);
		Gametime = Config.TIMESET;
		addObject(myBackground);
		addObject(myCardGroup);
		start();
		mGameState = GameState.GAME_START;
	}
	
	
	@Override
	public void draw(GraphicsContext gc) {
		if(mGameState == GameState.GAME_OVER){
			gc.setFont(new Font("Arial", 30));
			gc.fillText(Information, 20, 40);
			return ;
		}else{			
			super.draw(gc);
			gc.setFont(new Font("Arial", 30));
			gc.fillText(Information, 20, 40);
		}
	}


	public void upSetGroup() {
		myCardGroup.upSetMap();
	}
	
	public void reSetMap() {
		myCardGroup.reSetMap();	
		mGameState = GameState.GAME_START;
		Gametime = Config.TIMESET;
		
	}
	
	public void searchCouple() {
		myCardGroup.searchCouple();
	}

}
