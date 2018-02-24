package algorithm;

import java.util.Scanner;

public class Cheese {
	static int height;
	static int width;
	static int[][] square;
	static boolean[][] visit;
	static boolean[][] isOuter;
	static int count;
	static int time;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		height = sc.nextInt();
		width = sc.nextInt();
		square = new int[height][width];
		visit = new boolean[height][width];
		isOuter = new boolean[height][width];
		time=0;
		//배열 그리기
		for(int i = height - 1; i >= 0 ; i --){
			for(int j = 0 ; j < width ;j++){
				square[i][j] = sc.nextInt();
			}
		}
		
		//dfs
		while(!allResolved()){
			count = 0;
			dfs(0,0);
			time++;
			visit = new boolean[height][width];
		}
		System.out.println(time);
		System.out.println(count);
	}
	
	private static boolean allResolved() {
		int cmt = 0;
		for(int i = 0; i < height ; i++){
			for(int j = 0; j < width ;j++){
				if(square[i][j] == 1) {
					cmt++;
				}
				if(square[i][j] == -1) {
					square[i][j] = 0;
				}
			}
		}
		return cmt == 0;
	}

	public static boolean outer(int x, int y){
		if(square[x][y-1] == -1 || square[x][y+1] == -1 || square[x+1][y] == -1 || square[x-1][y] == -1)
		{
			return true;
		}
		return false;
	}
	
	public static void dfs(int y, int x){
		for(int i = 0 ; i < 4; i++){
			int ny = y + dy[i]; 
			int nx = x + dx[i];
			if(ny >= 0 && ny < height && nx >= 0 && nx < width && !visit[ny][nx]){
				if(square[ny][nx] == 0 ){
					visit[ny][nx] = true;
					square[ny][nx] = -1;
					dfs(ny,nx);
				}
				if(square[ny][nx] == 1 && outer(ny,nx)){
					square[ny][nx] = -1;
					visit[ny][nx] = true;
					count++;
				}
			}
		}
	}
//	public static boolean outer(int x, int y){
//			boolean ul = checkOuter(square[x][y-1],square[x][y],square[x+1][y-1],square[x+1][y]);
//			boolean ur = checkOuter(square[x][y],square[x][y+1],square[x+1][y],square[x+1][y+1]);
//			boolean dl = checkOuter(square[x-1][y-1],square[x-1][y],square[x][y-1],square[x][y]);
//			boolean dr = checkOuter(square[x-1][y],square[x-1][y+1],square[x][y],square[x][y+1]);
//			return (ul || ur || dl || dr);
//	}
//	
//	public static boolean checkOuter(int v1, int v2, int v3, int v4){
//		if(v1+v2+v3+v4 == 1){	
//			return true;
//		}
//		if(v1+v2+v3+v4 ==2){
//			if(v1+v2 ==2 || v3+v4 == 2 || v1+v3 ==2 || v2+v4 ==2){
//				return true;
//			}
//		}
//		return false;
//	}
}
