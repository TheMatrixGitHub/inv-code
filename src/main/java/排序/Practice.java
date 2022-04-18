package 排序;

import com.sun.java.swing.plaf.windows.WindowsTableHeaderUI;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * practice
 *
 * @author: sl
 * Date: 2021/9/29
 * Time: 12:31
 */
public class Practice {

    public void insertionSort(int[] ints) {

    }

    /**
     * @param ints
     * @return
     */
    public void selectionSort(int[] ints) {

    }

    /**
     * @param array
     */
    public void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * @param array
     */
    private void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        Random random = new Random();

        int pivot = left + random.nextInt(right - left);

        swap(array, pivot, right);

        int i = left;
        int j = right - 1;

        while (i <= j) {
            if (array[i] <= array[right]) {
                i++;
            } else {
                swap(array, i, j);
                j--;
            }
        }

        swap(array, i, right);

        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);


    }

    public void mergeSort(int[] array) {
        int[] temp = new int[array.length];
        mergeSort(array, 0, array.length - 1, temp);
    }

    private void mergeSort(int[] array, int left, int right, int[] tempArray) {

        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid, tempArray);
        mergeSort(array, mid + 1, right, tempArray);

        int i = left;
        int j = mid + 1;
        int temp = left;

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                tempArray[temp++] = array[i++];
            } else {
                tempArray[temp++] = array[j++];
            }
        }

        while (i <= mid) {
            tempArray[temp++] = array[i++];
        }

        while (j <= right) {
            tempArray[temp++] = array[j++];
        }

        while (left <= right) {
            array[left] = tempArray[left];
            left++;
        }

    }

    private void swap(int[] ints, int x, int y) {
        int tmp = ints[x];
        ints[x] = ints[y];
        ints[y] = tmp;


    }

    @Test
    public void test() {
        int[] array = new int[]{10, 8, 4, 9, 5, 6, 7, 11, 3, 4, 2, 1};

        quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}