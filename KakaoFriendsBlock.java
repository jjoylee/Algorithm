package algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Predicate;

public class KakaoFriendsBlock {
	static int m;
	static int n;
	static int count;
	static boolean[][] visit;
	public static void main(String args[]){
		List<LinkedList<String>>board = new LinkedList();
		m = 6;
		n = 6;
		String boardInf = "[¡°TTTANT¡±, ¡°RRFACC¡±, ¡°RRRFCC¡±, ¡°TRRRAA¡±, ¡°TTMMMF¡±, ¡°TMMTTJ¡±]";
		String[] boardArr = new String[m];
		StringTokenizer st = new StringTokenizer(boardInf, "¡° ¡± , ] [", false);
		int i = m-1;
		while(st.hasMoreTokens()){
			boardArr[i] = st.nextToken();
			i =i-1;
		}
		setBoard(boardArr, board);
		int result = playBoard(board);
		System.out.println(result);
	}

	private static int playBoard(List<LinkedList<String>> board) {
		visit = new boolean[n][m];
		int result = 0;
		while(true){
			count = 0;
			for (boolean[] row: visit){
			    Arrays.fill(row, false);
			}
			for(int i = 0 ; i < board.size() ; i ++){
				for(int j = 0 ; j < board.get(i).size() ; j++){
					checkBoard(board,i,j);
				}
			}
			result += count;
			if(count == 0) break;
			deleteBoard(board);
		}
		return result;
	}
	private static void deleteBoard(List<LinkedList<String>> board) {
		for(int j = 0 ; j < board.size(); j++){
			for(int k = 0 ; k < board.get(j).size() ; k++){
				if(visit[j][k]) board.get(j).set(k, "delete");
			}
		}
		Predicate<String> predicate = p-> p == "delete";
		for(int i = 0 ; i < board.size() ; i++){
			board.get(i).removeIf(predicate);
		}
	}

	private static void checkBoard(List<LinkedList<String>> board, int i, int j) {
		int x; 
		int y;
		String shape = board.get(i).get(j);
		if(i+1 < board.size()&& j+1 < board.get(i).size()&&j+1 < board.get(i+1).size()){
			if(shape.equals(board.get(i).get(j))&&shape.equals(board.get(i+1).get(j))&&shape.equals(board.get(i).get(j+1))&&shape.equals(board.get(i+1).get(j+1))){
				if(!visit[i][j]) {visit[i][j] = true; count++;}
				if(!visit[i+1][j]){visit[i+1][j] = true; count++;}
				if(!visit[i][j+1]){visit[i][j+1] = true; count++;}
				if(!visit[i+1][j+1]){visit[i+1][j+1] = true; count++;}
			}
		}
	}

	private static void setBoard(String[] boardArr, List<LinkedList<String>> board) {
		for(int k = 0; k < n ; k++){
			board.add(new LinkedList<String>());
		}
		for(int i = 0 ; i < boardArr.length ; i++){
			for(int j = 0 ; j < boardArr[i].length() ;j++){
				board.get(j).add(i,Character.toString(boardArr[i].charAt(j)));
			}
		}
	}
}
