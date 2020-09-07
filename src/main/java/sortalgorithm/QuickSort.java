package sortalgorithm;

public class QuickSort {

    public static void quickSort(int[] array, int start, int end) {

        //计算中间基准位置数据
        int pivot = array[start+(end - start) / 2];

        //左边界开始，右边界为结束
        int leftIndex = start;
        int rightIndex = end;

        //当左边界小于右边界的时候
        while (leftIndex <= rightIndex) {

            while (array[rightIndex] > pivot) {
                rightIndex--;
            }


            while (array[leftIndex] < pivot) {
                leftIndex++;
            }

            // Swap 上面的while循环结束时，就说明当前的a[start]的值比基准值大，应与基准值进行交换
            if (leftIndex <= rightIndex) {
                int temp = array[leftIndex];
                array[leftIndex] = array[rightIndex];
                array[rightIndex] = temp;
                leftIndex++;
                rightIndex--;
            }
        }

        if (start < rightIndex) {
            quickSort(array, start, rightIndex);
        }

        if (leftIndex < end) {
            quickSort(array, leftIndex, end);
        }
    }


    public static void main(String[] args) {
        int[] arr = {2,3,1,5,4,8,20,11,14};
        quickSort(arr,0,arr.length-1);
        for(int x : arr){
            System.out.print(x+" ");
        }
    }
}
