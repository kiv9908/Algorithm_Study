/*
특정 거리의 도시 찾기 성공

문제
어떤 나라에는 1번부터 N번까지의 도시와 M개의 단방향 도로가 존재한다. 모든 도로의 거리는 1이다.

이 때 특정한 도시 X로부터 출발하여 도달할 수 있는 모든 도시 중에서, 최단 거리가 정확히 K인 모든 도시들의 번호를 출력하는 프로그램을 작성하시오. 또한 출발 도시 X에서 출발 도시 X로 가는 최단 거리는 항상 0이라고 가정한다.

예를 들어 N=4, K=2, X=1일 때 다음과 같이 그래프가 구성되어 있다고 가정하자.


이 때 1번 도시에서 출발하여 도달할 수 있는 도시 중에서, 최단 거리가 2인 도시는 4번 도시 뿐이다.  2번과 3번 도시의 경우, 최단 거리가 1이기 때문에 출력하지 않는다.

입력
첫째 줄에 도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X가 주어진다. (2 ≤ N ≤ 300,000, 1 ≤ M ≤ 1,000,000, 1 ≤ K ≤ 300,000, 1 ≤ X ≤ N) 둘째 줄부터 M개의 줄에 걸쳐서 두 개의 자연수 A, B가 공백을 기준으로 구분되어 주어진다. 이는 A번 도시에서 B번 도시로 이동하는 단방향 도로가 존재한다는 의미다. (1 ≤ A, B ≤ N) 단, A와 B는 서로 다른 자연수이다.

출력
X로부터 출발하여 도달할 수 있는 도시 중에서, 최단 거리가 K인 모든 도시의 번호를 한 줄에 하나씩 오름차순으로 출력한다.

이 때 도달할 수 있는 도시 중에서, 최단 거리가 K인 도시가 하나도 존재하지 않으면 -1을 출력한다.

예제 입력 1
4 4 2 1
1 2
1 3
2 3
2 4
예제 출력 1
4
예제 입력 2
4 3 2 1
1 2
1 3
1 4
예제 출력 2
-1
예제 입력 3
4 4 1 1
1 2
1 3
2 3
2 4
예제 출력 3
2
3
 */

package week_9.다익스트라.특정거리도시찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int visited[];
    static ArrayList<Integer>[] A;
    static int N,M,K,X;
    static List<Integer> answer;

    public static void main(String[] args) throws IOException  {
        /*
         * 도시의 개수 N
         * 도로의 개수 M
         * 거리 정보 K
         * 출발 도시의 번호 X
         * A B 식으로 입력 받음(A,B는 도시)
         * 최단거리가 K인 도시가 없으면 -1 출력
         * 인접리스트 -> BFS 탐색, 방문리스트에 0부터 1씩 증가하게 표시하기
         */

        // 입력 구현
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        A = new ArrayList[N+1];
        answer = new ArrayList<Integer>();

        for(int i = 1 ; i<=N;i++) {
            A[i] = new ArrayList<Integer>();
        }


        for(int i = 0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            A[S].add(E);
        }

        visited = new int[N+1];
        for(int i = 0;i<=N;i++) {
            visited[i]=-1;
        }

        BFS(X);
        for(int i = 0;i<=N;i++) {
            if(visited[i]==K) {
                answer.add(i);
            }
        }
        if(answer.isEmpty()) {
            System.out.println("-1");
        }
        else {
            Collections.sort(answer);
            for(int temp:answer) {
                System.out.println(temp);
            }
        }
    }

    private static void BFS(int Node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(Node);
        // 첫 방문한 노드 방문 표시 -1 + 1 = 0
        visited[Node]++;
        while(!queue.isEmpty()) {
            int now_Node = queue.poll();
            for(int i : A[now_Node]) {
                // 방문한 노드와 연결된 모든 노드에 이전 노드보다 +1 만큼 방문표시
                if(visited[i]==-1) {
                    visited[i]=visited[now_Node]+1;
                    queue.add(i);
                }
            }
        }

    }

}
