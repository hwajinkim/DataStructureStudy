package Tree;

import java.util.HashMap;

class Tree_10{
    static class Node{
        int data;
        Node left;
        Node right;
        Node parent;
        Node(int data){
            this.data = data;
        }
    }
    Node root;
    HashMap<Integer, Node> rootMap;
    Tree_10(int size){
        rootMap = new HashMap<Integer, Node>();
        root = makeBST(0, size - 1, null);
    }
    Node makeBST(int start, int end, Node parent){
        if(start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid - 1, node);
        node.right = makeBST(mid + 1, end, node);
        node.parent = parent;
        rootMap.put(mid, node);
        return node;
    }
    Node getNode(int data){
        return rootMap.get(data);
    }

    boolean covers(Node root, Node node){ //node가 root의 자손인지 확인
        if(root == null) return false;
        if(root == node) return true;
        return covers(root.left, node) || covers(root.right, node);
    }

    Node commonAncestor(int d1, int d2){
        Node p = getNode(d1);
        Node q = getNode(d2);
        int diff = depth(p) - depth(q); //depth: 루트부터 노드까지의 길이
        Node first = diff > 0? q : p; //짧은 것
        Node second = diff > 0? p : q; //긴 것
        second = goUpBy(second, Math.abs(diff));//긴 쪽을 차이만큼 위로 올라감
        while(first != second && first != null && second != null){
            first = first.parent;
            second = second.parent;
        }
        //두개가 같으면 while 빠져나옴
        return first == null || second == null ? null : first;
    }

    Node goUpBy(Node node, int diff){
        while(diff > 0 && node != null){
            node = node.parent;
            diff--;
        }
        return node;
    }
    int depth(Node node){
        int depth = 0;
        while(node != null){
            node = node.parent;
            depth++;
        }
        
        return depth;
    }

    Node commonAncestor2(int d1, int d2){
        Node p = getNode(d1);
        Node q = getNode(d2);
        if(!covers(root, p) || !covers(root, q)){ //root에서 p,q의 존재여부 체크
            return null;
        } else if(covers(p,q)){ //p가 q의 부모 노드라면 p에서 만나니까 p리턴
            return p;
        } else if(covers(q,p)){ //q가 p의 부모 노드라면 q에서 만나니까 q리턴
            return q;
        }
        Node sibling = getSibling(p); //형제 노드 가져옴.
        Node parent = p.parent;
        while(!covers(sibling, q)){ //sibling에 q가 속해 있지 않는 동안
            sibling = getSibling(parent); // 부모의 형제 노드 가져옴.
            parent = parent.parent; //1번 더 계층 올라감
        }
        return parent; //형제 노드에 q가 속해있으면 공통 부모 이므로 부모값 리턴
    }
    Node getSibling(Node node){
        if(node == null || node.parent == null){
            return null;
        }
        Node parent = node.parent; //현재 나의 부모 노드 가져와서
        // 현재 내가 부모의 왼쪽 노드값과 같으면 오른쪽 노드를
        // 다르면 왼쪽 노드를 리턴
        return parent.left == node? parent.right : parent.left;
    }

    Node commonAncestor3(int d1, int d2){
        Node p = getNode(d1);
        Node q = getNode(d2);
        if(!covers(root, p) || !covers(root, q)){ //p, q가 트리안에 존재X -> null 반환
            return null;
        }
        return ancestorHelper(root, p, q);
    }

    Node ancestorHelper(Node root, Node p, Node q){
        if(root == null || root == p || root == q){
            return root;
        }
        //노드에서 p가 왼쪽 트리에 있는지
        //q가 왼쪽 트리에 있는지 확인해서 반환
        boolean pIsOnLeft = covers(root.left, p); 
        boolean qIsOnLeft = covers(root.left, q); 
        //p나 q가 둘다 같은 쪽에 있으면 같은 방향으로 가면되지만
        //다른쪽에 있으면 root를 반환
        if(qIsOnLeft != pIsOnLeft){ //찾는 값이 갈라짐.
            return root; //return 7
        }
        //p가 왼쪽에 있었으면 왼쪽 자식 노드를 담고 없었으면 오른쪽 자식 노드를 담음.
        Node childSide = pIsOnLeft ? root.left : root.right;
        return ancestorHelper(childSide, p, q); 
    }

    Node commonAncestor4(int d1, int d2){
        Node p = getNode(d1);
        Node q = getNode(d2);
        return commonAncestor4(root, p, q);
    }
    Node commonAncestor4(Node root, Node p, Node q){
        if(root == null) return null;
        if(root == p && root == q) return root;
        //post order 왼쪽 먼저 검색
        Node x = commonAncestor4(root.left, p, q);
        if(x != null && x != p && x!= q){ //공통 부모 찾은 것.
            return x;
        }
        Node y = commonAncestor4(root.right,p, q); 
        if(y != null && y != p && y != q){ //공통 부모 찾은 것
            return y;
        }
        
        if(x != null && y != null){
            return root;
        }else if(root == p || root == q){
            return root;
        }else{
            return x == null ? y : x;
        }
    }
}
public class Tree10 {
    public static void main(String[] args) {
        Tree_10 t = new Tree_10(10);
        Tree_10.Node fa = t.commonAncestor4(2, 8);
        System.out.println("The first common ancestor is "+ fa.data);
    }
}

