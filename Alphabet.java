package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Alphabet {
	static char[][] board;
	static boolean[][] visit;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int max = 0;
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] size = br.readLine().split(" ");
		int height = Integer.parseInt(size[0]);
		int width = Integer.parseInt(size[1]);
		board = new char[height][width];
		for(int i = 0; i < height ; i++){
			String line = br.readLine();
			for(int j = 0; j < width ; j++){
				board[i][j] = line.charAt(j);
			}
		}
		List<Character> seq = new ArrayList<>();
		seq.add(board[0][0]);
		search(height, width, 0,0, seq);
		System.out.println(max);
	}
	
	private static void search(int height, int width, int x, int y, List<Character> seq) {
		List<Character> route = new ArrayList();
		route.addAll(seq);
		for(int i = 0; i < 4 ; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx >= 0 && nx < height && ny >= 0 && ny < width){
				if(!route.contains(board[nx][ny])){
					route.add(board[nx][ny]);
					search(height,width,nx,ny,route);
					route.remove(route.size()-1);
				}
			}
		}
		if(max < route.size()) max = route.size();
	}
}