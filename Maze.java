package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Maze {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	public static void main(String args[]) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int time = Integer.parseInt(br.readLine());
		int[][] maze = new int[time][time];
		boolean[][] visit = new boolean[time][time];
		int[][] dist = new int[time][time];
		// 미로 만들기
		for(int i = 0 ; i < time ; i++){
			String line = br.readLine();
			for(int j=0; j< time; j++){
				maze[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
				dist[i][j] = 1000;
			}
		}
		PriorityQueue<Point> pq = new PriorityQueue();
		
		dist[0][0] = 0;
		pq.add(new Point(0,0,0));
		while(!pq.isEmpty()){
			Point now = pq.poll();
			if(now.x == time-1 && now.y == time-1) break;
			for(int i = 0 ; i < 4 ; i++){
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(nx >= 0 && nx < time && ny >= 0 && ny < time && !visit[nx][ny]){
					if(maze[nx][ny] == 1){
						if(dist[nx][ny] > dist[now.x][now.y]){
							dist[nx][ny] = dist[now.x][now.y];
						}
					}
					else{
						if(dist[nx][ny] > dist[now.x][now.y] + 1){
							dist[nx][ny] = dist[now.x][now.y] + 1;
						}
					}
					pq.add(new Point(nx,ny, dist[nx][ny]));
					visit[nx][ny] = true;
				}
			}			
		}
		System.out.println(dist[time-1][time-1]);
	}
}

class Point implements Comparable<Point>{
	public int x;
	public int y;
	public int dist;
	
	public Point(int x, int y, int dist){
		this.x = x;
		this.y = y;
		this.dist = dist;
	}

	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		return this.dist - o.dist;
	}

}
