package sortalgorithm;

public class BubbleSort {

    public static void bubbleSort(int[] a){
        int n = a.length -1;
        //一共n-1趟排序
        for (int i = 0 ;i < n;i++){
            //i趟排序从第0个元素到第n-i个元素
            for (int j= 0; j< n -i;j++ ){
                if (a[j] > a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }



    public static void main(String[] args) {
        int[] a= {2,5,3,7,3,9};
        bubbleSort(a);
        for ( int x:a) {
            System.out.println(x);
        }
    }

}
