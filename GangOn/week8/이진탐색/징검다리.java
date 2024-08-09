import java.util.*;

class Solution {
    
    private static int Distance;
    private static int[] Rocks;
    private static int N;
    
    public int solution(int distance, int[] rocks, int n) {
        
        Distance = distance;
        Rocks = rocks;
        N = n; 
        
        //바위 위치 정렬
        Arrays.sort(rocks);
        
        return binarySearch(0, distance);
    }
    
    
    //이분탐색 메서드
    public static int binarySearch(int left, int right){
        
        int answer = 0;
        
        while(left <= right){
            int mid = (left + right)/2;
            
            //거리의 최솟값이 mid일 때 제거해야하는 바위의 개수가 N 이하이면
            if(removeRockCount(Rocks, mid, Distance) <= N){
                //mid값을 늘려야 제거해야하는 바위의 개수가 늘어남
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;

    }
    
    //mid가 바위 간 최소 거리가 되기 위해 제거해야할 바위의 개수를 리턴하는 메서드
    public static int removeRockCount(int[] rocks, int mid, int distance){

        int before = 0;
        int end = distance;

        int removeCnt = 0;
        for(int i = 0; i < rocks.length; i++){
            //현재 바위와 전 바위 사이의 거리가 mid보다 작으면 바위 제거
            if(rocks[i] - before < mid) {
                removeCnt++;
                continue;
            }
            before = rocks[i];
        }

        //마지막 바위와 도착지점 사이의 거리가 mid보다 작으면 마지막 바위도 제거
        if(end - before < mid) removeCnt++;

        return removeCnt;
    }
}
