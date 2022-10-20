package datastructure;

public class Sort {

    public static void main(String[] args) {

        int[] arr ={5,3,7,2,1,4,6};

        bubbleDescSort(arr);

        bubbleAescSort(arr);

        selsort(arr);

    }


    /**
     * 冒泡排序
     *      比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     *      对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     *      针对所有的元素重复以上的步骤，除了最后一个。
     *      持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     * @param numbers
     */
    public static void bubbleDescSort(int[] numbers){

        int temp;//记录临时中间值
        int size =numbers.length;
        System.out.println("numbers.length==="+size);
        //因为最后一次可以不排序,所以可以size -1
        for (int i= 0;i <size-1;i++){
            for (int j=i+1;j<size;j++){
                if(numbers[i]<numbers[j]){
                    temp =numbers[i];
                    numbers[i]=numbers[j];
                    numbers[j]=temp;
                }
            }
        }
        System.out.print("倒序冒泡排序法=====");
        for (int i=0;i<size;i++) System.out.print(numbers[i]);
        System.out.println("");
    }



    public static void bubbleAescSort(int[] numbers){

        int temp;//记录临时中间值
        int size =numbers.length;

        //因为最后一次可以不排序,所以可以size -1

        for(int i=0;i<size-1;i++){
            for (int j=i+1;j<size;j++){
                if(numbers[i]>numbers[j]){
                    temp =numbers[i];
                    numbers[i]=numbers[j];
                    numbers[j]=temp;
                }
            }
        }

        System.out.print("升序冒泡排序法=====");
        for (int i=0;i<size;i++) System.out.print(numbers[i]);
        System.out.println("");
    }

    /**
     * 选择排序法
     * 将要排序的对象分作两部份，一个是已排序的，
     * 一个是未排序的，从后端未排序部份选择一个最小值，
     * 并放入前端已排序部份的最后一个。
     *
     * 开始排序的时候k的值和i的相同,都为0的其实位置，通过内循环j,
     * 如果j的下标的数小于起始位置下标的数,则把j的值给k,就是把k移动到j的位置
     * j继续右走,如果发现k下标的值大于j下标的值,则继续把k的值移动到j的位置
     * 这样则把最小值的下标的数找到,如果开始的k于i的值不同则交换k和i下标的数值
     * 这样第一次循环遍历把最小的值找到,i+1为1,k的值恢复为1继续内循环,
     * j为数值长度
     *
     */
    public static void selsort(int numbers[]) {

        if((numbers==null)||(numbers.length==0))
            return;

        int size = numbers.length, temp;

        for (int i = 0; i < size; i++) {
            int k = i;//无序区的最小数据数组下标
            for (int j = size - 1; j >i; j--)  {
                //在无序区中找到最小数据并保存其数组下标
                if (numbers[j] < numbers[k])  k = j;
            }
            if(k!=i){
                temp = numbers[i];
                numbers[i] = numbers[k];
                numbers[k] = temp;
            }
        }
        System.out.print("选择排序法======");
        for (int i=0;i<size;i++) System.out.print(numbers[i] );
        System.out.println("");
    }

    /**
     * 插入排序
     * 对于第一个数是为已排序
     * temp的值为第一个
     * 取第二个数与第一个数比较
     * 如果k下标的值大于k-1下标的值则交换
     * 第一次循环结束
     * 第二次循环
     * 此时取第三个数 此时i=2
     *
     * 循环前面已经排序的数组
     * 逐个比较j =2 j--
     * 如果发现
     *
     */

    public static void insertsort(int arr[]){
        for(int i = 1;i < arr.length; i ++){
            if(arr[i] < arr[i-1]){
                //注意[0,i-1]都是有序的。如果待插入元素比arr[i-1]还大则无需再与[i-1]前面的元素进行比较了，反之则进入if语句
                int temp = arr[i];
                int j;
                for(j = i-1; j >= 0 && arr[j] > temp; j --){
                    arr[j+1] = arr[j];//把比temp大或相等的元素全部往后移动一个位置
                }
                arr[j+1] = temp;//把待排序的元素temp插入腾出位置的(j+1)
            }
        }

    }
}
