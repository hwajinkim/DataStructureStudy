package Tree;

class Tree8{
    class Node{
        int data;
        Node left;
        Node right;
        Node parent;
        Node(int data){
            this.data = data;
        }
    }
    Node root;
    Tree8(int size){
        root = makeBST(0, size - 1, null);
    }
    Node makeBST(int start, int end, Node parent){
        if(start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid - 1, node);
        node.right = makeBST(mid + 1, end, node);
        node.parent = parent;
        return node;
    }
    void findNext(Node node){
        if(node.right == null){ //오른쪽 노드가 없는 경우 위에서 찾음.
            System.out.println(findAbove(node.parent, node).data + " is " + node.data + "'s next");
        } else { //오른쪽 자식이 있으면 아래에서 찾음.
            System.out.println(findBelow(node.right).data + " is " + node.data + "'s next");
        }
    }
    Node findAbove(Node root, Node child){ 
        if (root == null) return null;
        if(root.left == child) return root; //부모 노드의 왼쪽 값이 자식값과 일치하면 부모값 리턴
        return findAbove(root.parent, root); //부모의 부모가 어느쪽 자식인지 비교 // 4,1
    }
    Node findBelow(Node root){ // 
        if(root.left == null) return root; //현제 노드의 왼쪽이 없으면 현재 노드
        return findBelow(root.left); // 왼쪽이 있므면 왼쪽 값 다시 재귀 호출
    }
}
public class Tree08 {
    public static void main(String[] args) {
        Tree8 t = new Tree8(10);
        t.findNext(t.root.left.right.right);
        t.findNext(t.root.left);
        t.findNext(t.root);
        t.findNext(t.root.left.left);
        t.findNext(t.root.right.left.right);
    }
}
