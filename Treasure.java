package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Treasure {
	static char[][] map;
	static List<List<Integer>> dist;
	static boolean[][] visit;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1}; 
	static int height;
	static int width;
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String size[] = br.readLine().split(" ");
		height = Integer.parseInt(size[0]);
		width = Integer.parseInt(size[1]);
		dist = new ArrayList<>();
		visit = new boolean[height][width];
		map = new char[height][width];
		List<Integer> maxDists = new ArrayList();
		// 지도 만들기
		boolean[][] visit = new boolean[height][width];
		for(int i = 0 ; i < height; i++){
			String line = br.readLine();
			dist.add(new ArrayList<Integer>());
			for(int j = 0 ; j < width ; j++){
				map[i][j] = line.charAt(j);
				dist.get(i).add(1000);
			}
		}
		
		for(int i = 0 ; i < height; i++){
			for(int j = 0 ; j < width ; j++){
				if(map[i][j]=='L') {
					reset();
					calcDist(i ,j, maxDists);
				}
			}
		}
		
		Collections.sort(maxDists);
		System.out.println(maxDists.get(maxDists.size()-1));
	}
	
	private static void calcDist(int x, int y, List<Integer> maxDists) {
		PriorityQueue pq = new PriorityQueue<Pt>();
		pq.add(new Pt(x,y,0));
		visit[x][y] = true;
		dist.get(x).set(y, 0);
		int max = 0;
		while(!pq.isEmpty()){
			Pt point = (Pt)pq.poll();
			for(int i =0; i < 4 ; i++){
				int nx = point.x + dx[i];
				int ny = point.y + dy[i];
				if(nx >= 0 && nx < height && ny >=0 && ny < width && !visit[nx][ny]){
					if(map[nx][ny]=='L'){
						if(dist.get(nx).get(ny) >  dist.get(point.x).get(point.y) + 1){
							dist.get(nx).set(ny, dist.get(point.x).get(point.y) + 1);
						}
						pq.add(new Pt(nx,ny, dist.get(nx).get(ny)));
						visit[nx][ny] = true;
						if(max < dist.get(nx).get(ny)) max = dist.get(nx).get(ny);
					}
				}
			}
		}
		maxDists.add(max);
	}

	public static void reset(){
		for(int i = 0 ; i < height; i++){
			for(int j = 0 ; j < width ; j++){
				dist.get(i).set(j, 1000);
				visit[i][j] = false;
			}
		}
	}
}
class Pt implements Comparable<Pt>{
	int x;
	int y;
	int dist;
	public Pt(int x, int y, int dist){
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
	@Override
	public int compareTo(Pt o) {
		// TODO Auto-generated method stub
		return this.dist - o.dist;
	}
	
}