package algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class Sheep {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visit; 
	static int R;
	static int C;
	static int[] subResult;
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String[] size = sc.nextLine().split(" ");
		R = Integer.parseInt(size[0]);
		C = Integer.parseInt(size[1]);
		int sCount = 0;
		int wCount = 0;
		visit = new boolean[R][C];
		subResult = new int[2];
		String[][] map = new String[R][C];
		for(int i = 0 ; i < R ;i++){
			String line = sc.nextLine();
			for(int j = 0 ; j < C; j++)
			{	
				map[i][j] = Character.toString(line.charAt(j));
			}
		}
		
		for(int i =0 ; i < R; i++){
			for(int j = 0 ; j < C ; j++){
				if(!visit[i][j] && !map[i][j].equals("#")){
					visit[i][j] = true;
					if(map[i][j].equals("o")) {
						subResult[0]++;
					}
					if(map[i][j].equals("v")) {
						subResult[1]++;
					}
					bfs(map,i,j);
				}
				if(subResult[0] <= subResult[1]){
					wCount += subResult[1];
				}else{
					sCount += subResult[0];
				}
				Arrays.fill(subResult, 0);
			}
		}
		System.out.println(sCount + " " + wCount);
	}
	private static void bfs(String[][] map, int x, int y){
		for(int i = 0 ; i < 4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx >= 0 && nx < R && ny >=0 && ny < C && !visit[nx][ny]){
				if(map[nx][ny].equals("o")){
					subResult[0]++;
					visit[nx][ny] = true;
					bfs(map,nx,ny);
				}
				if(map[nx][ny].equals("v")){
					subResult[1]++;
					visit[nx][ny] = true;
					bfs(map,nx,ny);
				}
				if(map[nx][ny].equals(".")){
					visit[nx][ny] = true;
					bfs(map,nx,ny);
				}
			}
		}
	}
}
