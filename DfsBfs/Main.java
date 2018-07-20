package algorithm.DfsBfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

//https://www.acmicpc.net/problem/1260
public class Main {
	static int VERTEX; 
	static int[][] graph;
	static int[] visited;
	static StringBuilder result = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int START = createMap(br);
		
		//dfs
		dfs(START);
		printResult(bw);
		bw.newLine();
		
		//reset 
		result = new StringBuilder();
		
		//bfs
		bfs(START);
		printResult(bw);
		
		bw.flush();
		br.close();
		bw.close();
	}

	private static int createMap(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		VERTEX = Integer.parseInt(line[0]);
		int EDGE = Integer.parseInt(line[1]);
		int START = Integer.parseInt(line[2]);
		graph = new int[VERTEX+1][VERTEX+1];
		visited = new int[VERTEX+1];
		for(int edge = 0 ; edge < EDGE ; edge++){
			line = br.readLine().split(" ");
			graph[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = 1;
			graph[Integer.parseInt(line[1])][Integer.parseInt(line[0])] = 1;
		}
		return START;
	}

	private static void printResult(BufferedWriter bw) throws IOException {
		bw.write(String.valueOf(result.toString().replaceAll("\\[", "")
				.replaceAll("\\]", "").replaceAll(",", "")));
	}

	private static void bfs(int start) {
		Queue queue = new LinkedList();
		queue.offer(start);
		result.append(start + " ");
		visited[start]++;
		while(!queue.isEmpty()){
			int thisVertex = (int)queue.poll();
			for(int vertex = 1; vertex < VERTEX+1 ; vertex++){
				if(graph[thisVertex][vertex] == 1 && visited[vertex] == 1){
					queue.offer(vertex);
					result.append(vertex + " ");
					visited[vertex]++;
				}
			}
		}
	}

	private static void dfs(int start) {
		result.append(start + " ");
		visited[start]++;
		for(int vertex = 1; vertex < VERTEX+1 ; vertex++){
			if(graph[start][vertex] == 1 && visited[vertex] == 0){
				dfs(vertex);
			}
		}
	}
}
