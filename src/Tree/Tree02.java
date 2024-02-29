package Tree;

import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

class Graph{
    class Node{
        int data;
        LinkedList<Node> adjacent; //인접한 노드가 무엇인지(자식개념X)
        boolean marked; //방문했는지 마크
        Node(int data){ //data를 받고 marked 플래그 false로 초기화, linkedlist 준비
            this.data = data;
            this.marked = false;
            adjacent = new LinkedList<Node>();
        }
    }
    Node[] nodes; //노드들을 저장할 배열
    Graph(int size){ //그래프의 노드 갯수는 고정
        nodes = new Node[size]; //노드 갯수를 받아서 그 갯수만큼 배열 방 생성
        for(int i=0; i< size; i++){
            nodes[i] = new Node(i); //편의를 위해 데이터 배열방 범위에 숫자를 넘. 
        }
    }
    void addEdge(int i1, int i2){ //두 노드의 관계를 정의하는 함수
        Node n1 = nodes[i1];
        Node n2 = nodes[i2]; //데이터 = 인덱스
        if(!n1.adjacent.contains(n2)){ //두개의 노드에 인접한 노드를 저장하는 linkedlist에 상대방이 있는지 확인 
            n1.adjacent.add(n2); //없으면 서로 추가
        }
        if(!n2.adjacent.contains(n1)){
            n2.adjacent.add(n1);
        }
    }
    void dfs(){
        dfs(0); //0번부터 시작
    }

    void dfs(int index){ //dfs 순회 결과를 출력하는 함수
        Node root  = nodes[index]; //해당 인덱스로 노드를 가져옴.
        Stack<Node> stack = new Stack<Node>(); //스택을 하나 생성
        stack.push(root); //현재 노드를 스택에 추가
        root.marked = true; //스택에 들어감 표시
        while(!stack.isEmpty()){ //스택에 데이터가 없을때까지 진행
            Node r = stack.pop();
            for(Node n : r.adjacent){
                if(n.marked == false){
                    n.marked = true;
                    stack.push(n);
                }
            }
            visit(r); //방문할 때 출력
        }
    }
    void bfs(){
        bfs(0);
    }
    void bfs(int index){
        Node root = nodes[index];
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        root.marked = true;
        while(!queue.isEmpty()){
            Node r = queue.remove();
            for(Node n : r.adjacent){
                if(n.marked == false){
                    n.marked = true;
                    queue.add(n);
                }
            }
            visit(r);
        }
    }

    void dfsR(Node r){
        if(r == null) return;
        r.marked = true;
        visit(r);
        for(Node n : r.adjacent){
            if(n.marked == false){
                dfsR(n);
            }
        }
    }
    void dfsR(int index){
        Node r = nodes[index];
        dfsR(r);
    }
    void dfsR(){
        dfsR(0);
    }

    void visit(Node n){
        System.out.print(n.data + " ");
    }
}
public class Tree02 {
    public static void main(String[] args) {
        Graph g = new Graph(9);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(5, 6);
        g.addEdge(5, 7);                      
        g.addEdge(6, 8);
        g.dfs(0);
    }
    
    
}
