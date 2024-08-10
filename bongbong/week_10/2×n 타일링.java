/*
https://www.acmicpc.net/problem/11726
2×n 타일링
시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
1 초	256 MB	181497	70417	52291	36.776%
문제
2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.

아래 그림은 2×5 크기의 직사각형을 채운 한 가지 방법의 예이다.



입력
첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)

출력
첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.

예제 입력 1
2
예제 출력 1
2
예제 입력 2
9
예제 출력 2
55
 */

package week_10.동적계획법.백준_2xn타일링;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long D[] = new long[1001];
        D[1]=1; // n=1 일때 타일 채우는 경우의 수
        D[2]=2; // n=2 일떄 타일 채우는 경우의 수

        for(int i = 3;i<=n;i++){
            D[i] = (D[i-1]+D[i-2]) % 10007; // 2(D[n-2]) = D[n]아닌 이유 : 세로 두개로 채워진 네모의 경우 D[n-1] 과 동일하기 때문
        }
        System.out.println(D[n]);
    }
}
