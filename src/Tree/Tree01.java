package Tree;

//Binary Tree의 3가지 순회방법 구현하기

//Tree의 노드 생성
class Node{ // 이진 트리 노드 : 데이터, 왼쪽 오른쪽 자식노드를 가짐
    int data;
    Node left;
    Node right;
}

class Tree {
    public Node root; // 트리의 시작점.

    public void setRoot(Node node){
        this.root = node;
    }
    public Node getRoot(){
        return root;
    }
    public Node makeNode(Node left, int data, Node right){
        Node node = new Node();
        node.data = data;
        node.left = left;
        node.right = right;
        return node; 
    }

    public void inorder(Node node){ //left -> root -> right
        if(node != null){ //node가 null이 아닐때까지 재귀호출 반복
            inorder(node.left);
            System.out.print(node.data); //왼쪽 노드를 모두 순회한뒤 자신 출력
            inorder(node.right);
        }
       
    }

    public void preorder(Node node){ // root -> left -> right
        if(node != null){
            System.out.print(node.data); // 자신을 먼저 출력
            preorder(node.left);
            preorder(node.right);
        }
       
    }

    public void postorder(Node node){ // left -> right -> root
        if(node != null){
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.data);
        }
        
    }
}

//      (1)
//      / \
//    (2) (3)
//    / \
//  (4)  (5)
// Inorder   (Left, Root, Right) : 4 2 5 1 3
// Preorder (Root, Left , Right) : 1 2 4 5 3
// Postorder (Left, Right, Root) : 4 5 2 3 1
public class Tree01 {
    public static void main(String[] args) {
        Tree t = new Tree(); // 트리 생성
        Node n4 = t.makeNode(null, 4, null); //leaf 노드부터 생성
        Node n5 = t.makeNode(null, 5, null);
        Node n2 = t.makeNode(n4, 2, n5); // 2번 자식 노드 n4, n5 연결
        Node n3 = t.makeNode(null, 3, null);
        Node n1 = t.makeNode(n2, 1, n3); // 1번 자식 노드 n2, n3 연결
        
        t.setRoot(n1);
        t.inorder(t.getRoot());
        //System.out.println();
        //t.preorder(t.getRoot());
        //System.out.println();
        //t.postorder(t.getRoot());
        //System.out.println();
    }
}
