# FIFO = first in, first out


class Queue:

    def __init__(self):
        self.queue = []
        self.len_queue = 0

    def push(self, element):
        self.queue.append(element)
        self.len_queue += 1

    def pop(self):
        if not self.empty():
            self.queue.pop(0)
            self.len_queue -= 1

    def empty(self):
        if self.len_queue == 0:
            return True
        return False

    def lenght(self):
        return self.len_queue

    def front(self):
        if not self.empty():
            return self.queue[0]
        return 'Fila Vazia!'


queue1 = Queue()

queue1.push(10)
queue1.push(2)
queue1.push(33)
queue1.push(42)
queue1.push(51)

print('Queue 1')

for i in range(0, 5, 1):
    print('==================================================')
    print('Last element in the queue: {}'.format(queue1.front()))
    print('Queue Size: {}'.format(queue1.lenght()))
    queue1.pop()

