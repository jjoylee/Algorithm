package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Section {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int height;
	static int width;
	static int square[][];
	static int count;
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		height = sc.nextInt();
		width = sc.nextInt();
		int squareCount;
		int time = sc.nextInt();
		List<Integer> squareWidth = new ArrayList<>();
		square = new int[height][width];
		for(int i =0; i < time ;i++){
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			for(int j = y1; j < y2; j++){
				for(int l = x1 ; l < x2; l++){
					square[j][l] = 1;
				}
			}
		}
		
		for(int m= 0 ; m < height; m++){
			for(int n = 0 ; n < width ; n++){
				if(square[m][n] != 1 && square[m][n] != -1){
					square[m][n] = -1;
					count = 1;
					int c= dfs(m,n, square);
					squareWidth.add(c);
				}
			}  
		}
		
		squareWidth.sort(new asc());
		System.out.println(squareWidth.size());
		for(int next : squareWidth){
			System.out.print(next + " ");
		}
	}
	
	public static int dfs(int m , int n, int[][] square){
		for(int i= 0 ;i < 4; i++){
			int ny = m + dy[i];
			int nx = n + dx[i];	
			if(nx>=0 && nx < width && ny >= 0 && ny < height && square[ny][nx] == 0) {
				count++;
				square[ny][nx] = -1;
				dfs(ny,nx,square);
			}
		}
		return count;
	}
}

class asc implements Comparator<Integer>{

	@Override
	public int compare(Integer o1, Integer o2) {
		// TODO Auto-generated method stub
		return o1.compareTo(o2);
	}
	
}
