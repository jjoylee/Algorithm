package algorithm.KevinBacon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int M;
	static int[][] adjGraph;
	static boolean[][] visited;
	static int kevinCount = 1;
	public static void main(String[] args) throws IOException{
		createAdjGraph();
		calcKevinBacon();
		for(int n = 1; n < N+1 ; n++){
			for(int m = 1 ; m < M+1; m++){
				System.out.print(adjGraph[n][m] + " ");
			}
			System.out.println();
		}
	}
	
	private static void calcKevinBacon() {
		for(int n = 1; n < N+1 ; n++){
			for(int m = 1 ; m < M+1; m++){
				if(adjGraph[n][m] != 1 || visited[n][m]) continue;
				search(n,m, 2, n);
			}
		}
	}
	
	private static void search(int x, int y, int depth, int target) {	
		for(int m = 1 ; m < M+1 ; m++){
			if(visited[y][m]) continue;
			if(adjGraph[y][m] != 1 || m == x) continue;
			if(adjGraph[target][m] != 1){
				adjGraph[target][m] = depth;
				visited[x][y] = true;
				search(y,m,depth+1,target);
				visited[x][y] = false;
			}
		}
	}

	private static void createAdjGraph() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		adjGraph = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
		for(int m = 0 ; m < M ; m++){
			line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			adjGraph[x][y] = adjGraph[y][x] = 1;
		}
		br.close();
	}
}
