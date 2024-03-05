package Sorting;

public class Sorting04 {

    private static void selectionSort(int[] arr){
        selectionSort(arr, 0);
    }
    private static void selectionSort(int[] arr, int start){
        if(start < arr.length - 1){
            int min_index = start; 
            for(int i = start; i<arr.length; i++){
                // 가장 작은 값의 인덱스를 min_index에 담음.
                if(arr[i] < arr[min_index]) min_index = i;
            }
            swap(arr, start, min_index);
            // 가장 앞에 값은 정렬이 된 것이기 때문에 start인덱스에 + 1함.
            selectionSort(arr, start + 1);
        }
    }
    private static void swap(int[] arr, int index1, int index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    private static void printArray(int[] arr){
        for(int data : arr){
            System.out.print(data + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {3,6,1,8,2,4};
        printArray(arr);
        selectionSort(arr);
        printArray(arr);
    }
}
