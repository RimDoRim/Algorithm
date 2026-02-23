def solution(array, n):
    answer = 100
    a = 0
    array.sort()
    for i in range(len(array)):
        if answer > abs(array[i] - n):
            answer = abs(array[i] - n)
            a = array[i]   
    return a
 