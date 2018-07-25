package algorithm.Gear;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/14891
public class Main {
	static LinkedList<Integer>[] gears = new LinkedList[4];
	static int[] turnCount = new int[4];
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		createGears(br);
		turnGears(br);
		printResult();
	}

	private static void printResult() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int result = 0;
		for(int gear = 0; gear < 4 ; gear++){
			result += gears[gear].get(0) * Math.pow(2, gear);
		}
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
	}

	private static void turnGears(BufferedReader br) throws IOException {
		int K = Integer.parseInt(br.readLine());
		for(int k = 0 ; k < K ; k++){
			String[] line = br.readLine().split(" ");
			int gearIdx = Integer.parseInt(line[0]) - 1;
			int direction = Integer.parseInt(line[1]);
			countTurn(gearIdx, direction);
			turn();
		}
		br.close();
	}
	
	private static void turn(){
		for(int gear = 0 ; gear < 4 ; gear++){
			if(turnCount[gear] == -1) turnLeft(gear);
			if(turnCount[gear] == 1) turnRight(gear);
			turnCount[gear] = 0;
		}
	}

	private static void countTurn(int gearIdx, int direction) {
		rigthCount(gearIdx,direction);
		leftCount(gearIdx,direction);
		turnCount[gearIdx] = direction;
	}

	private static void rigthCount(int gearIdx, int direction) {
		while(gearIdx < 3){
			if(gears[gearIdx].get(2) == gears[++gearIdx].get(6)) break;
			direction *= -1;
			turnCount[gearIdx] = direction;
		}
	}

	private static void leftCount(int gearIdx, int direction) {
		while(gearIdx > 0){
			if(gears[gearIdx].get(6) == gears[--gearIdx].get(2)) break;
			direction *= -1;
			turnCount[gearIdx] = direction;
		}
	}

	private static void turnLeft(int gearIdx) {
			gears[gearIdx].addLast(gears[gearIdx].pollFirst());
	}

	private static void turnRight(int gearIdx) {
			gears[gearIdx].addFirst(gears[gearIdx].pollLast());
	}

	private static void createGears(BufferedReader br) throws IOException {
		for(int status = 0 ; status < 4 ; status++){
			String line = br.readLine();
			LinkedList<Integer> gear = new LinkedList();
			for(int type = 0; type < 8 ; type++){
				gear.add(Character.getNumericValue(line.charAt(type)));
			}
			gears[status] = gear;
		}
	}
}
