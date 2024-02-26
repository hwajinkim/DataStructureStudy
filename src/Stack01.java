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
class MyQueue<T>{
    Stack<T> stackNewest, stackOldest;
    MyQueue(){
        stackNewest = new Stack<T>();
        stackOldest = new Stack<T>();
    }

    public int size(){
        return stackNewest.size() + stackOldest.size();
    }
    public void add(T value){
        stackNewest.push(value);
    }
    private void shiftStacks(){
        if(stackOldest.isEmpty()){ // 오래된 스택이 비어있으면
            while(!stackNewest.isEmpty()){ //새로운 스택이 비어있을 때까지
                stackOldest.push(stackNewest.pop()); //새로운 스택에서 꺼낸 값을 오래된 스택에 저장
            }
        }
    }

    public T peek(){
        shiftStacks();
        return stackOldest.peek();
    }

    public T remove(){
        shiftStacks();
        return stackOldest.pop();
    }
}
public class Stack01 {
    public static void main(String[] args) {
        MyQueue<Integer> q = new MyQueue<Integer>();
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println(q.remove()); //1
        System.out.println(q.remove()); //2
        System.out.println(q.remove()); //3
    }
}
