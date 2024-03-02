package Tree;
// Tree의 Balance 확인
class Tree6{
    class Node{
        int data;
        Node left;
        Node right;
        Node (int data){
            this.data = data;
        }
    }
    Node root;
    Tree6(int size){ //이진 트리 생성
        root = makeBST(0, size - 1);
        root.right.right.right.right = new Node(10);
        root.right.right.left = new Node(11);
    }
    Node makeBST(int start, int end){
        if(start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid - 1);
        node.right = makeBST(mid+1, end);
        return node;
    }
    //방법1
    boolean isBalanced(Node root){
        if(root == null) return true;
        //getHeight() : 서브트리의 줄기의 길이를 잼 두개 길이 차이를 반환
        int heightDiff = getHeight(root.left) - getHeight(root.right);
        if(Math.abs(heightDiff) > 1){
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }
    int getHeight(Node root){
        if(root == null) return -1; //노드가 마지막 노드를 지났으면 카운팅 된 갯수에서 하나뺌
        // 왼쪽 자식노드, 오른쪽 자식노드 중 줄기가 더 긴 노드를 선택하고 거기에 1을 더해서 반환
        return Math.max(getHeight(root.left), getHeight(root.right))+1;

    }
    //방법2
    int checkHeight(Node root){
        if(root == null) return -1;
        int leftHeight = checkHeight(root.left); //왼쪽 노드를 돌면서 서브트리 길이잼
        if(leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int rightHeight = checkHeight(root.right);
        if(leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int heightDiff = leftHeight - rightHeight;
        if(Math.abs(heightDiff) > 1){
            return Integer.MIN_VALUE;
        }else{
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
    boolean isBalanced2(Node root){
        return checkHeight(root) != Integer.MIN_VALUE;
    }
    
    class Level {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
    }
    boolean isBalanced3(Node root){
        Level obj = new Level();
        checkBalanced(root, obj, 0);
        if(Math.abs(obj.min - obj.max)>1) return false; //가장 긴노드의 길이와 가장 작은 노드의 길이가 1이상 차이 나면 unbalance
        else return true;
    }
    void checkBalanced(Node node, Level obj, int level){
        //모든 서브트리의 젤 마지막에서 obj 정보 업데이트
        //들어가면서 level을 하나씩 증가하면 길이는 맨 마지막 노드에서 나옴
        //중간에는 비교할 필요 X
        if(node == null){
            if(level < obj.min) obj.min = level;
            else if(level > obj.max) obj.max = level;
            return;
        }
        checkBalanced(node.left, obj, level+1);
        checkBalanced(node.right, obj, level+1);
    }
}
public class Tree06 {
    public static void main(String[] args) {
        Tree6 t = new Tree6(10);
        System.out.println(t.isBalanced(t.root));
        System.out.println(t.isBalanced2(t.root));
        System.out.println(t.isBalanced3(t.root));
    }
}
