package algorithm.ExamSupervision;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//https://www.acmicpc.net/problem/13458
public class Main {
	static int ROOM_COUNT;
	static int[] STUDENT_COUNT;
	static int[] SUPERVISOR = new int[2];
	public static void main(String[] args) throws NumberFormatException, IOException{
		setData();
		System.out.println(countSupervisor());
	}
	
	private static long countSupervisor() {
		long supervisor = ROOM_COUNT;
		for(int room = 0; room < ROOM_COUNT ; room++){
			int students = STUDENT_COUNT[room] - SUPERVISOR[0];
			if(students > 0)
				supervisor += Math.ceil(students/ (double)SUPERVISOR[1]);
		}
		return supervisor;
	}

	private static void setData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ROOM_COUNT = Integer.parseInt(br.readLine());
		setStudent(br);
		setSupervisor(br);
		br.close();
	}

	private static void setSupervisor(BufferedReader br) throws IOException {
		String[] line = br.readLine().split(" ");
		for(int supervisor = 0; supervisor < 2 ; supervisor++){
			SUPERVISOR[supervisor] = Integer.parseInt(line[supervisor]);
		}
	}

	private static void setStudent(BufferedReader br) throws IOException {
		STUDENT_COUNT = new int[ROOM_COUNT];
		String[] line = br.readLine().split(" ");
		for(int room = 0; room < ROOM_COUNT ; room++){
			STUDENT_COUNT[room] = Integer.parseInt(line[room]);
		}
	}
}
