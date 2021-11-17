package com.zqx.flychess;

import java.util.Scanner;

public class Game {
	Map map = new Map();
	int playerpos1;
	int playerpos2;
	String[] goAndStop = new String[2];
	String[] playerName = new String[2];

	public void init() {
		map.createMap();
		playerpos1 = 0;
		playerpos2 = 0;
		goAndStop[0] = "on";
		goAndStop[1] = "on";
	}

	public void start() {
		init();
		System.out.println("*********************************************");
		System.out.println("              开始游戏");
		System.out.println("*********************************************");
		System.out.println("\n----------------------两人对战------------------");
		System.out.println("请选择角色：1、戴高乐 2、 艾森豪威尔 3、麦克阿瑟 4、巴顿");
		Scanner input = new Scanner(System.in);
		System.out.println("请玩家1选择角色：");
		int role1 = input.nextInt();
		int role2;
		do {
			System.out.println("请玩家2选择角色：");
			role2 = input.nextInt();
		} while (role2 == role1);
		setRole(1, role1);
		setRole(2, role2);
		play();
	}

	public void setRole(int no, int role) {
		switch (role) {
		case 1:
			playerName[no - 1] = "戴高乐";
			break;
		case 2:
			playerName[no - 1] = "艾森豪威尔";
			break;
		case 3:
			playerName[no - 1] = "麦克阿瑟";
			break;
		case 4:
			playerName[no - 1] = "巴顿";
			break;
		default:
			break;
		}
	}

	public void play() {
		System.out.print("----------------------------");
		System.out.print("\n       开始游戏        \n");
		System.out.println("----------------------------");
		System.out.println("^_^（玩家1）" + playerName[0] + "的士兵：A");
		System.out.println("^_^（玩家2）" + playerName[1] + "的士兵：B\n");
		System.out.println("图例：■暂停点 ◆幸运轮盘 ★地雷 〓时空隧道 ::道路 @@位置相同");
		map.showMap(playerpos1, playerpos2);
		int step;
		while (playerpos1 < 99 && playerpos2 < 99) {
			if(goAndStop[0].equals("on")){    
				//玩家1掷骰子
				step = throwShifter(1); //掷骰子
				System.out.println("\n-----------------"); //显示结果信息
				System.out.println("骰子数： "+ step);
				playerpos1 = getCurPos(1, playerpos1, step);   //计算这一次移动后的当前位置
				System.out.println("\n（您）"+playerName[0]+"当前位置："+ playerpos1);
				System.out.println("（对方）"+playerName[1]+"当前位置："+ playerpos2);
				System.out.println("-----------------\n");	
				map.showMap(playerpos1, playerpos2); //显示当前地图
				if(playerpos1 == 99){ //如果走到终点
					break;   //退出
				}
			}else{
				System.out.println(playerName[0] +"已在暂停点，停掷一次！\n");   //显示此次暂停信息
				goAndStop[0] = "on";   //设置下次可掷状态 
			}
			if(goAndStop[1].equals("on")){
				//玩家2掷骰子
				step = throwShifter(2); //掷骰子
				System.out.println("\n-----------------"); //显示结果信息
				System.out.println("骰子数： "+ step);
				playerpos2 = getCurPos(2, playerpos2, step);   //计算这一次移动后的当前位置
				System.out.println("\n（您）"+playerName[1]+"当前位置："+ playerpos2);
				System.out.println("（对方）"+playerName[0]+"当前位置："+ playerpos1);
				System.out.println("-----------------\n");
				map.showMap(playerpos1, playerpos2);
				if(playerpos2 == 99){ //如果走到终点
					break;   //退出
				}
			}else{
				System.out.println(playerName[1] + "已在暂停点，停掷一次！\n"); //显示此次暂停信息
				goAndStop[1] = "on"; //设置下次可掷状态 
			}
		} 
		//游戏结束
		System.out.println("***************************");
		System.out.println("         游戏结束...        ");
		System.out.println("***************************\n");
		judge();
	}
	public int throwShifter(int no) {
		int step = 0;
		System.out.print("\n\n" + playerName[no - 1] + ", 请您按任意字母键后回车启动掷骰子： ");
		Scanner input = new Scanner(System.in);
		String answer = input.next();
		step = (int) (Math.random() * 10) % 6 + 1;// 产生一个1~6的数字,即掷的骰子数目
		return step;
	}

	public int getCurPos(int no, int position, int step) {
		position = position + step; // 第一次移动后的位置
		if (position >= 99) {
			return 99;
		}
		Scanner input = new Scanner(System.in);
		switch (map.map[position]) { // 根据地图中的关卡代号进行判断
		case 0: // 走到普通格
			if (no == 1 && playerpos2 == position) { // 玩家1与对方骑兵相遇
				playerpos2 = 0; // 踩到对方，对方回到起点
				System.out.println(":-D 哈哈哈哈...踩到你了！");
			}
			if (no == 2 && playerpos1 == position) { // 玩家2与对方骑兵相遇
				playerpos1 = 0; // 踩到对方，对方回到起点
				System.out.println(":-D 哈哈哈哈...踩到你了！");
			}
			break;
		case 1: // 幸运轮盘
			System.out.println("\n*******欢迎进入幸运轮盘******");
			System.out.println("   请选择一种运气：");
			System.out.println("   1. 交换位置 2. 轰炸");
			System.out.println("=============================");
			int choice = input.nextInt();
			int temp;
			switch (choice) {
			case 1:
				if (no == 1) {
					temp = position;
					position = playerpos2;
					playerpos2 = temp;
				} else if (no == 2) {
					temp = position;
					position = playerpos1;
					playerpos1 = temp;
				}
				break;
			case 2:
				if (no == 1 && playerpos2 < 6) {
					playerpos2 = 0;
				} else {
					playerpos2 = playerpos2 - 6;
				}
				if (no == 2 && playerpos1 < 6) {
					playerpos1 = 0;
				} else {
					playerpos1 = playerpos1 - 6;
				}
				break;
			}
			System.out.println(":~) " + "幸福的我都要哭了...");
			break;
		case 2: // 踩到地雷
			position = position - 6; // 踩到地雷退6步
			System.out.println("~:-( " + "踩到地雷，气死了...");
			break;
		case 3: // 下一次暂停一次
			goAndStop[no - 1] = "off"; // 设置下次暂停掷骰子
			System.out.println("~~>_<~~ 要停战一局了。");
			break;
		case 4: // 时空隧道
			position = position + 10; // 进入时空隧道，加走10步
			System.out.println("|-P " + "进入时空隧道，真爽！");
			break;
		}
		// 返回此次掷骰子后玩家的位置坐标
		if (position < 0) {
			return 0;
		} else if (position > 99) {
			return 99;
		} else {
			return position;
		}
	}

	/**
	 * 显示对战结果
	 */
	public void judge() {
		if (playerpos1 > playerpos2) {
			System.out.println("恭喜" + playerName[0] + "将军! 您获胜了！");
		} else {
			System.out.println("恭喜" + playerName[1] + "将军! 您获胜了！");
		}
	}
}