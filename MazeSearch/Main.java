package algorithm.MazeSearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;


//https://www.acmicpc.net/problem/2178
public class Main {
	static int COLUMN;
	static int ROW;
	static int[][] maze;
	static Queue<MazePoint> queue = new LinkedList();
	static boolean[][] visited;
	static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		createMaze(br);
		searchMaze();
		printResult(br, bw);
	}

	private static void printResult(BufferedReader br, BufferedWriter bw) throws IOException {
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void searchMaze(){
		queue.offer(new MazePoint(COLUMN-1, 0, 1)); 
		visited[COLUMN-1][0] = true;// Ω√¿€¡°
		while(!queue.isEmpty()){
			MazePoint thisPoint = queue.poll();
			for(int move = 0; move < 4 ; move++){
				int nx = thisPoint.x + dx[move];
				int ny = thisPoint.y + dy[move];
				if(!isValidPoint(nx, ny) || visited[nx][ny] || maze[nx][ny] == 0)
					continue;
				if(nx == 0 && ny == ROW-1){
					result = Math.min(result, thisPoint.dist + 1);
					continue;
				}
				queue.offer(new MazePoint(nx,ny,thisPoint.dist + 1));
				visited[nx][ny] = true;
			}
		}
	}
	
	private static boolean isValidPoint(int nx, int ny) {
		return nx >= 0 && nx < COLUMN && ny >= 0  && ny < ROW;
	}
	
	private static void createMaze(BufferedReader br) throws IOException {
		String line = br.readLine();
		COLUMN = Integer.parseInt(line.split(" ")[0]);
		ROW = Integer.parseInt(line.split(" ")[1]);
		maze = new int[COLUMN][ROW];
		visited = new boolean[COLUMN][ROW];
		for(int column = COLUMN-1 ; column >= 0 ; column--){
			line = br.readLine();
			for(int row = 0; row < ROW ; row++){
				maze[column][row] = Character.getNumericValue(line.charAt(row));
			}
		}
	}
}

class MazePoint{
	int x;
	int y;
	int dist;
	public MazePoint(int x, int y, int dist){
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}
