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
		System.out.println("              ��ʼ��Ϸ");
		System.out.println("*********************************************");
		System.out.println("\n----------------------���˶�ս------------------");
		System.out.println("��ѡ���ɫ��1�������� 2�� ��ɭ������ 3����˰�ɪ 4���Ͷ�");
		Scanner input = new Scanner(System.in);
		System.out.println("�����1ѡ���ɫ��");
		int role1 = input.nextInt();
		int role2;
		do {
			System.out.println("�����2ѡ���ɫ��");
			role2 = input.nextInt();
		} while (role2 == role1);
		setRole(1, role1);
		setRole(2, role2);
		play();
	}

	public void setRole(int no, int role) {
		switch (role) {
		case 1:
			playerName[no - 1] = "������";
			break;
		case 2:
			playerName[no - 1] = "��ɭ������";
			break;
		case 3:
			playerName[no - 1] = "��˰�ɪ";
			break;
		case 4:
			playerName[no - 1] = "�Ͷ�";
			break;
		default:
			break;
		}
	}

	public void play() {
		System.out.print("----------------------------");
		System.out.print("\n       ��ʼ��Ϸ        \n");
		System.out.println("----------------------------");
		System.out.println("^_^�����1��" + playerName[0] + "��ʿ����A");
		System.out.println("^_^�����2��" + playerName[1] + "��ʿ����B\n");
		System.out.println("ͼ��������ͣ�� ���������� ����� ��ʱ����� ::��· @@λ����ͬ");
		map.showMap(playerpos1, playerpos2);
		int step;
		while (playerpos1 < 99 && playerpos2 < 99) {
			if(goAndStop[0].equals("on")){    
				//���1������
				step = throwShifter(1); //������
				System.out.println("\n-----------------"); //��ʾ�����Ϣ
				System.out.println("�������� "+ step);
				playerpos1 = getCurPos(1, playerpos1, step);   //������һ���ƶ���ĵ�ǰλ��
				System.out.println("\n������"+playerName[0]+"��ǰλ�ã�"+ playerpos1);
				System.out.println("���Է���"+playerName[1]+"��ǰλ�ã�"+ playerpos2);
				System.out.println("-----------------\n");	
				map.showMap(playerpos1, playerpos2); //��ʾ��ǰ��ͼ
				if(playerpos1 == 99){ //����ߵ��յ�
					break;   //�˳�
				}
			}else{
				System.out.println(playerName[0] +"������ͣ�㣬ͣ��һ�Σ�\n");   //��ʾ�˴���ͣ��Ϣ
				goAndStop[0] = "on";   //�����´ο���״̬ 
			}
			if(goAndStop[1].equals("on")){
				//���2������
				step = throwShifter(2); //������
				System.out.println("\n-----------------"); //��ʾ�����Ϣ
				System.out.println("�������� "+ step);
				playerpos2 = getCurPos(2, playerpos2, step);   //������һ���ƶ���ĵ�ǰλ��
				System.out.println("\n������"+playerName[1]+"��ǰλ�ã�"+ playerpos2);
				System.out.println("���Է���"+playerName[0]+"��ǰλ�ã�"+ playerpos1);
				System.out.println("-----------------\n");
				map.showMap(playerpos1, playerpos2);
				if(playerpos2 == 99){ //����ߵ��յ�
					break;   //�˳�
				}
			}else{
				System.out.println(playerName[1] + "������ͣ�㣬ͣ��һ�Σ�\n"); //��ʾ�˴���ͣ��Ϣ
				goAndStop[1] = "on"; //�����´ο���״̬ 
			}
		} 
		//��Ϸ����
		System.out.println("***************************");
		System.out.println("         ��Ϸ����...        ");
		System.out.println("***************************\n");
		judge();
	}
	public int throwShifter(int no) {
		int step = 0;
		System.out.print("\n\n" + playerName[no - 1] + ", ������������ĸ����س����������ӣ� ");
		Scanner input = new Scanner(System.in);
		String answer = input.next();
		step = (int) (Math.random() * 10) % 6 + 1;// ����һ��1~6������,������������Ŀ
		return step;
	}

	public int getCurPos(int no, int position, int step) {
		position = position + step; // ��һ���ƶ����λ��
		if (position >= 99) {
			return 99;
		}
		Scanner input = new Scanner(System.in);
		switch (map.map[position]) { // ���ݵ�ͼ�еĹؿ����Ž����ж�
		case 0: // �ߵ���ͨ��
			if (no == 1 && playerpos2 == position) { // ���1��Է��������
				playerpos2 = 0; // �ȵ��Է����Է��ص����
				System.out.println(":-D ��������...�ȵ����ˣ�");
			}
			if (no == 2 && playerpos1 == position) { // ���2��Է��������
				playerpos1 = 0; // �ȵ��Է����Է��ص����
				System.out.println(":-D ��������...�ȵ����ˣ�");
			}
			break;
		case 1: // ��������
			System.out.println("\n*******��ӭ������������******");
			System.out.println("   ��ѡ��һ��������");
			System.out.println("   1. ����λ�� 2. ��ը");
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
			System.out.println(":~) " + "�Ҹ����Ҷ�Ҫ����...");
			break;
		case 2: // �ȵ�����
			position = position - 6; // �ȵ�������6��
			System.out.println("~:-( " + "�ȵ����ף�������...");
			break;
		case 3: // ��һ����ͣһ��
			goAndStop[no - 1] = "off"; // �����´���ͣ������
			System.out.println("~~>_<~~ Ҫͣսһ���ˡ�");
			break;
		case 4: // ʱ�����
			position = position + 10; // ����ʱ�����������10��
			System.out.println("|-P " + "����ʱ���������ˬ��");
			break;
		}
		// ���ش˴������Ӻ���ҵ�λ������
		if (position < 0) {
			return 0;
		} else if (position > 99) {
			return 99;
		} else {
			return position;
		}
	}

	/**
	 * ��ʾ��ս���
	 */
	public void judge() {
		if (playerpos1 > playerpos2) {
			System.out.println("��ϲ" + playerName[0] + "����! ����ʤ�ˣ�");
		} else {
			System.out.println("��ϲ" + playerName[1] + "����! ����ʤ�ˣ�");
		}
	}
}