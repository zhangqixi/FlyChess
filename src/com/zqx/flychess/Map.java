package com.zqx.flychess;


public class Map {
	int player1;
	int player2;
	int[] map = new int[100];
	int[] luckyTurn = { 6, 23, 40, 55, 69, 83 }; // 幸运轮盘
	int[] landMine = { 5, 13, 17, 33, 38, 50, 64, 80, 94 };// 地雷位置
	int[] pause = { 9, 27, 60, 93 };// 暂停
	int[] timeTunnel = { 20, 25, 45, 63, 72, 88, 90 };// 时空隧道

	public void createMap() {
		int i = 0;
		for (i = 0; i < luckyTurn.length; i++) {// 地图上设置幸运轮盘
			map[luckyTurn[i]] = 1;
		}

		for (i = 0; i < landMine.length; i++) {// 地图上设置地雷
			map[landMine[i]] = 2;
		}

		for (i = 0; i < pause.length; i++) {// 地图上设置暂停
			map[pause[i]] = 3;
		}
		for (i = 0; i < timeTunnel.length; i++)// 地图上设置时空隧道
			map[timeTunnel[i]] = 4;
	}


	public String getGraph(int i, int index, int playerpos1, int playerpos2) {
		String graph = "";
		if (index == playerpos1 && index == playerpos2) {// 两人重合时图形为@@
			graph = "@@";
		} else if (index == playerpos1) {
			graph = "A";
		} else if (index == playerpos2) {
			graph = "B";
		} else {
			switch (i) {
			case 1:
				graph = "◆";
				break;
			case 2:
				graph = "★";
				break;
			case 3:
				graph = "■";
				break;
			case 4:
				graph = "〓";
				break;
			case 0:
				graph = "::";
				break;
			}
		}

		return graph;
	}


	public void showLine1(int start, int end, int playerpos1, int playerpos2) {
		for (int i = start; i < end; i++) {
			System.out.print(getGraph(map[i], i, playerpos1, playerpos2));
		}
	}


	public void showLine2(int start, int end, int playerpos1, int playerpos2) {
		for (int i = end; i >= start; i--) {
			System.out.print(getGraph(map[i], i, playerpos1, playerpos2));
		}
	}


	public void showRLine(int start, int end, int playerpos1, int playerpos2) {
		for (int i = start; i < end; i++) {
			for (int j = 26; j > 0; j--) {
				System.out.print("  ");
			}
			System.out.print(getGraph(map[i], i, playerpos1, playerpos2));
			System.out.println();
		}
	}


	public void showLLine(int start, int end, int playerpos1, int playerpos2) {
		// 添加代码
		for (int i = start; i < end; i++) {
			System.out.println(getGraph(map[i], i, playerpos1, playerpos2));
		}
	}

	public void showMap(int playerpos1, int playerpos2) {
		showLine1(0, 31, playerpos1, playerpos2);
		System.out.println();
		showRLine(31, 35, playerpos1, playerpos2);
		showLine2(35, 64, playerpos1, playerpos2);
		System.out.println();
		showLLine(66, 69, playerpos1, playerpos2);
		showLine1(69, 99, playerpos1, playerpos2);
	}
}
