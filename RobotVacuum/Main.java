package algorithm.RobotVacuum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//https://www.acmicpc.net/problem/14503
public class Main {
	static int[] dx = {0, -1, 0, 1}; 
	static int[] dy = {-1, 0, 1, 0};
	// ������ ��(0)�� ���¿����� ������ x�� 0�� ���ϰ� y�� -1�� ���־� �Ѵ�.(dx[0], dy[0])
	static int[] directions = {3, 0, 1, 2}; 
	// ��(0)�� ������ ��(3) == directions[0] = 3
	static Robot robot;
	static int[][] map;
	static boolean[][] visited;
	static int count = 1;
	static int N, M;
	public static void main(String[] args) throws IOException{
		createMapAndRobot();
		clean();
		System.out.println(count);
	}

	private static void clean() {
		visited[robot.x][robot.y] = true; // ������ġ û��
		searchToClean(); // ������ û���� ��ġ ã��
	}

	private static void searchToClean() {
		// ���� Ȯ��
		if(checkLeft()){
			 clean();
			 count++;
			 return;
		}
		// �������� ��� û�� || ���� ���� ��  -> �ڰ� ������ Ȯ�� �� ���� �ƴϸ� �̵�
		if(checkBehind()){
			robot.x = robot.x + dx[(robot.direction + 3) % 4];
			robot.y = robot.y + dy[(robot.direction + 3) % 4];
			searchToClean();
		}
	}


	private static boolean checkBehind() {
		int behindX = robot.x + dx[(robot.direction + 3) % 4];
		int behindY = robot.y + dy[(robot.direction + 3) % 4];
		if(isValidIdx(behindX, behindY)) return map[behindX][behindY] == 0;
		return false;
	}


	private static boolean isValidIdx(int x, int y) {
		return (x < N && x >= 0 && y < M && y >= 0);
	}

	private static boolean checkLeft() {
		for(int time = 0 ; time < 4 ; time++){
			int leftX = robot.x + dx[robot.direction];
			int leftY = robot.y + dy[robot.direction];
			robot.direction = directions[robot.direction];
			if(!isValidIdx(leftX, leftY) || visited[leftX][leftY] || map[leftX][leftY] == 1) continue;
			robot.x = leftX;
			robot.y = leftY;
			return true;
		}
		return false;
	}


	private static void createMapAndRobot() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new int[N][M];
		visited = new boolean[N][M];
		line = br.readLine().split(" ");
		robot = new Robot(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]));
		for(int n = 0; n < N ; n++){
			line = br.readLine().split(" ");
			for(int m = 0 ; m < M ; m++){
				map[n][m] = Integer.parseInt(line[m]);
			}
		}
		br.close();
	}
}

class Robot{
	int x; 
	int y;
	int direction;
	public Robot(int x, int y, int direction){
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
}
