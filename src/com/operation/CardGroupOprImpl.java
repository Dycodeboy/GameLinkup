package com.operation;


import java.util.ArrayList;
import java.util.Random;

import com.beans.Point;
import com.core.Config;

public class CardGroupOprImpl implements CardGroupOpr {
	
	@Override
	public int[][] getRandomGroup(int colcount, int rowcount) {
		int[][] cardmap = new int[colcount+2][rowcount+2];
		boolean flag = false;
		int cardtype = 0;
		for (int i = 0; i < cardmap.length; i++) {
			for (int j = 0; j < cardmap[0].length; j++) {
				if(i==0 || j==0 || i==cardmap.length-1 || j==cardmap[0].length-1){
					cardmap[i][j] = -1;
				}else{
					cardmap[i][j] = cardtype;
					if(flag){
						cardtype = (cardtype+1)%Config.CARDTYPE;
					}
					flag^=true;
				}
			}
		}
		refreshMap(cardmap);
		return cardmap;
	}


	@Override
	public boolean isLink(Point p1, Point p2, int[][] cardmap) {
		if(p1==null || p2==null || (p1.getX()==p2.getX()&&p1.getY()==p2.getY()) ||cardmap[p1.getX()][p1.getY()]!=cardmap[p2.getX()][p2.getY()]){
			return false;
		}else{
			if(isLink_X(p1, p2, cardmap) || isLink_Y(p1, p2, cardmap) ||
					isOneCornerLink(p1, p2, cardmap) || isTwoCornerLink(p1, p2, cardmap)){
				return true;
			}
			
		}
		return false;
	}
	
	public void refreshMap(int[][] a){
		ArrayList<Point> list = new ArrayList<Point>();
		for (int i = 1; i < a.length-1; i++) {
			for (int j = 1; j < a[0].length-1; j++) {
				if(a[i][j]!=-1){
					list.add(new Point(i, j));
				}
			}
		}
		//每一位随机与其他位置交换一次
		Random random = new Random();
		for (int i = 0; i < list.size(); i++) {
			int randomIndex = random.nextInt(list.size());
			int temp = a[list.get(i).getX()][list.get(i).getY()];
			a[list.get(i).getX()][list.get(i).getY()] = a[list.get(randomIndex).getX()][list.get(randomIndex).getY()];
			a[list.get(randomIndex).getX()][list.get(randomIndex).getY()] = temp;
		}		
	}
	
	//是否X方向上直连
	private boolean isLink_X(Point p1, Point p2, int[][] cardmap){
		if(p1.getY()==p2.getY()){
			int x1 = p1.getX()<p2.getX()?p1.getX():p2.getX();
			int x2 = p1.getX()>p2.getX()?p1.getX():p2.getX();
			for (int i = x1+1; i <x2; i++) {
				if(cardmap[i][p1.getY()]!=-1){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	//是否Y方向上直连
	private boolean isLink_Y(Point p1, Point p2, int[][] cardmap){
		if(p1.getX()==p2.getX()){
			int y1 = p1.getY()<p2.getY()?p1.getY():p2.getY();
			int y2 = p1.getY()>p2.getY()?p1.getY():p2.getY();
			for (int i = y1+1; i <y2; i++) {
				if(cardmap[p1.getX()][i]!=-1){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	//一次转弯能否链接
	private boolean isOneCornerLink(Point p1, Point p2, int[][] cardmap){
		if(cardmap[p1.getX()][p2.getY()]==-1){
			Point pCorner = new Point(p1.getX(), p2.getY());
			if(isLink_Y(p1, pCorner, cardmap) && isLink_X(pCorner, p2, cardmap)){
				return true;
			}
		}
		if(cardmap[p2.getX()][p1.getY()]==-1){
			Point pCorner = new Point(p2.getX(), p1.getY());
			if(isLink_X(p1, pCorner, cardmap) && isLink_Y(pCorner, p2, cardmap)){
				return true;
			}
		}
		return false;
	}
	//两次次转弯能否链接
	private boolean isTwoCornerLink(Point p1, Point p2, int[][] cardmap){
		//左
		for (int i = p1.getX()-1; i >=0; i--) {
			if(cardmap[i][p1.getY()]!=-1){
				break;
			}
			if(isOneCornerLink(new Point(i, p1.getY()), p2, cardmap)){
				return true;
			}
		}
		//右
		for (int i = p1.getX()+1; i<cardmap.length; i++) {
			if(cardmap[i][p1.getY()]!=-1 ){
				break;
			}
			if(isOneCornerLink(new Point(i, p1.getY()), p2, cardmap)){
				return true;
			}
		}
		//上
		for (int i = p1.getY()-1; i >=0; i--) {
			if(cardmap[p1.getX()][i]!=-1){
				break;
			}
			if(isOneCornerLink(new Point(p1.getX(), i), p2, cardmap)){
				return true;
			}
		}
		
		//下
		for (int i = p1.getY()+1; i<cardmap[0].length; i++) {
			if(cardmap[p1.getX()][i]!=-1 ){
				break;
			}
			if(isOneCornerLink(new Point(p1.getX(), i), p2, cardmap)){
				return true;
			}
		}
		
		return false;
	}

}
