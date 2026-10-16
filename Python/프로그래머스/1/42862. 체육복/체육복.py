def solution(n, lost, reserve):
    answer = 0
    std = [[a+1,1] for a in range(n)] # 학생 디폴트 설정
    
    for i in range(0, len(lost)): # lost[i] 에서 나오는 값 -1 해서 그 인덱스 매칭, 그 인덱스에 숫자 1 빼기
        std[lost[i]-1][1] -= 1
    
    for j in range(0, len(reserve)): # reserve, 위와 동일 로직 +1
        std[reserve[j]-1][1] += 1
        
    for k in range(n-1):
        if std[k][1] > 1 and std[k+1][1] < 1: #k번째가 1보다 크고 그 뒤가 도난당하면
            std[k][1] -= 1
            std[k + 1][1] += 1
        elif std[k][1] < 1 and std[k+1][1] > 1: # k번째가 도난이고 그뒤가 여유이면, 어차피 모든 Case 다 됨 
            std[k][1] += 1
            std[k+1][1] -= 1
            
    for l in range(n):
        if std[l][1] >= 1:
            answer += 1
    
    return answer



# def solution(n, lost, reserve):
#     std = [[a+1,1] for a in range(n)]
#     for i in range(1, len(lost)+ 1):
#         std[lost[i]-1][1] -= 1
#     for j in range(1, len(reserve)+ 1):
#         std[reserve[j]-1][1] += 1
#     for k in range(n):
#         if std[k][0] == 0 and std[k][1] < 1 and std[k+1][1] > 1:
#             std[k][1] += 1
#             std[k-1][1] -= 1 #첫번째 사람만
#         elif std[k][1] < 1 and std[k-1][1] > 1:
#             std[k][1] += 1
#             std[k-1][1] -= 1 #두번째사람부터, 내앞사람 서칭
#         elif std[k][1] < 1 and std[k+1][1] > 1:
#             std[k][1] += 1
#             std[k+1][1] -= 1
        
#         answer = 0
#         for ans in range(n):
#             if std[ans][1] >= 1:
#                 answer += 1
            
#     return answer


# # [학생라벨,1] 2차원 배열 만들기
# # search : k th
# # lost -> -1 reserve -> + 1
# # [1] > 1 이면, k+1로 체육복 토스 -> k - 1 로 토스
# # [1] = 0 인 카운트 
# # answer = n - 카운트
# # 여기서 변수 개수 줄이고 Lost reserve 동시 서칭하는 방법과
# # 앞뒤 동시 서칭하는 방법도 고민을 했는데 뭐가더 좋은지 몰라서 일단함