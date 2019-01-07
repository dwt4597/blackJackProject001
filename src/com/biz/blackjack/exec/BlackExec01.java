package com.biz.blackjack.exec;

import java.util.*;

import com.biz.blackjack.service.*;

public class BlackExec01 {
	Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Blackjack Start!!");
		System.out.println();
		BlackService bs = new BlackService();
		int index = 0;
		bs.createCard1();
		bs.createCard2();
		
		bs.shuffleDeck();

		index = bs.divide52Deck(index);
		bs.check___01();
		bs.makeToScore();
		bs.check___02();
		
		index =bs.dealerPrecondition(index);
		bs.playerPrecondition(index);
		bs.battle();
		
		
//		bs.dealerDeck();
//		bs.playerDeck();
//		bs.match();
//		bs.checkDeck();
//		bs.splitScore();
	
//		bs.showPScore();
//		bs.showDScore();
	}

}
