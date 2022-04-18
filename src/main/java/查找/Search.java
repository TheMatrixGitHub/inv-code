package 查找;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: sl
 * Date: 2021/10/10
 * Time: 14:54
 */
public class Search {

    /**
     * 二分查找
     * @param arr
     * @param data
     * @return
     */
    public static int binary(int[] arr, int data) {
        int min = 0;
        int max = arr.length - 1;
        int mid;
        while (min <= max) {
            mid = (min + max) / 2;
            if (arr[mid] > data) {
                max = mid - 1;
            } else if (arr[mid] < data) {
                min = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}