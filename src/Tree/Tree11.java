package Tree;

import java.util.ArrayList;
import java.util.LinkedList;

class Tree_11{
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
        }
    }
    Node root;
    Tree_11(int size){
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

}
public class Tree11 {
    public static void main(String[] args) {
        Tree_11 t = new Tree_11(5);
        ArrayList<LinkedList<Integer>> result = allSequences(t.root);
        for(LinkedList<Integer> l : result){
            for(int data : l){
                System.out.print(data);
            }
            System.out.println();
        }
    }
    static ArrayList<LinkedList<Integer>> allSequences(Tree_11.Node node){
        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
        if(node == null) {
            result.add(new LinkedList<Integer>());
            return result;
        }
        LinkedList<Integer> prefix = new LinkedList<Integer>();
        prefix.add(node.data);

        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);
        
        //for문을 왼쪽 오른쪽 돌면서 모든 경우의 수를 다 찾음.
        for(LinkedList<Integer> left : leftSeq){
            for(LinkedList<Integer> right : rightSeq){
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
                weaveLists(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        }
        return result;
    }
    static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, 
    ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix){
       //두개의 파이프 중 하나라도 비었으면 더 이상 선택의 여지가 없으므로 결과반환
        if(first.size() == 0 || second.size() == 0){
            LinkedList<Integer> result = new LinkedList<Integer>();
            for(int data : prefix) result.add(data);
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
       } 
       //첫 번째 경우의 수
       int headFirst = first.removeFirst();
       prefix.addLast(headFirst);
       weaveLists(first, second, results, prefix);
       prefix.removeLast();
       first.addFirst(headFirst);

       //두번째 경우의 수
       int headSecode = second.removeFirst();
       prefix.addLast(headSecode);
       weaveLists(first, second, results, prefix);
       prefix.removeLast();
       second.addFirst(headSecode);
    }
}
