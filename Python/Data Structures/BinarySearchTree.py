##Binary Search Tree

class BinarySearchTreeNode:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None
    
    def add_node(self, data):
        if(data == self.data):
            #data already exist
            return
        if(data < self.data):
            #data is less add to left
            if(self.left):
                #if it is not a leaf node
                self.left.add_node(data)
            else:
                self.left = BinarySearchTreeNode(data)
        else:
            #add to right
            if(self.right):
                #if it is not a leaf node
                self.right.add_node(data)
            else:
                self.right =  BinarySearchTreeNode(data)
                
    def search(self, value):
        
        if(self.data == value):
            return True
        
        if(value < self.data):
            if(self.left):
                return self.left.search(value)
            else:
                return False
        if(value > self.data):
            if(self.right):
                return self.right.search(value)
            else:
                return False
            
    def find_min(self):
        if(self.left):
            return self.left.find_min()
        else:
            return self.data
    
    def find_max(self):
        if(self.right):
            return self.right.find_max()
        else:
            return self.data
    
    def in_order_traversal(self):
        elements = []
        # left -> root -> right
        if self.left:
            elements += self.left.in_order_traversal()
        
        elements.append(self.data)
        
        if self.right:
            elements += self.right.in_order_traversal()
            
        return elements
    
    def calculate_sum(self):
        sum = self.data
        if(self.left):
            sum += self.left.calculate_sum()
        if(self.right):
            sum += self.right.calculate_sum()
        return sum
    
    '''def sum_of_two_pairs(self, aSum, aSet):
        if((aSum - self.data) in aSet):
            return ("Pair found: " + str(self.data) +"," + str(aSum - self.data))
        else:
            aSet.append(self.data)
        if(self.right):
            return self.right.sum_of_two_pairs(aSum, aSet)
        if(self.left):
            return self.left.sum_of_two_pairs(aSum, aSet)        
        return "Pair Not Found" '''
        
def build_tree(lists):
    tree = BinarySearchTreeNode(lists[0])
    for i in range(1, len(lists)):
        tree.add_node(lists[i])
    return tree

if __name__ == "__main__":
    lists = [14, 88, 12, 7, 27, 23, 20, 15]
    tree = build_tree(lists)
    print("Inorder Travarsal = ", tree.in_order_traversal())