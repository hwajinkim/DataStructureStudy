package Tree;

import java.util.Random;

class Tree_13{
    class Node{
        int data;
        Node left;
        Node right;
        int size = 0;
        Node(int data){
            this.data = data;
            this.size = 1;
        }
        void insert(int data){
            if(data <= this.data){ // 이진 검색 트리는 왼쪽에서 찾음.
                if(left == null){ // 왼쪽에 아무 값도 없으면 넣으면 됌.
                    left = new Node(data);
                } else {
                    left.insert(data);// 왼쪽에 값이 있으면 또 한번 내려가야함.
                }
            }else { // 받은 데이터가 현재 노드보다 크면
                if(right == null) { //오른쪽에 아무 값도 없으면 현재 데이터로 노드 만들어서 넣음.
                    right = new Node(data); 
                } else{
                    right.insert(data); //오른쪽에 값이 있으면 현재 데이터를 가지고 또 한번 내려가서 붙여줌.
                }
            }
            //입력이 완료되면
            size++; //루트까지 함수가 지나간 모든 자리에 사이즈를 1개씩 늘려줌.
        }
        int size(){return size;}
        Node find(int data){
            if(data == this.data){
                return this;
            }else if(data < this.data){ //찾는 값이 현재 값보다 작으면 왼쪽에서 찾아야 하므로
                return left != null ? left.find(data) : null; //find 함수 또 호출
            }else if(data > this.data){ //찾는 값이 현재 값보다 크면 오른쪽에서 찾아야 함
                return right != null ? right.find(data) : null;
            }else{ //그렇지 않은 모든 경우는 null 반환
                return null;
            }
        }
        Node getIthNode(int i){
            int leftSize = left == null? 0 : left.size(); //왼쪽의 노드 갯수
            if(i < leftSize){
                return left.getIthNode(i);
            }else if(i == leftSize){
                return this; //나 자신을 반환
            }else {
                return right.getIthNode(i-(leftSize + 1)); // 찾는 값에서 지나온 노드 갯수 만큼 빼줌.
            }
        }
    }

    Node root;
    int size(){
        return root == null ? 0 : root.size();
    }
    void insert(int data){
        if(root == null) root = new Node(data); //루트가 null 이면 노드를 생성
        else root.insert(data); //아니면 아까 만든 함수에 data전달
    }
    Node getRandomNode(){
        if(root == null) return null;
        Random random = new Random();
        int i = random.nextInt(size());
        return root.getIthNode(i);
    }
}
public class Tree13 {
    public static void main(String[] args) {
        Tree_13 tr = new Tree_13();
        tr.insert(4);  
        tr.insert(0);  
        tr.insert(1);  
        tr.insert(2);  
        tr.insert(5);  
        tr.insert(7);  
        tr.insert(8);    
        tr.insert(3);    
        tr.insert(6);    
        tr.insert(9);
        System.out.println(tr.getRandomNode().data);
    }
    
    
    
}
