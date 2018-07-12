package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://www.acmicpc.net/problem/2667
public class Numbering {
	private int[][] map;
	private int group = 0;
	private List<Integer> results = new ArrayList<Integer>();
	private BufferedReader br;
	private BufferedWriter bw;
	public static void main(String[] args) throws IOException{
		Numbering numbering = new Numbering();
		numbering.start();
	}
	
	private void start() throws IOException{
		makeMap();
		search();
		printResult();
	}

	private void printResult() throws IOException {
		Collections.sort(results);
		bw.write(String.valueOf(results.size()));
		for(int result : results){
			bw.newLine();
			bw.write(String.valueOf(result));
		}
		
		bw.flush();
		br.close();
		bw.close();
	}

	private void makeMap() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int SIZE = Integer.parseInt(br.readLine());
		map = new int[SIZE][SIZE];
		for(int column = SIZE-1 ; column >= 0 ; column --){
			String line = br.readLine();
			for(int row = 0 ; row < SIZE ; row ++){
				map[column][row] = Character.getNumericValue(line.charAt(row));
			}
		}
	}
	
	private void search() {
		for(int column = 0 ; column < map.length ; column ++){
			for(int row = 0 ; row < map.length ; row ++){
				if(map[column][row] != 0) {
					results.add(0);
					dfs(column,row);
					group++;
				}
			}
		}
	}

	private void dfs(int column, int row) {
		map[column][row] = 0;
		int[] rowMove = { 0,0,-1,1 };
		int[] columnMove = { 1,-1,0,0 };
		results.set(group, (int)results.get(group) + 1);
		for(int move = 0; move < 4 ; move++){
			int movedColumn = column + columnMove[move];
			int movedRow = row + rowMove[move];
			if(checkIndex(movedColumn, movedRow) && map[movedColumn][movedRow] != 0){
				map[movedColumn][movedRow] = 0;
				dfs(movedColumn,movedRow);
			}
		}
	}

	private boolean checkIndex(int movedColumn, int movedRow) {
		return movedColumn < map.length  && movedColumn >= 0  && movedRow < map.length && movedRow >= 0;
	}
}
