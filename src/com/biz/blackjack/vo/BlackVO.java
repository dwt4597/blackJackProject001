package com.biz.blackjack.vo;

import java.util.*;

public class BlackVO {

	private String playerCard1;
	private String playerCard2;

	private int pScore1;
	private int pScore2;
	private int pSum;

	private String dealerCard1;
	private String dealerCard2;

	private int dScore1;
	private int dScore2;
	private int dSum;

	public String getPlayerCard1() {
		return playerCard1;
	}

	public void setPlayerCard1(String playerCard1) {
		this.playerCard1 = playerCard1;
	}

	public String getPlayerCard2() {
		return playerCard2;
	}

	public void setPlayerCard2(String playerCard2) {
		this.playerCard2 = playerCard2;
	}

	public int getpScore1() {
		return pScore1;
	}

	public void setpScore1(int pScore1) {
		this.pScore1 = pScore1;
	}

	public int getpScore2() {
		return pScore2;
	}

	public void setpScore2(int pScore2) {
		this.pScore2 = pScore2;
	}

	public int getpSum() {
		return pSum;
	}

	public void setpSum(int pSum) {
		this.pSum = pSum;
	}

	public String getDealerCard1() {
		return dealerCard1;
	}

	public void setDealerCard1(String dealerCard1) {
		this.dealerCard1 = dealerCard1;
	}

	public String getDealerCard2() {
		return dealerCard2;
	}

	public void setDealerCard2(String dealerCard2) {
		this.dealerCard2 = dealerCard2;
	}

	public int getdScore1() {
		return dScore1;
	}

	public void setdScore1(int dScore1) {
		this.dScore1 = dScore1;
	}

	public int getdScore2() {
		return dScore2;
	}

	public void setdScore2(int dScore2) {
		this.dScore2 = dScore2;
	}

	public int getdSum() {
		return dSum;
	}

	public void setdSum(int dSum) {
		this.dSum = dSum;
	}

	@Override
	public String toString() {
		return "BlackVO [playerCard1=" + playerCard1 + ", playerCard2=" + playerCard2 + ", pScore1=" + pScore1
				+ ", pScore2=" + pScore2 + ", pSum=" + pSum + ", dealerCard1=" + dealerCard1 + ", dealerCard2="
				+ dealerCard2 + ", dScore1=" + dScore1 + ", dScore2=" + dScore2 + ", dSum=" + dSum + "]";
	}

}
