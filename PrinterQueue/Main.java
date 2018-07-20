package algorithm.PrinterQueue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1966
public class Main {
	static int TARGET_POS;
	static LinkedList<Integer> priorities = new LinkedList();
	static Queue<Document> docQueue = new LinkedList();
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int TIMES = Integer.parseInt(br.readLine());
		for(int time = 0 ; time < TIMES ; time++){
			setDocumentAndPriority(br);
			pollDocuments(bw);
			clear();
		}
		bw.flush();
		bw.close();
		br.close();
		
	}

	private static void pollDocuments(BufferedWriter bw) throws IOException {
		int count = 0;
		while(true){
			Document thisDoc = docQueue.poll();
			// �켱������ ���� ������ ���� �� �ڿ� �ٽ� �ֱ�
			if(thisDoc.priority != priorities.getLast()){
				docQueue.offer(thisDoc);
				continue;
			}
			// �˸��� ���� ����
			priorities.removeLast();
			count++;
			// ���� ã�� �ִ� ������ ���� ��  
			if(thisDoc.position == TARGET_POS){
				bw.write(String.valueOf(count));
				bw.newLine();
				break;
			}
		}
	}

	private static void clear() {
		priorities.clear();
		docQueue.clear();
	}
	
	private static void setDocumentAndPriority(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int DOC_COUNT = Integer.parseInt(st.nextToken());
		TARGET_POS = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		for(int doc = 0 ; doc < DOC_COUNT ; doc++){
			int priority = Integer.parseInt(st.nextToken());
			docQueue.offer(new Document(priority, doc));
			priorities.add(priority);
		}
		Collections.sort(priorities);
	}
}

class Document{
	int priority;
	int position;
	public Document(int priority, int position){
		this.priority = priority;
		this.position = position;
	}
}