package Sorting;

public class Sorting01 {
    private static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length - 1);
    }
    private static void quickSort(int[] arr, int start, int end){
        int part2 = partition(arr, start, end); //배열 안에서 시작과 끝 파티션을 나눔. 나눈 파티션의 오른쪽 방 첫 번째 값 받아옴.
        if(start < part2 - 1){ // 시작값이 중간값보다 
            quickSort(arr, start, part2 - 1);
        }
        if(part2 < end){ //중간값이 끝나는 값보다 작은 경우에만
            quickSort(arr, part2, end);
        }
    }
    private static int partition(int[] arr, int start, int end){
        int pivot = arr[(start + end)/ 2];
        while(start <= end){ //시작점이 끝나는 점보다 작거나 같은 동안만 반복
            while(arr[start] < pivot) start++;
            while(arr[end] > pivot) end--;
            if(start <= end){ //시작점이 끝나는 점을 지나치지 않았는지 확인
                swap(arr, start, end);
                start++;
                end--;
            }
        }
        return start; //오른쪽 파티션 배열의 인덱스가 들어감.
    }
    private static void swap(int[] arr, int start, int end){
        int tmp = arr[start];
        arr[start] = arr[end];
        arr[end] = tmp;
    }
    private static void printArray(int[] arr){
        for(int data : arr){
            System.out.print(data + ", ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = {3, 9, 4, 7, 5, 0, 1, 6, 8, 2};
        printArray(arr);
        quickSort(arr);
        printArray(arr);
    }
}
