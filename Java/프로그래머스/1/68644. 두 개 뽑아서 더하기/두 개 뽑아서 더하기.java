import java.util.*;
        // 배열의 인덱스 중 임의의 2개를 뽑음
        // 두개를 더해서 int[] 에 넣음, 중복 허용하면안됨
        // 그것을 정렬
class Solution {
    public int[] solution(int[] numbers) {
        Set<Integer> set = new HashSet<>();
        
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }
        
        int[] answer = set.stream()
                          .mapToInt(Integer::intValue)
                          .sorted()
                          .toArray();
        
        return answer;
    }
}