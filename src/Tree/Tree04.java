package Tree;

//배열을 이진검색트리로 만들기
class Tree4{
    class Node{
        int data;
        Node left;
        Node right;
        Node (int data){
            this.data = data;
        }
    }
    Node root; //트리가 시작되는 루트 노드
    public void makeTree(int[] a){
        root = makeTreeR(a, 0, a.length-1); // 재귀 호출이 끝나면 가장 꼭대기에 있는 루트노드 주소를 받아서 멤버 변수에 저장
    }
    public Node makeTreeR(int[] a, int start, int end){ 
        if(start > end) return null;
        int mid = (start + end) / 2; //시작지점과 끝지점으로 중간지점 계산   
        Node node = new Node(a[mid]); //중간에 저장된 값으로 노드 생성      
        node.left = makeTreeR(a, start, mid - 1 );//시작 지점에서 중간지점 바로 앞까지의 노드를 현재노드의 왼쪽에 생성 
        node.right = makeTreeR(a, mid+1, end);//중간 지점 바로 다음부터 끝 지점까지의 노드를 현재노드의 오른쪽에 생성
        return node; //노드 반환
    }

    public void searchBTree(Node n, int find){ //검색할 노드, 찾을값
        if(find < n.data){ //찾을 값이 현재데이터 값보다 작은지 비교
            System.out.println("Data is smaller than " + n.data);
            searchBTree(n.left, find); // 찾는 값이 더 작으므로 왼쪽 노드 주소와 찾는값을 인자로 전달, 반복 호출
        }else if(find > n.data){
            System.out.println("Data is bigger than " + n.data);
            searchBTree(n.right, find); // 찾는 값이 더 크므로 오른쪽 노드 주소와 찾는값을 인자로 전달, 반복 호출
        }else{
            System.out.println("Data found!"); // 찾는 값이 현재 노드의 데이터와 동일
        }
    }
}
public class Tree04 {
    public static void main(String[] args) {
        int[] a = new int[10];
        for(int i=0; i < a.length; i++){
            a[i] = i;
        }

        Tree4 t = new Tree4();
        t.makeTree(a);
        t.searchBTree(t.root, 2);
    }
}
