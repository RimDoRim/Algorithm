import heapq

def solution(s, K):
    answer = 0
    
    heapq.heapify(s)
    
    while s[0] < K:
        f = heapq.heappop(s)
        se = heapq.heappop(s)
        t = f + se * 2
        heapq.heappush(s, t)
        answer += 1
        if len(s) == 1 and s[0] < K:
            return -1

    return answer