import heapq # 오름차순 만 존재
def solution(n, works):
    answer = 0
    if sum(works) <= n:
        return 0
    works = [-i for i in works]
    
    heapq.heapify(works)
    
    # 남은 일을 남은 시간으로 가장 큰 값들만 골라서 차감
    for i in range(n):
        t = heapq.heappop(works)
        t += 1
        heapq.heappush(works, t)
    
    for i in works:
        answer += i ** 2
    return answer