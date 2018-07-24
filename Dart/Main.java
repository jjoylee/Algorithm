package algorithm.Dart;

import java.util.Scanner;

//코드그라운드 다트게임
public class Main {
	static int Answer;
	static int[] scores = {6,13,4,18,1,20,5,12,9,14,11,8,16,7,19,3,17,2,15,10,6};
	public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();
		for(int test_case = 0; test_case < T; test_case++) {
			Answer = 0;
			int[] RADIUS = new int[5];
			String line[] = sc.nextLine().split(" ");
			for(int radius = 0 ; radius < 5; radius++){
				RADIUS[radius] = Integer.parseInt(line[radius]);
			}
			int POINT_COUNT = Integer.parseInt(sc.nextLine());
			for(int point = 0 ; point < POINT_COUNT ; point++){
				line = sc.nextLine().split(" ");
				int pointX = Integer.parseInt(line[0]);
				int pointY = Integer.parseInt(line[1]);
				double pointRadius = Math.sqrt(pointX*pointX + pointY*pointY);
				int scoreType = 1;
				if(RADIUS[0] > pointRadius){
					Answer += 50;
					continue;
				}
				if(RADIUS[4] < pointRadius){
					continue;
				}
				if(RADIUS[1] <= pointRadius && RADIUS[2] >= pointRadius)
					scoreType = 3;
				if(RADIUS[3] <= pointRadius && RADIUS[4] >= pointRadius)
					scoreType = 2;
				double pointRadian = Math.toDegrees(Math.atan2(pointY, pointX));
				if(pointRadian < 0) pointRadian += 360;
				int pointScore = scores[(int)(pointRadian + 9)/ 18];
				Answer += pointScore * scoreType;
			}
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}
