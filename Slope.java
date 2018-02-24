package algorithm;

import java.util.Scanner;

public class Slope {
	static int count = 0;
	static int L;
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String[] line;
		line = sc.nextLine().split(" ");
		int N = Integer.parseInt(line[0]);
		L = Integer.parseInt(line[1]);
		int[][] Map = new int[N][N];
		for(int i = 0 ; i < N ;i++){
			line = sc.nextLine().split(" ");
			for(int j =0 ; j < N ;j++){
				Map[i][j] = Integer.parseInt(line[j]);
			}
		}
		check(Map);
		System.out.println(count);
	}
	private static void check(int[][] map) {
		for(int i = 0 ; i <map.length ; i++){
			if(checkHor(i,map)) {
				count++;
			}
			if(checkVer(i,map)) {
				count++;
			}
		}
	}
	private static boolean checkVer(int i, int[][] map) {
		boolean check = true;
		boolean[] useSlope = new boolean[map.length];
		for(int j = 0; j < map.length -1 ; j++){
			if(map[j][i] != map[j+1][i] && Math.abs(map[j][i] - map[j+1][i]) == 1){
				if(map[j][i] > map[j+1][i]){
					if(j+1 > map.length - L) return false;
					for(int k = 0; k < L ; k++){
						if(useSlope[j+1+k]) return false;
						if(map[j+1][i] != map[j+1+k][i]) return false;
					}
					for(int k = 0; k < L ; k++){
						useSlope[j+1+k] = true;
					}
				}
				else{
					if(j+1 - L < 0) return false;
					for(int k = 0; k < L ; k++){
						if(useSlope[j-k]) return false;
						if(map[j][i] != map[j-k][i]) return false;
					}
					for(int k = 0; k < L ; k++){
						useSlope[j-k] = true;
					}
				}
			}
			if(Math.abs(map[j][i] - map[j+1][i]) > 1) return false;
		}
		return true;
	}
	private static boolean checkHor(int i, int[][] map) {
		boolean check = true;
		boolean[] useSlope = new boolean[map.length];
		for(int j = 0; j < map.length -1 ; j++){
			if(map[i][j] != map[i][j+1] && Math.abs(map[i][j] - map[i][j+1]) == 1){
				if(map[i][j] > map[i][j+1]){
					if(j+1 > map.length - L) return false;
					for(int k = 0; k < L ; k++){
						if(useSlope[j+1+k])return false;
						if(map[i][j+1] != map[i][j+1+k]) return false;
					}
					for(int k = 0; k < L ; k++){
						useSlope[j+1+k] = true;
					}
				}
				else{
					if(j+1 - L < 0) return false;
					for(int k = 0; k < L ; k++){
						if(useSlope[j-k])return false; 
						if(map[i][j] != map[i][j-k]) return false;
					}
					for(int k = 0; k < L ; k++){
						useSlope[j-k] = true;
					}
				}
			}
			if(Math.abs(map[i][j] - map[i][j+1]) > 1) return false;
		}
		return true;
	}
}
