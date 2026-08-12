class Solution {
    public int solution(String s) {
        int answer = s.length(); // 압축 안 했을 때가 최댓값

        for (int sl = 1; sl <= s.length() / 2; sl++) {
            StringBuilder sb = new StringBuilder();
            String prev = s.substring(0, sl);
            int count = 1;

            for (int i = sl; i < s.length(); i += sl) {
                String cur = s.substring(i, Math.min(i + sl, s.length()));
                if (cur.equals(prev)) {
                    count++;
                } else {
                    sb.append(count > 1 ? count : "").append(prev);
                    prev = cur;
                    count = 1;
                }
            }
            sb.append(count > 1 ? count : "").append(prev);
            answer = Math.min(answer, sb.length());
        }
        return answer;
    }

}