import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static boolean[][] check;
    private static int[][] arr;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        check = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0, 0);
        System.out.println(answer);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        check[x][y] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int currX = now[0];
            int currY = now[1];

            // 미로의 끝에 도달한 경우
            if (currX == N - 1 && currY == M - 1) {
                answer = arr[currX][currY];
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = currX + dx[i];
                int nextY = currY + dy[i];

                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M) { // 유효 범위 체크
                    if (arr[nextX][nextY] == 1 && !check[nextX][nextY]) { // 이동 가능하고 방문하지 않은 경우
                        queue.add(new int[]{nextX, nextY});
                        check[nextX][nextY] = true;
                        arr[nextX][nextY] = arr[currX][currY] + 1; // 거리 업데이트
                    }
                }
            }
        }
    }
}
