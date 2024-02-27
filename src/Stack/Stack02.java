package Stack;
//stack 정렬하기

import java.util.EmptyStackException;
class Stack<T>{
    class Node<T>{
        private T data;
        private Node<T> next;

        public Node(T data){
            this.data = data;
        }  
    }
    private Node<T> top;

    int size;
    public int size(){
        return size;
    }
    
    public T pop(){
        if(top == null){
            throw new EmptyStackException();
        }
        T item = top.data;
        top = top.next;
        return item;
    }

    public void push(T item){
        Node<T> t = new Node<T>(item);
        t.next = top;
        top = t;
    }

    public T peek(){
        if(top == null){
            throw new EmptyStackException();
        }
        return top.data;
    }

    public boolean isEmpty(){
        return top == null;
    }
}
public class Stack02 {

    //Stack을 인자로 받는 sort 함수
    private static void sort(Stack<Integer> s1){
        Stack<Integer> s2 = new Stack<Integer>();  //s1과 비교하여 작은 값을 저장시킬 스택 s2
        while(!s1.isEmpty()){ //정렬이 되어있지않은 스택 s1을 하나씩 돌면서
            int tmp = s1.pop(); //s1에서 데이터를 pop 후 tmp에 넣음.
            while(!s2.isEmpty() && s2.peek() > tmp){ //s2 스택이 비어있지 않고 s2 top에 값이 s1에서 꺼낸 값보다 크면
                s1.push(s2.pop());
            }
            //s1에서 꺼낸 값보다 s2 top값이 작으면 
            s2.push(tmp);
        } // 반복문을 다 돌고 나면 s2 스택에는 정렬된 데이터가 들어있고

        //s2 스택이 비어 있지 않으면 s2 스택에서 데이터 꺼내서 s1에 push
        while(!s2.isEmpty()){
            s1.push(s2.pop());
        }
    }
    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<Integer>();
        s1.push(3);
        s1.push(5);
        s1.push(1);
        s1.push(6);
        sort(s1);
        System.out.println(s1.pop());
        System.out.println(s1.pop());
        System.out.println(s1.pop());
        System.out.println(s1.pop());
    }

    
}
