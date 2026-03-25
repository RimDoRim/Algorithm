def solution(routes):
    answer = 0
    routes.sort(key = lambda x : (x[1],x[0]))
    end = -30000
    for s,e in routes :
        if end < s:
            answer += 1
            end = e
    return answer