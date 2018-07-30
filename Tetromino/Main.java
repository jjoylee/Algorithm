package algorithm.Tetromino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//https://www.acmicpc.net/problem/14500
public class Main {
	static int N, M, maxSum = Integer.MIN_VALUE;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		createMap();
		checkTetromino();
		System.out.println(maxSum);
	}
	
	private static void checkTetromino() {
		for(int n = 0 ; n < N ; n++){
			for(int m = 0 ; m < M ; m++){
				visited[n][m] = true;
				makeTetromino(n,m,1, map[n][m]);
				checkException(n,m);
				visited[n][m] = false;
			}
		}
	}

	private static void checkException(int x, int y){
		if(y > 0 && x > 0 && x < N -1) 
			maxSum = Math.max(maxSum, map[x][y-1] + map[x+1][y] + map[x-1][y] + map[x][y]);	
		if(y < M - 1 && x > 0 && x < N -1) 
			maxSum = Math.max(maxSum, map[x][y+1] + map[x+1][y] + map[x-1][y] + map[x][y]);
		if(y > 0 && y < M-1 && x < N - 1)
			maxSum = Math.max(maxSum, map[x][y+1] + map[x][y-1] + map[x+1][y] + map[x][y]);
		if(y > 0 && y < M-1 && x > 0)
			maxSum = Math.max(maxSum, map[x][y+1] + map[x][y-1] + map[x-1][y] + map[x][y]);	
	}


	private static void makeTetromino(int x, int y, int count, int sum) {
		if(count == 4){
			maxSum = Math.max(maxSum, sum);
		}
		if(count != 4){
			for(int move = 0 ; move < 4; move++){
				int nx = x + dx[move];
				int ny = y + dy[move];
				if(!isValidIdx(nx,ny) || visited[nx][ny]) continue;
				visited[nx][ny] = true;
				makeTetromino(nx, ny, count + 1, sum + map[nx][ny]);
				visited[nx][ny] = false;
			}
		}
	}

	private static boolean isValidIdx(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < M);
	}

	private static void createMap() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new int[N][M];
		visited = new boolean[N][M];
		for(int n = 0 ; n < N; n++){
			line = br.readLine().split(" ");
			for(int m = 0 ; m < M ; m++){
				map[n][m] = Integer.parseInt(line[m]);
			}
		}
		br.close();
	}
}
