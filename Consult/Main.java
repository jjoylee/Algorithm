package algorithm.Consult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/14501
public class Main {
	static int N;
	static int[] payTable;
	public static void main(String[] args) throws NumberFormatException, IOException{
		createPayTable();
		System.out.println(payTable[N]);
	}
	
	private static void createPayTable() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		payTable = new int[N+1];
		for( int n = 0 ; n < N ; n++){
			String[] line = br.readLine().split(" ");
			int day = Integer.parseInt(line[0]);
			int pay = Integer.parseInt(line[1]);
			payTable[n + 1] = Math.max(payTable[n], payTable[n+1]);
			if(n + day <= N) payTable[n + day] = Math.max(payTable[n + day], payTable[n] + pay);
		}
		br.close();
	}
}