package Sorting;

public class Sorting03 {
    private static void bubbleSort(int[] arr){
        bubbleSort(arr, arr.length - 1);
    }
    private static void bubbleSort(int[] arr, int last){
        if(last > 0){ //재귀 호출
            for(int i = 1; i <= last; i++){
                if(arr[i - 1] > arr[i]){
                    swap(arr, i - 1, i);
                }
            }
            bubbleSort(arr, last - 1); // 맨 마지막은 정렬이 되었으므로 마지막인덱스 - 1을 함.
        }
    }
    private static void swap(int[] arr, int source, int target){
        int tmp = arr[source];
        arr[source] = arr[target];
        arr[target] = tmp;
    }
    private static void printArray(int[] arr){
        for(int data : arr){
            System.out.print(data + ", ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = {3,5,4,2,1};
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
    }
}
