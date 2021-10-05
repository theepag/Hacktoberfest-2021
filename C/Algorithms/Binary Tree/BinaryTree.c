#include <stdio.h>
#include <stdlib.h>

typedef struct sNode{
	int info;
	struct sNode *esq;
	struct sNode *dir;
}Node;

Node *getNode(){
	return (Node*)malloc(sizeof(Node));
}

void freeNode(Node *ptrArvore){
	free(ptrArvore);
}

int isEmpty(Node *ptrArvore){
	if(ptrArvore == NULL){
		return 1;
	}else{
		return 0;
	}
}

void init(Node **ptrArvore){
	*ptrArvore = NULL;
}

Node* search(Node *ptrRaiz, int x){
	Node *aux;
	aux = ptrRaiz;
	if(aux == NULL){
		printf("\n ERROR: Value not found in Tree!");
		return NULL;
	}else{
		if(x < aux->info){
			return search(aux->esq, x);
		}else{
			if(x > aux->info){
				return search(aux->dir, x);
			}else{
				return aux;
			}
		}
	}
}

Node *largestRight(Node **n1) { 
    if((*n1)->dir != NULL){
    	return largestRight(&(*n1)->dir);
	}else{
        Node *aux = *n1;
        if((*n1)->esq != NULL){
            *n1 = (*n1)->esq;
		}else{
            *n1 = NULL;
            return aux;
        }
    }
    return *n1;
}

Node *lowestLeft(Node **n1) {
    if((*n1)->esq != NULL){
        return lowestLeft(&(*n1)->esq);
	}else{
        Node *aux;
		aux = *n1;
        if((*n1)->dir != NULL){
            *n1 = (*n1)->dir;
		}else{
			*n1 = NULL;
		}
        return aux;
    }
}

void pushR(Node **ptrRaiz, int x) {
    if(*ptrRaiz == NULL){
        (*ptrRaiz)->esq = NULL;
        (*ptrRaiz)->dir = NULL;
        (*ptrRaiz)->info = x;
    }else{
        if(x < ((*ptrRaiz)->info)){
            pushR(&((*ptrRaiz)->esq), x);
    	}else{
            pushR(&((*ptrRaiz)->dir), x);
    	}
	}
}

void push(Node **ptrRaiz, int x){
	Node *aux;
	Node *aux2;
	Node *q;
	
	aux = *ptrRaiz;
	aux2 = NULL;
	q = getNode();
	
	if(q != NULL){
		q->info = x;
		q->dir = NULL;
		q->esq = NULL;
		if(isEmpty(*ptrRaiz) == 1){
			*ptrRaiz = q;
			printf("\n First Tree Member!");
		}else{
			while(aux != NULL){
				aux2 = aux;
				if(x < aux2->info){
					aux = aux->esq;
				}else{
					aux = aux->dir;
				}
			}
			if(x < aux2->info){
				aux2->esq = q;
			}else{
				aux2->dir = q;
			}
			printf("\n Value placed in the tree!");
		}
	}else{
		printf("\nERROR: Not allocated! ");
	}
}

void pop(Node **ptrRaiz, int x){
	if(isEmpty(*ptrRaiz) == 1){
		printf("\n ERROR: empty tree!");
	}else{
		Node *aux;
		aux = *ptrRaiz;
		if(x < aux->info){
			pop(&aux->esq, x);
		}
		if(x > aux->info){
			pop(&aux->dir, x);
		}else{
			if((*ptrRaiz)->esq == NULL && (*ptrRaiz)->dir == NULL){
				// caso o n� n�o tenha sub-n�s...
				freeNode(aux);
				(*ptrRaiz) == NULL;
			}else{
				if((*ptrRaiz)->esq == NULL) {
					// caso ainda tenha um n� direito..
                    (*ptrRaiz) = (*ptrRaiz)->dir;
                    aux->dir = NULL;
                    free(aux);
                    aux = NULL;
				}else{
                	if((*ptrRaiz)->dir == NULL){
                		// caso ainda tenha um n� esquerdo
	                    (*ptrRaiz) = (*ptrRaiz)->esq;
	                    aux->esq = NULL;
	                    free(aux);
	                    aux = NULL;
                    }else{ 
	                    aux = largestRight(&(*ptrRaiz)->esq); 
	                    aux->esq = (*ptrRaiz)->esq;
	                    aux->dir = (*ptrRaiz)->dir;
	                    (*ptrRaiz)->esq = (*ptrRaiz)->dir = NULL;
	                    free((*ptrRaiz));
	                    *ptrRaiz = aux;
	                    aux = NULL;
                    } 				
				}         
			}	
		}
		printf("\n Value removed from tree!");
	}
}

void biggestValue(Node* ptrRaiz) {
    if((ptrRaiz->dir != NULL) && (ptrRaiz->dir->info > ptrRaiz->info)){
        biggestValue(ptrRaiz->dir);
    }else{
        printf("\nHighest value: %d\n", ptrRaiz->info);    	
	}
}

void smallestValue(Node* ptrRaiz) {
    if((ptrRaiz->esq != NULL) && (ptrRaiz->esq->info < ptrRaiz->info)){
        smallestValue(ptrRaiz->esq);
    }else{
        printf("\nLower value: %d\n", ptrRaiz->info);
	}
}

void printTrace(int level) {
	int i;
    for(i = 0; i < level; i++){
		printf("-");
	}
}

void print(Node* ptrRaiz, int level) {
    if(ptrRaiz == NULL) {
        printTrace(level);
        printf("*\n");
    }else{
	    print(ptrRaiz->dir, level+1);
	    printTrace(level);
	    printf("%d\n", ptrRaiz->info);
	    print(ptrRaiz->esq, level+1);
	}
}

void visit(Node* ptrRaiz) {
    printf("%d\n",ptrRaiz->info);
}

void showInOrder(Node *ptrRaiz) { 
    if(ptrRaiz != NULL) {
        showInOrder(ptrRaiz->esq);
        visit(ptrRaiz);
        showInOrder(ptrRaiz->dir);
    }
}

void showPreOrder(Node *ptrRaiz) { 
    if(ptrRaiz != NULL) {
        visit(ptrRaiz);
        showPreOrder(ptrRaiz->esq);
        showPreOrder(ptrRaiz->dir);
    }
}

void showPosOrder(Node *ptrRaiz) { 
    if(ptrRaiz != NULL) {
        showPosOrder(ptrRaiz->esq);
        showPosOrder(ptrRaiz->dir);
        visit(ptrRaiz);
    }
}

int countNodes(Node *ptrRaiz) { 
    if(ptrRaiz == NULL){
        return 0;
	}else{
		return 1 + countNodes(ptrRaiz->esq) + countNodes(ptrRaiz->dir);
	}
}

int countLeafs(Node *ptrRaiz) {
    if(ptrRaiz == NULL){
        return 0;  	
	}
    if(ptrRaiz->esq == NULL && ptrRaiz->dir == NULL){
    	return 1;
	}
    return countLeafs(ptrRaiz->esq) + countLeafs(ptrRaiz->dir);
}

int compare(int a, int b) {
    if(a > b){
        return a;    	
	}else{
        return b;
	}
}

int heightTree(Node *ptrRaiz){
    if((ptrRaiz == NULL) || (ptrRaiz->esq == NULL && ptrRaiz->dir == NULL)){
        return 0;    	
	}else{
        return 1 + compare(heightTree(ptrRaiz->esq), heightTree(ptrRaiz->dir));		
	}
}

void menu(){
	Node *ptrArvore;
	init(&ptrArvore);
	int input, chooseMenu, loopMenu = 1;
	while (loopMenu == 1){
		printf("\n==============================================================================");
		printf("\n				  MENU 							");
		printf("\n 1) Insert value into Tree.......");
		printf("\n 2) Insert value into Tree with recursion.......");
		printf("\n 3) Remove Value from Tree.......");
		printf("\n 4) display in pre-order.......");		
		printf("\n 5) display in order.......");		
		printf("\n 6) display in post-order.......");		
		printf("\n 7) Count Tree Leaves.......");		
		printf("\n 8) Tell Us of the Tree.......");		
		printf("\n 9) Shows the height of the tree.......");		
		printf("\n 10) Procura elemento na Arvore.......");
		printf("\n 11) Search for element in the Tree.......");				
		printf("\n 12) Highest value of the tree.......");				
		printf("\n 13) Lowest tree value.......");				
		printf("\n 14) To go out.......");
		printf("\n==============================================================================");
		printf("\nEnter the Value for the Transaction: ");
		scanf("%d", &chooseMenu);
		switch(chooseMenu){
			case 1:
				printf("\nInsert value into Tree: ");
				scanf("%d", &input);
				push(&ptrArvore, input);
				break;
			case 2:
				printf("\nInsert value into Tree: ");
				scanf("%d", &input);
				pushR(&ptrArvore, input);
				break;
			case 3:				
				printf("\nRemove value from Tree: ");
				scanf("%d", &input);
				pop(&ptrArvore, input);
				break;		
			case 4:
				showPreOrder(ptrArvore);
				break;
			case 5:
				showInOrder(ptrArvore);
				break;
			case 6:
				showPosOrder(ptrArvore);
				break;
			case 7:
				printf("\nNumber of leafs: %d", countLeafs(ptrArvore));
				break;
			case 8:
				printf("\nNumber of Nodes: %d", countNodes(ptrArvore));
				break;
			case 9:
				printf("\ntree height: %d", heightTree(ptrArvore));
				break;
			case 10:
				printf("\n Value to be sought: ");
				scanf("%d", &input);
				if(search(ptrArvore, input) != NULL){
					printf("\nElement %d found", input);
				}else{
					printf("\nElement %d not found", input);
				}
				break;
			case 11:
				print(ptrArvore, 0);
				break;
			case 12:
				biggestValue(ptrArvore);
				break;
			case 13:
				smallestValue(ptrArvore);
				break;
			case 14:							
				printf("Ending the Menu Loop!");
				loopMenu = 0;
				break;	
			default:
				printf("Invalid option!");
		}	
	}
}

int main(){
	menu();
	return 0;
}
