package algorithm.Josephus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	static int COUNT;
	static int ORDER;
	static ArrayList<Integer> circle = new ArrayList();
	static ArrayList<Integer> result = new ArrayList();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		setCircle(br);
		removePeople();
		printResult(bw);
		bw.flush();
		bw.close();
		br.close();
	}
	private static void printResult(BufferedWriter bw) throws IOException {
		bw.write("<" );
		for(int count = 0; count < COUNT ; count++){
			if(count == COUNT-1){
				bw.write(result.get(count) + ">");
				break;
			}
			bw.write(result.get(count) + ", ");
		}
	}
	
	private static void removePeople() {
		ORDER = ORDER - 1 ; // FOR INDEX
		int index = ORDER; 
		while(circle.size() != 0){
			result.add(circle.get(index));
			circle.remove(index);
			if(circle.size() != 0) index = (index + ORDER) % circle.size();
		}
	}
	
	private static void setCircle(BufferedReader br) throws IOException {
		String[] input = br.readLine().split(" ");
		COUNT = Integer.parseInt(input[0]);
		ORDER = Integer.parseInt(input[1]);
		for(int count = 1 ; count < COUNT+1 ; count++){
			circle.add(count);
		}
	}
}
