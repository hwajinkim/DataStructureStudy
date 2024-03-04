package Tree;

class Tree_12{
    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
        }
    }
    Node root;
    Node makeBST(int start, int end){
        if(start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid - 1);
        node.right = makeBST(mid + 1, end);
        return node;
    }
    boolean containsTree(Node t1, Node t2){ // t2가 t1에 포함되는지 확인
       if(t2 == null) return true;
       return subTree(t1, t2); 
    }
    //t1을 preorder 순회를 하면서 t2의 루트값과 같은지 확인.
    boolean subTree(Node t1, Node t2){
        if(t1 == null){ //일치하는 값X
            return false;
            //t1의 값과 t2의 값이 일치하면 그 해당 노드를 기준으로 두개의 트리가 일치하는지
            // 비교
        }else if(t1.data == t2.data && matchTree(t1,t2)){
            return true;
        }
        //t1의 값과 t2의 값이 일치하지 않으면 계속해서 반복해서 돌면서 같은 값 나올때
        //까지 재귀 호출
        return subTree(t1.left, t2) || subTree(t1.right, t2);
    }
    boolean matchTree(Node t1, Node t2){ //두개의 트리를 똑같이 순회하면서 값이 일치하는지 확인
        if(t1 == null && t2 == null){// 둘다 널이면 같은 값이 하나도 없다는 뜻
            return true;
        }else if(t1 == null || t2 == null){ //둘중 하나가 널이면 값이 같지않음
            return false;
        }else if(t1.data != t2.data){
            return false;
        }else {
            return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right);
        }

    }
}
public class Tree12 {
    public static void main(String[] args) {
        Tree_12 t1 = new Tree_12();
        Tree_12 t2 = new Tree_12();
        boolean result;

        t1.root = t1.makeBST(0, 9);
        t2.root = t2.makeBST(5, 9);
        result = t1.containsTree(t1.root, t2.root);
        System.out.println(result);

        t2.root = t2.makeBST(7, 9);
        result = t1.containsTree(t1.root, t2.root);
        System.out.println(result);
    }
}
