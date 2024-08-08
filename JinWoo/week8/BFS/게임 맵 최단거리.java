import java.util.*;

class Solution {
    static int[][] arr;
    static boolean[][] check;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int answer = 0;
    static int N, M;

    public int solution(int[][] maps) {

        N = maps.length;
        M = maps[0].length;

        arr = new int[N][M];
        check = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = maps[i][j];
            }
        }

        bfs(0, 0);
        if(answer == 0){
            return -1;
        }
        return answer;
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
