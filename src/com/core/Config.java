package com.core;

import javafx.scene.image.Image;

public abstract class Config {
	public static int ROWCOUNT = 8;//行数
	public static int COLCOUNT = 15;//列数
	public static int CARDTYPE = 10;//游戏中实际使用的牌数
	public static int TIMESET = 60;//游戏时间
	
	public static int CARDTYPESUM = 52;
	private static String RESPATH = "/com/res/";
	public static Image[] CARDIMAGES = new Image[CARDTYPESUM];
	public static Image BKIMAGE = new Image("/com/res/background.jpg");
	
	public static double SHEIGHT;//程序窗口高
	public static double SWIDTH;//这个是窗口的宽度？？EN
	
	public static double GHEIGHT;//游戏画面高
	public static double GWIDTH;
	public static double PaneWidth = 200;// 控制面板宽度
	public static double PaneHeight = GHEIGHT;// 控制面板高度
	
	public static double CARDHEIGHT;//
	public static double CARDWIDTH;//
	public static double xOffset;//横向偏移
	public static double yOffset;//纵向偏移
	
	static{
		for(int i=0;i<CARDTYPESUM;i++){
			CARDIMAGES[i] = new Image(RESPATH+i+".png");
		}
		GHEIGHT = 600;
		GWIDTH =GHEIGHT*CARDIMAGES[0].getWidth()*COLCOUNT/CARDIMAGES[0].getHeight()/ROWCOUNT;
		CARDWIDTH = GWIDTH/COLCOUNT;
		CARDHEIGHT = GHEIGHT/ROWCOUNT;
		xOffset = CARDWIDTH;
		yOffset = CARDHEIGHT;
		SWIDTH = GWIDTH + 2*xOffset+PaneWidth;
		SHEIGHT = GHEIGHT + 2*yOffset;
	}
	
}
 