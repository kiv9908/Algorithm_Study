import java.util.*;

class Solution {
    
    private static HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        
        for (int i = 0; i < info.length; i++) {
            dfs(0, "", info[i].split(" "));
        }

        //점수를 담은 리스트 오름차순 정렬
        for(ArrayList<Integer> list : map.values()) {
            Collections.sort(list);
        }

        int result[] = new int[query.length];

        
        for(int i = 0; i < query.length; i++) {

            //query를 map의 key와 비교하기 위해 변환하는 과정
            String[] arr = query[i].split(" and ");

            //이분탐색으로 찾을 점수
            int targetScore = 0;
            
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < 4; j++) {
                if (j == 3) {
                    sb.append(arr[j].split(" ")[0]);
                    targetScore = Integer.parseInt(arr[j].split(" ")[1]);
                } else {
                    sb.append(arr[j]);
                }
            }

            String key = sb.toString();

            if (map.containsKey(key)) {
                result[i] = binarySearch(map.get(key), targetScore);
            }
        }
        return result;
    }
    
    //info에 대해 통과 할 수 있는 모든 경우의 수를 key로, 점수를 value로 만들기
    public static void dfs(int depth, String query, String[] info) {
        if (depth == 4) {
            ArrayList<Integer> scores = map.getOrDefault(query, new ArrayList<>());
            scores.add(Integer.parseInt(info[4]));
            map.put(query, scores);
            return;
        }

        dfs(depth + 1, query + "-", info);
        dfs(depth + 1, query + info[depth], info);
    }
    
    //이분탐색 메서드
    public static int binarySearch(ArrayList<Integer> list, int score) {

        int left = 0;
        int right = list.size();

        // score 이상인 값이 처음으로 나타나는 인덱스 찾기(하한선)
        while(left < right) {
            int mid = (left + right) / 2;

            if(list.get(mid) < score) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return list.size() - left;

    }
}
