package algorithm;

import java.util.Scanner;

public class Island {
	static int w;
	static int h;
	static int count;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {0,0,-1,1,-1,1,-1,1};
	static int[] dy = {1,-1,0,0,1,1,-1,-1};
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		while(true){
			w = sc.nextInt();
			h = sc.nextInt();
			if(w == 0 && h == 0) break;
			map = new int[h][w];
			visit = new boolean[h][w];
			for(int i = h-1 ; i >= 0; i--){
				for(int j = 0 ; j < w ; j++){
					map[i][j] = sc.nextInt();
				}
			}
			count = 0;
			for(int i = 0; i < h; i++){
				for(int j = 0 ; j < w ; j++){
					if(map[i][j] == 1 && !visit[i][j]){
						count ++;
						visit[i][j] = true;
						search(i,j);
					}
				}
			}
			System.out.println(count);
		}
	}
	
	private static void search(int i, int j) {
		for(int k = 0 ; k < 8; k++){
			int nx = j + dx[k];
			int ny = i + dy[k];
			if(nx >= 0 && nx < w && ny >= 0 && ny < h && !visit[ny][nx] && map[ny][nx] == 1){
				visit[ny][nx] = true;
				search(ny,nx);
			}
		}
	}
}
