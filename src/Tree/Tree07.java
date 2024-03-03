package Tree;

class Tree7{
    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
        }
    }
    Node root;
    int size;
    Tree7(int size){
        this.size = size;
        root = makeBST(0, size - 1);
        
    }
    Node makeBST(int start, int end){
        if(start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid - 1);
        node.right = makeBST(mid + 1, end);
        return node;
    }
    boolean isValidateBST1(){
        int[] array = new int[size];
        inorder(root, array);// 시작 노드와 순서대로 가져온 노드르 담을 배열
        for(int i=1; i<array.length; i++){
            if(array[i] < array[i - 1]){ //왼쪽 데이터가 현재 데이터보다 크면 false 리턴
                return false;
            }
        }
        return true;
    }
    int index=0; //몇 번째 방까지 담았는지
    void inorder(Node root, int[] array){
        if(root != null){
            inorder(root.left, array);
            array[index] = root.data;
            index++;
            inorder(root.right, array);
        }
    }
    //이전 노드의 값 저장
    Integer last_printed = null; //정수 객체의 주소를 넘길 수 있도록 클래스타입으로 선언
    boolean isValidateBST2(){
        return isValidateBST2(root);
    }
    boolean isValidateBST2(Node n){
        if(n == null) return true;
        if(!isValidateBST2(n.left)) return false; //정렬되지 않은 값 만나면 false 리턴
        if(last_printed != null && n.data < last_printed){
            return false;
        }
        last_printed = n.data; // 현재 값을 이전값에 넣음.
        if(!isValidateBST2(n.right)) return false;
        return true;
    }

    boolean isValidateBST3(){
        return isValidateBST3(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    boolean isValidateBST3(Node n, int min, int max){
        if(n == null){
            return true;
        }
        if(n.data < min || n.data > max){ //노드가 최대 최소값 영역을 벗어나면 종료
            return false;
        }
        //노드의 왼쪽일때 최대값을 현재값으로 주어 영역 좁히고, 노드의 오른쪽일때 최소값을 현재값으로 주어 영역좁힘.
        if(!isValidateBST3(n.left, min, n.data) || !isValidateBST3(n.right, n.data, max)){
        
            return false;
        }
        return true;
    }
}
//주어진 트리가 이진검색트리인지 확인
public class Tree07 {
    public static void main(String[] args) {
        Tree7 t = new Tree7(10);
        System.out.println("Solution 1 - using inorder:"+t.isValidateBST1());
        System.out.println("Solution 2 - without array:"+t.isValidateBST2());
        System.out.println("Solution 3 - min/max:"+t.isValidateBST3());
    }
}
