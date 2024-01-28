# python3


def max_sliding_window_naive(sequence, m):
    maximums = []
    max = 0
    max1 = 0
    maxIndex = -1
    maxIndex = -1
    for i in range(len(sequence) - m + 1):
        if sequence[i] > max:
            max = sequence[i]
            maxIndex = i
    for i in range(len(sequence) - m + 1):
        if sequence[i] > max1 and i!=maxIndex:
            max1 = sequence[i]
            maxIndex1 = i

    for i in range(len(sequence) - m + 1):
        if(sequence[m+i]>=max)
            max = sequence[m+i]
            maxIndex = m+i
        if(maxIndex<i)
            maxIndex = max1Index
            max = max1
        maximums.append(max(sequence[i:i + m]))

    return maximums

if __name__ == '__main__':
    n = int(input())
    input_sequence = [int(i) for i in input().split()]
    assert len(input_sequence) == n
    window_size = int(input())

    print(*max_sliding_window_naive(input_sequence, window_size))