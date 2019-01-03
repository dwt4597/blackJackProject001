package com.biz.blackjack.service;

import java.util.*;

import com.biz.blackjack.vo.*;

public class BlackService {

	List<String> numList;
	List<String> moList;
	List<String> alpaList;

	List<String> randomDeckList;

	List<String> player52Deck;
	List<String> dealer52Deck;

	List<Integer> pscoreList;
	List<Integer> dscoreList;
	Scanner scan;

	public BlackService() {
		// 배열을 List 객체형으로 변환
		numList = Arrays.asList(number.num);
		moList = Arrays.asList(moyang.design);
		alpaList = Arrays.asList(alpa.design);
		randomDeckList = new ArrayList();

		player52Deck = new ArrayList();
		dealer52Deck = new ArrayList();

		pscoreList = new ArrayList();
		dscoreList = new ArrayList();
		scan = new Scanner(System.in);
	}

	public void createCard1() {
		for (String n : numList) {
			for (String m : moList) {
				String card = n + "_" + m;
				randomDeckList.add(card);
			}
		}
	}

	public void createCard2() {
		for (String a : alpaList) {
			for (String n : moList) {
				String card = a + "_" + n;
				randomDeckList.add(card);
			}
		}
	}

	public void shuffleDeck() {
		int size = randomDeckList.size();
		for (int i = 0; i < size; i++) {
			Collections.shuffle(randomDeckList);
		}
	}

	// P와 D 각각 52장씩 랜덤하게 순차적으로 뽑게함.
	public int divide52Deck(int index) {

		for (int i = 0; i < 2; i++) {
			player52Deck.add(randomDeckList.get(index++));
			dealer52Deck.add(randomDeckList.get(index++));
		}

		return index;
	}

	// P와 D의 각각 52장 카드가, 서로 랜덤한 카드들인지 확인
	public void check___01() {
		for (String s : player52Deck) {
			System.out.print("Player Card:"+ s+"\t");
		}
		System.out.println();
		for (String s : dealer52Deck) {
			System.out.print("Dealer Card:" + s+"\t");
		}
		System.out.println();
		System.out.println();
	}

	public void makeToScore() {
		for (String s : player52Deck) {
			int score = splitScore(s);
			pscoreList.add(score);
		}
		for (String s : dealer52Deck) {
			int score = splitScore(s);
			dscoreList.add(score);
		}

	}

	public void check___02() {
		System.out.println("각 카드 점수");
		
			System.out.print(pscoreList);
		
		System.out.println();
		
			System.out.print(dscoreList);
			
		
		System.out.println();
		System.out.println();
	}
	public void dealerPrecondition(int index) {
		int sum = 0;
		for(int i = 0; i < dscoreList.size(); i++) {
			int a = dscoreList.get(i);
			sum += a;

		}
		if(sum<=16) {
			System.out.println("딜러카드가 16점이하. 1장 Hit");
			int s = splitScore(randomDeckList.get(index++));
			dscoreList.add(s);
		} else if ( sum >=17) {
			System.out.println("딜러카드 17점 이상. Stand");
		}
			
		System.out.println(dscoreList);
	}
	public void playerHit(int index) {

		while (true) {
			System.out.println("Hit?? (1.Hit || 2.Stand)");
			String select = scan.nextLine();

			if (select.equals("1")) {
				int s = splitScore(randomDeckList.get(index++));
				pscoreList.add(s);
				for (int a : pscoreList) {
					System.out.print(a + " ");
				}
			} else if (select.equals("2")) {
				battle();
			}

		}
	}



	private void battle() {

	}

	public int converter(String s) {
		if (s.equals("A")) {
			return 1;
		} else if (s.equals("K")) {
			return 10;
		} else if (s.equals("Q")) {
			return 10;
		} else if (s.equals("J")) {
			return 10;
		} else {
			int a = Integer.valueOf(s);
			return a;
		}
	}

	public int splitScore(String s) {

		String[] card = s.split("_");
		int cardScore = converter(card[0]);
		return cardScore;
	}
	// boolean flag = true;
	// int size = randomDeckList.size();
	// for (int i = 0; i < size; i++) {
	// if (flag == true) {
	// shuffleDeck();
	// String pgetCard = randomDeckList.get(i);
	// player52Deck.add(pgetCard);
	// flag = false;
	// }
	// if (flag == false) {
	// shuffleDeck();
	// String dgetCard = randomDeckList.get(i);
	// dealer52Deck.add(dgetCard);
	// flag = true;
	// }
	//
	// }
	/*
	 * while (true) { System.out.println("HIT?? (1.Hit || 2.Stand"); String select =
	 * scan.nextLine(); int selection = Integer.valueOf(select); if (selection == 1)
	 * { int i = (int) (Math.random() * (52 - 3 + 1))+ 3; String a =
	 * randomDeckList.get(i); int b = Integer.valueOf(a); sum += b; vo.setpSum(sum);
	 * if(sum>21) { System.out.println("21초과. 게임 짐"); return; } } else if (selection
	 * == 2) { battle(); } playerDeck.add(vo); }
	 */

	// for (BlackVO vo : dealerList) {
	// String d1 = vo.getDealerCard1();
	// String d2 = vo.getDealerCard2();
	// String d3 = vo.getDealerCard3();
	//
	// String[] dd1 = d1.split("");
	// String ddd1 = dd1[0];
	// int dcard1 = converter(ddd1);
	// dscoreList.add(dcard1);
	//
	// String[] dd2 = d2.split("");
	// String ddd2 = dd2[0];
	// int dcard2 = converter(ddd2);
	// dscoreList.add(dcard2);
	//
	// String[] dd3 = d3.split("");
	// String ddd3 = dd3[0];
	// int dcard3 = converter(ddd3);
	// dscoreList.add(dcard3);
	//
	// }
	// for (BlackVO v : playerList) {
	// String p1 = v.getPlayerCard1();
	// String p2 = v.getPlayerCard2();
	// String p3 = v.getPlayerCard3();
	//
	// String[] pp1 = p1.split("");
	// String ppp1 = pp1[0];
	// int pcard1 = converter(ppp1);
	// pscoreList.add(pcard1);
	//
	// String[] pp2 = p2.split("");
	// String ppp2 = pp2[0];
	// int pcard2 = converter(ppp2);
	// pscoreList.add(pcard2);
	//
	// String[] pp3 = p3.split("");
	// String ppp3 = pp3[0];
	// int pcard3 = converter(ppp3);
	// pscoreList.add(pcard3);
	//
	// }
}
// public void DealerPrecondition() {
// for (int i : dscoreList) {
// int dS = dscoreList.get(0) + dscoreList.get(1);
// if (dS <= 16) {
// System.out.println("(!Dealer카드가 16점이하이므로, " + "1장을 추가로 뽑음(강제))");
// int dS2 = dS + dscoreList.get(2);
//
// }
// }
// }
// public void checkDeck() {
// // for(String s : deckList) {
// // System.out.println(s);
// // }
// for (BlackVO vo : playerList) {
// System.out.println(vo);
// }
// for (BlackVO v : dealerList) {
// System.out.println(v);
// }
// // System.out.println("-------------------");
// // for (BlackVO v : playerList) {
// // System.out.println(v);
// // }
// // }
//
// // public void makePlayerScore() {
// // for(BlackVO a : playerList) {
// // String[] s = a.getPlayerCard1();
// //
// // }
// }
// public void showPScore() {
//
// int sum = pscoreList.get(0) + pscoreList.get(1);
// System.out.printf("내 패 [%d],[%d] => %d\n", pscoreList.get(0),
// pscoreList.get(1), sum);
//
// }
//
// public void showDScore() {
// int sum = dscoreList.get(0) + dscoreList.get(1);
// System.out.printf("니 패 [%d],[%d] => %d\n", dscoreList.get(0),
// dscoreList.get(1), sum);
//
// }

// public void dealerDeck() {
//
// String dgetCard1 = randomDeckList.get(0);
//
// String dgetCard2 = randomDeckList.get(1);
//
// String dgetCard3 = randomDeckList.get(2);
//
// BlackVO vo = new BlackVO();
// vo.setDealerCard1(dgetCard1);
// vo.setDealerCard2(dgetCard2);
// vo.setDealerCard3(dgetCard3);
// dealerList.add(vo);
// }
//
// public void playerDeck() {
//
// String pgetCard1 = randomDeckList.get(3);
//
// String pgetCard2 = randomDeckList.get(4);
//
// String pgetCard3 = randomDeckList.get(5);
//
// BlackVO vo = new BlackVO();
// vo.setPlayerCard1(pgetCard1);
// vo.setPlayerCard2(pgetCard2);
// vo.setPlayerCard3(pgetCard3);
// playerList.add(vo);
//
// }

// public void match() {
// for (BlackVO v : dealerList) {
// for (BlackVO vo : playerList) {
// vo.setDealerCard1(v.getDealerCard1());
// vo.setDealerCard2(v.getDealerCard2());
// vo.setDealerCard3(v.getDealerCard3());
// vo.setDealerCardSum(v.getDealerCardSum());
// }
// }
// }

// public void mainGame() {
// for (BlackVO vo : playerList) {
// if (vo.getDealerCardSum() < vo.getPlayerCardSum()) {
// System.out.println("나의 승");
// } else if (vo.getDealerCardSum() > vo.getPlayerCardSum()) {
// System.out.println("너의 승");
// }
// }
// }
//
// public void PlayerHit() {
// for (BlackVO vo : playerList) {
// int pS = vo.getPlayerCardSum();
// int pS2 = vo.getPlayerCardSum() + vo.getPlayerCard3();
// vo.setPlayerCardSum(pS2);
// }
// }
//

//
// }
////
// public void Showdealercard() {
// for (BlackVO vo : dealerList) {
// System.out.println("딜러 패 = " + "[" + vo.getDealerCard1() + "]" + "[" +
// vo.getDealerCard2() + "]" + "="
// + +(vo.getDealerCard1() + vo.getDealerCard2()));
//
// }
// }
//
// public void Showmycard() {
// for (BlackVO vo : playerList) {
// System.out.println("나의 패 = " + "[" + vo.getPlayerCard1() + "]" + "[" +
// vo.getPlayerCard2() + "]" + "="
// + (vo.getPlayerCard1() + vo.getPlayerCard2()));
//
// }
// }

// public void select() {
// System.out.println("게임시작? (1.시작 || Any key.종료)");
// String selec = scan.nextLine();
// int hitCard = 0;
// if (selec.equals("1")) {
//
// dealerCardShuffle();
// playerCardShuffle();
////// match();
// DealerPrecondition();
// Showmycard();
// Showdealercard();
// System.out.println("Hit 하시겠습니까?? (1.Hit | 2.Stand)");
// String strHit = scan.nextLine();
// if (strHit.equals("1")) {
// PlayerHit();
// } else if(strHit.equals("2")){
// mainGame();
// } else {
// System.out.println("???");
// }
//
// }
// }
// 딜러 카드를 섞고, 첫번째~세번째 카드의 점수를
// 딜러 리스트에 저장.
