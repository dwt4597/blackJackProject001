package com.biz.blackjack.service;

import java.util.*;

import com.biz.blackjack.vo.*;

public class BlackService {
	
	//카드의 숫자, 모양, 알파벳을 저장할 List 3개.
	List<String> numList;
	List<String> moList;
	List<String> alpaList;
	
	//shuffleDeck() 메서드로 실행되어,  
	//카드(숫자 모양 알파벳 조합)을 랜덤으로 섞는다.
	List<String> randomDeckList;
	
	//위의 랜덤으로 섞인 52장의 카드를
	//플레이어의 덱과, 딜러의 덱으로 나누어 저장시킬 List.
	List<String> player52Deck;
	List<String> dealer52Deck;
	
	//자신(딜러)의 점수 보기, 승패를 결정할때 사용될
	//딜러와 플레이어의 각자 카드의 점수를 저장할 List.
	List<Integer> pscoreList;
	List<Integer> dscoreList;
	
	//입력받기위한 Scanner함수
	Scanner scan;
	
	//생성자 부분. 
	public BlackService() {
		
		// 배열을 List 객체형으로 변환.
		// 각각의 클래스에 배열타입으로 값을 저장시키고
		// 초기화해주었다.
		numList = Arrays.asList(number.num);
		moList = Arrays.asList(moyang.design);
		alpaList = Arrays.asList(alpa.design);
		
		//randomDeckList를 ArrayList()로 초기화
		randomDeckList = new ArrayList();
		
		//player52Deck,dealer52Deck를 ArrayList()로 초기화
		player52Deck = new ArrayList();
		dealer52Deck = new ArrayList();
		
		//pscoreList,dscoreList를 ArrayList()로 초기화
		pscoreList = new ArrayList();
		dscoreList = new ArrayList();
		
		//Scanner함수를 초기화.
		scan = new Scanner(System.in);
	}
	
	//숫자_모양 형식의 카드를 만드는 매서드이다.
	public void createCard1() {
		
		//num 클래스에 담겨있는 숫자들을 String형 변수 n에 대입
		for (String n : numList) {
			
			//moyang 클래스에 담겨있는 숫자들을 String형 변수 m에 대입
			for (String m : moList) {
				
				//각 카드들을 숫자_모양 형식으로 card(스트링형)변수에
				//담는다. 나중에 split할때, 10이 오류가나서(2자리로읽는듯)
				// ""에서 "_"으로 바꿨다 (split할때 구분의 기준)
				String card = n + "_" + m;
				
				//randomDeckList에 저장한다.
				randomDeckList.add(card);
			}
		}
	}
	//알파벳_모양 형식의 카드를 만드는 매서드이다.
	public void createCard2() {
		
		//alpa 클래스에 담겨있는 숫자들을 String형 변수 a에 대입
		for (String a : alpaList) {
			
			//alpa 클래스에 담겨있는 숫자들을 String형 변수 a에 대입
			for (String n : moList) {
				
				//각 카드들을 알파벳_모양 형식으로 card(스트링형)변수에 저장한다.
				String card = a + "_" + n;
				
				//randomDeckList에 저장한다.
				randomDeckList.add(card);
			}
		}
	}

	//덱을 섞는 메서드이다.
	public void shuffleDeck() {
		
		//기존의 저장되었던 카드들의 List의 사이즈를 size변수에 저장.
		int size = randomDeckList.size();
		
		//사이즈만큼, randomDeckList를 shuffle한다.
		for (int i = 0; i < size; i++) {
			Collections.shuffle(randomDeckList);
		}
	}

	// P와 D 각각 52장씩 랜덤하게 순차적으로 뽑게함.
	//BlackExec01(실행시키는 클래스)에서
	//index는 0으로 설정되어있다.
	//매개변수 index ( 0 )을 받아옴.
	public int divide52Deck(int index) {
		
		//'딜러와 게이머는 순차적으로 카드를 하나씩 뽑아, 
		//각자 2개의 카드를 소지'이므로 for문의 조건을 2로(0,1)한다.
		for (int i = 0; i < 2; i++) {
			
			//player52Deck,dealer52Deck에 randomDeckList에 저장된 값들을
			//분배하여 저장시킨다. 여기서 randomDeckList의 
			//저장된 값 순번들은 index값을 1씩 증가시키며,
			//각각 중복이 되지 않도록 분배시킨다.
			player52Deck.add(randomDeckList.get(index++));
			dealer52Deck.add(randomDeckList.get(index++));
		}
		//BlackExec01(실행시키는 클래스)에서 index변수에
		//저장시킨다. return index로.
		return index;
	}

	// Player와 Dealer의 각각 52장 카드가, 
	// 서로 랜덤한 카드들인지 확인하는 메서드.
	public void check___01() {
		
		System.out.print("Player Card = ");
		//player52Deck를 String s변수에 대입한다.
		for (String s : player52Deck) {
			//s의 값을 출력한다.
			System.out.print("|"+ s+"|");
		}
		System.out.println();
		
		System.out.print("Dealer Card = ");
		//dealer52Deck를 String s변수에 대입한다.
		for (String s : dealer52Deck) {
			//s의 값을 출력한다.
			System.out.print("|" + "#_#"+"|");
		}
		System.out.println();
		System.out.println();
	}
	
	public int splitScore(String s) {
		//카드를 "_"단위로 쪼개어, card에 배열형태로 저장한다.
		String[] card = s.split("_");
		
		//문자열로 저장되어있는 숫자들과
		//알파벳들을 숫자형으로 return하고
		// int형 cardScore 변수에 저장한다.
		int cardScore = converter(card[0]);
		
		//변환된 '값'을 return한다.
		return cardScore;
	}
	
	//알파벳과 문자열로된 숫자를
	//int형 숫자로 변환한다.
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
			//문자열의 숫자를, int형 숫자로 변환 후
			//숫자를 return한다.
			int a = Integer.valueOf(s);
			return a;
		}
	}

	
	
	//카드들을 split으로 분리하고, 
	//알파벳과 문자열 숫자를 숫자로 변환, int타입으로  변환한다.
	public void makeToScore() {
		
		//player52Deck을 문자열 s변수에 대입
		for (String s : player52Deck) {
			
			//splitScore()에 s를 매개변수로 보낸 후,
			// int형 score로 받는다.
			int score = splitScore(s);
			
			//pscoreList에 score을 저장한다.
			pscoreList.add(score);
		}
		
		//dealer52Deck을 문자열 s변수에 대입
		for (String s : dealer52Deck) {
			
			//splitScore()에 s를 매개변수로 보낸 후,
			// int형 score로 받는다.
			int score = splitScore(s);
			//dscoreList에 score을 저장한다.
			dscoreList.add(score);
		}

	}

	//Player와 Dealer의 카드와 점수를 확인시켜주는 매서드.
	public void check___02() {
		System.out.println(" 카드 점수 ");
		
			//예)Player Card =[7, 10] 형태로 보여준다.
			System.out.print("Player Card =" + pscoreList);
		
		System.out.println();
			//Dealer Card =[ #, # ] 형태로 보여준다.
			System.out.print("Dealer Card =" + "[ #, # ] ");
			
		
		System.out.println();
		System.out.println();
	}
	//'딜러는 합계점수가 16점 이하이면 반드시 1장을 추가(17점이상이면 추가X)를
	//하는 딜러의 조건 메서드이다.
	public int dealerPrecondition(int index) {
		
		//합계를 계산하기위해 dscoreList의 점수들의 합계를 sum함수에 저장한다.
		//2장의 카드를 변환한 점수 2개(카드2개의 점수)가 dscoreList에 저장되어있음.
		int sum = 0;
		for(int i = 0; i < dscoreList.size(); i++) {
			int a = dscoreList.get(i);
			sum += a;

		}
		//합계(sum변수)이 16점 이상이면, 1장을 추가로 받는다.
		if(sum<=16) {
			System.out.println("(딜러카드가 16점이하.. 1장 Hit 했습니다.)");
			
			//randomDeckList에서 새로운 카드를 받고,
			//값을 구하는 매서드(splitScore())을 실행시킨다음
			//return된 값을 int형 변수 s에 저장한다.
			int s = splitScore(randomDeckList.get(index++));
			
			//조건의 새로 추가된 카드값을 , 기존의 2개의 값이있는 
			//dscoreList에 추가한다.
			dscoreList.add(s);
			
			// sum이 17이상이면, 별다른 실행시킬게 없으므로
			// 그 상태로 메서드를 끝낸다.
		} else if ( sum >=17) {
			System.out.println("(딜러카드 17점 이상.. Stand 했습니다.)");
		}
		System.out.println();
		
		//Dealer의 카드를 볼 수 있는 문장을 추가.
		System.out.println("1.Dealer Card보기 , press Any Key. 안보기");
		
		//키보드로부터 문자열 s변수에 값을 입력받는다.
		String s = scan.nextLine();
		
		//1을 입력받았을 경우, 딜러의 카드를 보여준다. 
		//다른 입력일 경우, 그냥 넘어간다.
		if(s.equals("1")) {
		System.out.println("Dealer Card =" + dscoreList);
		} else{
			System.out.println();
		}
		return index;
	}
	
	//'게이머는 얼마든지 카드를 추가로 뽑을 수 있다.'를 수행하는 매서드
	public void playerPrecondition(int index) {

		while (true) {
			System.out.println();
			//나의 카드를 보여준 뒤, 카드를 추가로 뽑을지, 승패를 결정하는
			//게임으로 넘어갈지 결정한다.
			System.out.println("내 카드" + pscoreList);
			System.out.println("Hit?? (1.Hit || 2.Stand)");
			String select = scan.nextLine();
			//입력받은 값이 1일 경우, 
			//randomDeckList에서 새로운 카드를 받고,
			//값을 구하는 매서드(splitScore())을 실행시킨다음
			//return된 값을 int형 변수 s에 저장한다.
			if (select.equals("1")) {
				int s = splitScore(randomDeckList.get(index++));
				//조건의 새로 추가된 카드값을 , 기존의 2개의 값이있는 
				//pscoreList에 추가한다.
				pscoreList.add(s);
				
				//pscoreList에 저장된 값(기존의 2장, hit해서 새로받은 1장의 값)
				//을 보여준다.
				for (int a : pscoreList) {
					System.out.print(a + " ");
				}
				//2일 경우, 
				//승패 결과를 위한 battel()메서드를 실행한다.
			} else if (select.equals("2")) {
				battle();
				break;
			}

		}
	}

	//플레이어와 딜러의 점수들이 저장된 List(pscoreList,dscoreList)로
	//게임의 승패를 결정한다.
	public void battle() {
		int dealerCardSum = 0;
		int playerCardSum = 0;
		
		//플레이어의 카드들( 2장, 혹은 3장의 값)을 합산하여
		//playerCardSum 변수에 저장한다.(위에서 int형, 0값으로 초기화된)
		for(int p : pscoreList) {
			playerCardSum += p;
		}
		
		//딜러의 카드들( 2장, 혹은 3장의 값)을 합산하여
		//dealerCardSum 변수에 저장한다.(위에서 int형, 0값으로 초기화된)
		for(int d : dscoreList) {
			dealerCardSum += d;
		}
		
		//'21을 초과하면 무조건 초과한 쪽이 게임에서 진다'
		//라는 조건을 만족시키기위한 if문
		if(playerCardSum>21) {
			System.out.println("Dealer Card = "+ dscoreList);
			System.out.println("Player Card = "+ pscoreList);
			System.out.println("Bust! Player 패배, Dealer 승리");
			
			return;
		}
		
		//'21을 초과하면 무조건 초과한 쪽이 게임에서 진다'
		//라는 조건을 만족시키기위한 조건문
		if(dealerCardSum>21) {
			System.out.println("Dealer Card = "+ dscoreList);
			System.out.println("Player Card = "+ pscoreList);
			System.out.println("Bust! Dealer 패배, Player 승리");
			
			return;
		}
		
		//'딜러와 게이머중 소유한 카드의 합이 21에 가장 가까운쪽이 
		//승리한다'는 조건을 만족시키기위한 조건문.
		
		//위의 조건들을 다 만족하고 왔을 경우,
		// 각자의 카드의 합들이 21을 초과하지 않으므로
		//dealerCardSum이 큰 경우, Dealer Win을 출력
		// 아닐경우, 는 playerCardSum이 큰 경우이므로
		//Player Win을 출력.
		if(playerCardSum<dealerCardSum) {
			System.out.println("Dealer Card = "+ dscoreList);
			System.out.println("Player Card = "+ pscoreList);
			System.out.println("Dealer Win");
			return;
		} else {
			System.out.println("Dealer Card = "+ dscoreList);
			System.out.println("Player Card = "+ pscoreList);
			System.out.println("Player Win");
			return;
		}
		
		
		
	}

	
//	 boolean flag = true;
//	 int size = randomDeckList.size();
//	 for (int i = 0; i < size; i++) {
//	 if (flag == true) {
//	 shuffleDeck();
//	 String pgetCard = randomDeckList.get(i);
//	 player52Deck.add(pgetCard);
//	 flag = false;
//	 }
//	 if (flag == false) {
//	 shuffleDeck();
//	 String dgetCard = randomDeckList.get(i);
//	 dealer52Deck.add(dgetCard);
//	 flag = true;
//	 }
//	
//	 }
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
