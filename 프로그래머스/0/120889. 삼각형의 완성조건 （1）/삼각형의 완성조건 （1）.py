def solution(sides):
    answer = 0
    sides = sorted(sides)
    if sides[0] + sides[1] <= sides[2]:
        answer += 2
    elif sides[0] + sides[1] > sides[2]:
        answer += 1
        
    return answer