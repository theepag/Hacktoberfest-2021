# deck ou double-ended queue, uma fila de duas pontas....
class Deque:
    def __init__(self):
        self.len = 0
        self.deque = []

    def empty(self):
        if self.len == 0:
            return True
        return False

    def push_front(self, element):
        self.deque.insert(0, element)
        self.len += 1

    def push_back(self, element):
        self.deque.insert(self.len, element)
        self.len += 1

    def pop_front(self):
        if not self.empty():
            self.deque.pop(0)
            self.len -= 1

    def pop_back(self):
        if not self.empty():
            self.deque.pop(self.len - 1)
            self.len -= 1

    def front(self):
        if not self.empty():
            return self.deque[0]

    def back(self):
        if not self.empty():
            return self.deque[-1]

    def show(self):
        for i in self.deque:
            print(i, end=' | ')


deck1 = Deque()

deck1.push_front(1)
deck1.push_front(3)
deck1.push_front(5)
deck1.push_back(2)
deck1.push_back(4)
deck1.push_back(6)

print(deck1.show())

for i in range(0, 4, 1):
    print('==================================================')
    print('Start Value: {}'.format(deck1.front()))
    print('End Value: {}'.format(deck1.back()))
    deck1.pop_front()
    deck1.pop_back()
