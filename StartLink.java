package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StartLink {
	static boolean[] divide;
	static int count = 0;
	static ArrayList<Integer> start;
	static ArrayList<Integer> link;
	static ArrayList<Integer> results = new ArrayList<>();;
	static int times;
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		times = Integer.parseInt(sc.nextLine());
		link = new ArrayList<>();
		divide = new boolean[times];
		start = new ArrayList<>();
		int[][] power = new int[times][times];
		setLink();
		for(int i = 0 ; i < times; i++){
			String[] line = sc.nextLine().split(" ");
			for(int j = 0 ; j< times; j++){
				power[i][j] = Integer.parseInt(line[j]);
			}
		}
		setTeam(power, 0);
		Collections.sort(results);
		System.out.println(results.get(0));
	}
	private static void setLink() {
		for(int i = 0; i < times ; i++){
			link.add(i);
		}
	}
	private static void setTeam(int[][] power,int idx) {
		for(int i = idx ; i < times; i++){
			divide[i] = true;
			start.add(i);
			link.remove(link.indexOf(i));
			count++;
			if(count == times/2){
				calcDiff(power);
			}
			setTeam(power,i+1);
			divide[i] = false;
			count--;
			start.remove(start.indexOf(i));
			link.add(i);
		}
		
	}
	private static void calcDiff(int[][] power) {
		int startResult = 0;
		int linkResult = 0;
		for(int i =0; i < times/2; i++){
			for(int j = 0 ; j < times/2 ; j++){
				startResult += power[start.get(i)][start.get(j)];
				linkResult += power[link.get(i)][link.get(j)];
			}
		}
		results.add(Math.abs(startResult-linkResult));
	}
}
