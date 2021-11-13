package sortalgorithm;

/**
 * 二分查找
 * 必须是排序的数组
 */
public class BinarySearch {

    public static int search(int[] array, int low, int high, int target) {
        if (low > high)
            return -1;
        //mid = (low + high) / 2 这种情况下 mid值有溢出
        int mid = low + (high - low) / 2;

        if (target < array[mid])
            return search(array, low, mid - 1, target);
        if (target > array[mid])
            return search(array, mid + 1, high, target);
        return mid;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 5, 7, 15, 99, 106, 234, 555, 6343, 9999};
        System.out.println(search(array,0,array.length,15));
    }
}
