l=int(input("Enter number of nodes: "))
w=[[0 for i in range(l)] for j in range(l)]

e=int(input("Enter number of edges:"))
for i in range(e):
  a,b,c=[int(s) for s in input("Enter 2 connected vertices and weight of edge:").split()]
  w[a-1][b-1]=c

for t in w:
  print(t)
  
start=int(input("\nEnter starting vertex:"))
v=[float('inf') for i in range(l)]
v[start]=0
print('Init: ',v)
for i in range(l-1):
  for j in range(l):
    for k in range(l):
      if w[j][k]!=0:
        if v[j]+w[j][k]<v[k]:
          v[k]=v[j]+w[j][k]
  print("Vertex " + str(i)+': ',v)


'''
Example graph input 

7
9
1 2 6
1 3 5
1 4 5
2 5 -1
3 2 -2
4 3 -2
4 6 -1
5 7 3
6 7 3
0
'''