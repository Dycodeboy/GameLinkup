package com.core;

import javafx.scene.image.Image;

public abstract class Config {
	public static int ROWCOUNT = 8;//����
	public static int COLCOUNT = 15;//����
	public static int CARDTYPE = 10;//��Ϸ��ʵ��ʹ�õ�����
	public static int TIMESET = 60;//��Ϸʱ��
	
	public static int CARDTYPESUM = 52;
	private static String RESPATH = "/com/res/";
	public static Image[] CARDIMAGES = new Image[CARDTYPESUM];
	public static Image BKIMAGE = new Image("/com/res/background.jpg");
	
	public static double SHEIGHT;//���򴰿ڸ�
	public static double SWIDTH;//����Ǵ��ڵĿ�ȣ���EN
	
	public static double GHEIGHT;//��Ϸ�����
	public static double GWIDTH;
	public static double PaneWidth = 200;// ���������
	public static double PaneHeight = GHEIGHT;// �������߶�
	
	public static double CARDHEIGHT;//
	public static double CARDWIDTH;//
	public static double xOffset;//����ƫ��
	public static double yOffset;//����ƫ��
	
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
 