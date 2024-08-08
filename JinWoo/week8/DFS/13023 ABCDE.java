import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int [][] arr;
    static boolean [] check;
    static int N,M;

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1 ][N + 1];
        check = new boolean[N + 1];

        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            arr[A][B] = arr[B][A] = 1;
        }

        int count = 0;

        for(int i = 1; i <= N; i++){
            if(!check[i]){
                dfs(i);
                count++;
            }
        }
        System.out.println(count);
    }

    public static void dfs(int node) {
        check[node] = true;

        for(int i = 1; i <= N; i++){
            if(arr[node][i] == 1 && !check[i]){
                dfs(i);
            }
        }
    }
}

//인접 행렬 시간초과코드 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static boolean[] check;
    private static int[][] arr;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N]; // 배열 크기 초기화
        check = new boolean[N]; // 체크 배열 초기화

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            arr[A][B] = arr[B][A] = 1;
        }

        for (int i = 0; i < N; i++) {
            if (answer == 0) {
                dfs(i, 1);
            }
        }

        System.out.println(answer);
    }

    public static void dfs(int start, int depth) {
        if (depth == 5) {
            answer = 1;
            return;
        }

        check[start] = true;
        for (int i = 0; i < N; i++) { // 모든 노드를 확인
            if (arr[start][i] == 1 && !check[i]) { // 인접하고 방문하지 않은 노드
                dfs(i, depth + 1);
            }
        }
        check[start] = false;
    }
}

