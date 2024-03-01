package Tree;

import java.util.ArrayList;
import java.util.LinkedList;

//이진 트리를 레벨 단위 리스트로 변경
class Tree5{
    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
        }
    }
    Node root;
    Tree5 (int size){
        root = makeBST(0, size - 1); 
    }
    Node makeBST(int start, int end){
        if(start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid - 1);
        node.right = makeBST(mid+1, end);
        return node;
    }
    //방법1. 재귀 호출을 할 때 레벨을 함수의 인자로 받기
    ArrayList<LinkedList<Node>> BSTtoList(){
        ArrayList<LinkedList<Node>> lists = new ArrayList<LinkedList<Node>>();
        BSTtoList(root, lists, 0); //루트노드, 결과받을 리스트, 시작값 - 0
        return lists;
    }

    void BSTtoList(Node root, ArrayList<LinkedList<Node>> lists, int level){
        if(root == null) return;
        LinkedList<Node> list = null; //해당 노드를 받을 리스트 선언
        if(lists.size() == level){ //처음 받은 레벨에는 배열 방이 존재하지 않음.
            list = new LinkedList<Node>();
            lists.add(list);
        } else { //현 레벨의 배열 방에 리스트가 있으면
            list = lists.get(level);//레벨의 배열방에서 리스트 주소 획득
        }
        list.add(root); //리스트에 노드 추가
        //자식 노드들로 함수 다시 호출
        BSTtoList(root.left, lists, level + 1);
        BSTtoList(root.right, lists, level + 1);
    }
    //방법2 - DFS 활용
    ArrayList<LinkedList<Node>> BSTtoList2() {
        ArrayList<LinkedList<Node>> result = new ArrayList<LinkedList<Node>>();
        LinkedList<Node> current = new LinkedList<Node>(); //현재 노드를 담을 linkedlist
        if(root != null){
            current.add(root); //초기값으로 루트 노드를 담음.
        }
        while(current.size() > 0){
            result.add(current); //루트 노드가 담긴 리스트만 담겨있음
            LinkedList<Node> parents = current; //현재 리스트를 부모리스트로 넣음.
            current = new LinkedList<Node>(); //현재 레벨은 새로 시작
            for(Node parent : parents){ //부모 노드들의 모든 노드들을 돌면서
                if(parent.left != null) current.add(parent.left); //부모 노드에 자식이 있으면 현재 레벨의 리스트에 담음.
                if(parent.right != null) current.add(parent.right);
            }
        }
       //자식들이 하나도 남지 않았을때 결과값을 반환
        return result;
    }   
    void printList(ArrayList<LinkedList<Node>> arr){ //결과를 반환할 함수 정의
        for(LinkedList<Node> list : arr){
            for(Node node : list){
                System.out.print(node.data + " ");
            }
            System.out.println();
        }
    }
}
public class Tree05 {
    public static void main(String[] args) {
        Tree5 t = new Tree5(10);
        
        t.printList(t.BSTtoList2());

    }
}
