package algorithm.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//https://www.acmicpc.net/problem/14502
public class Main {
	static int N, M;
	static int[][] map, copyMap;
	static int[] dx = {1,-1,0,0}, dy = {0,0,-1,1};
	static ArrayList<Point> wallPoint = new ArrayList();
	static Queue<Point> virusQueue = new LinkedList();
	static int wallCount = 3, safetyZone = Integer.MIN_VALUE;
	public static void main(String args[]) throws IOException{
		createMap();
		createWall(0);
		System.out.println(safetyZone);
	}
	
	private static void createWall(int index) {
		if(wallPoint.size() == 3){
			copyMap();
			setWall();
			safetyZone = Math.max(safetyZone,spreadVirus());
		}
		if(wallPoint.size() != 3){
			for(int idx = index; idx < N * M ; idx++){
				int column = idx / M;
				int row = idx % M;
				if(map[column][row] != 0 ) continue;
				wallPoint.add(new Point(column, row));
				createWall(idx+1);
			}
		}
		wallPoint.remove(new Point((index - 1)/ M , (index -1) % M));
	}

	private static int spreadVirus() {
		int safetyCount = N*M - wallCount;
		while(!virusQueue.isEmpty()){
			Point virusPoint = virusQueue.poll();
			safetyCount--;
			for(int move = 0; move < 4 ; move++){
				int nx = virusPoint.x + dx[move];
				int ny = virusPoint.y + dy[move];
				if(!isValidIdx(nx,ny) || copyMap[nx][ny] != 0) continue;
				copyMap[nx][ny] = 2;
				virusQueue.offer(new Point(nx, ny));
			}
		}
		return safetyCount;
	}

	private static boolean isValidIdx(int x, int y) {
		return ( x >= 0 && x < N && y >= 0 && y < M);
	}

	private static void setWall() {
		for(Point wall : wallPoint){
			copyMap[wall.x][wall.y] = 1;
		}
	}

	private static void copyMap() {
		copyMap = new int[N][M];
		for(int n = 0 ; n < N ; n++){
			for(int m = 0 ; m < M ; m++){
				if(map[n][m] == 0) continue;
				copyMap[n][m] = map[n][m];
				if(map[n][m] == 2) virusQueue.offer(new Point(n,m));
			}
		}
	}

	private static void createMap() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new int[N][M];
		for(int n = 0 ; n < N ; n++){
			line = br.readLine().split(" ");
			for(int m = 0 ; m < M ; m++){
				map[n][m] = Integer.parseInt(line[m]);
				if(map[n][m] == 1) wallCount++;
			}
		}
		br.close();
	}
}

class Point{
	int x; 
	int y; 
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	@Override
	public boolean equals(Object obj){
		Point point = (Point)obj;
		return (this.x == point.x && this.y == point.y);
	}
}