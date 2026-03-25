def solution(targets):
    answer = 0
    #꼬리를 기준으로 오름차순, 만약 같다면 머리를 기준으로 추가 정렬
    targets.sort(key = lambda x : (x[1], x[0]))
    #꼬리 데이터를 저장하는 변수(최소값)
    end = -1
    for s, e in targets:
        #머리가 꼬리보다 뒤에 있으면
        if end <= s:
            answer += 1
            end = e
    return answer