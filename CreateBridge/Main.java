package algorithm.CreateBridge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

//https://www.acmicpc.net/problem/2146
public class Main {
	static int SIZE;
	static int[][] map, bridge;
	static boolean[][] visited;
	static int islandNum = 1;
	static int[] dx = {1,-1,0,0}, dy = {0,0,-1,1};
	static Queue<IslandPoint> islandQueue = new LinkedList();
	
	public static void main (String args[]) throws NumberFormatException, IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//지도 만들기
		createMap(br);
		//섬 구분하기 - 넘버링
		findIsland();
		//섬 확장시키기
		extendIsland();
		//제일 짧은 다리 길이 출력
		printShortestBridge(bw);
	
		bw.flush();
		bw.close();
		br.close();
	}

	private static void printShortestBridge(BufferedWriter bw) throws IOException {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(isValidPoint(nx,ny) && map[nx][ny] != map[i][j]) 
						min = Math.min(min, bridge[nx][ny] + bridge[i][j]);
				}
			}
		}
		bw.write(String.valueOf(min));
	}

	private static void extendIsland() {
		while(!islandQueue.isEmpty()){
			IslandPoint point = islandQueue.poll();
			for(int i = 0; i < 4 ; i++){
				int nx = point.getX() + dx[i];
				int ny = point.getY() + dy[i];
				if(!isValidPoint(nx,ny)) continue;
				if(map[nx][ny] == 0 && !visited[nx][ny]){
					islandQueue.add(new IslandPoint(nx,ny));
					map[nx][ny] = map[point.getX()][point.getY()];
					bridge[nx][ny] = bridge[point.getX()][point.getY()] + 1;
					visited[nx][ny] = true;
				}
			}
		}
	}
	
	private static void findIsland() {
		for(int column = 0 ; column < SIZE ; column++){
			for(int row = 0 ; row < SIZE ; row ++){
				if(map[column][row] == 1 && !visited[column][row]){
					setIsland(column,row);
					islandNum++;
				}
			}
		}
	}
	
	private static void setIsland(int column, int row) {
		map[column][row] = islandNum;
		visited[column][row] = true;
		for(int i = 0 ; i < 4 ; i++){
			int nx = column + dx[i];
			int ny = row + dy[i];
			if(!isValidPoint(nx, ny) || visited[nx][ny]) continue;
			if(map[nx][ny] == 1) setIsland(nx,ny);
		}
	}

	private static boolean isValidPoint(int x, int y) {
		return (x < SIZE && x >= 0 && y < SIZE && y >= 0);
	}
	
	private static void createMap(BufferedReader br) throws IOException {
		SIZE = Integer.parseInt(br.readLine());
		map = new int[SIZE][SIZE];
		bridge = new int[SIZE][SIZE];
		visited = new boolean[SIZE][SIZE];
		for(int column = SIZE - 1 ; column >= 0 ; column--){
			String line = br.readLine().replaceAll(" ", "");
			for(int row = 0 ; row < SIZE ; row++){
				map[column][row] = Character.getNumericValue(line.charAt(row));
				if(map[column][row] == 1 ) islandQueue.add(new IslandPoint(column,row));
			}
		}
	}
}

class IslandPoint{
	private int x;
	private int y;
	
	public IslandPoint(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
