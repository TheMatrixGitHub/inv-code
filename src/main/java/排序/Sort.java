package 排序;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: sl
 * Date: 2021/9/28
 * Time: 22:35
 */
public class Sort {

    public void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * 5132 4
     *
     * @param array
     * @param left
     * @param right
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
        int[] newArray = new int[array.length];
        mergeSort(array, 0, array.length - 1, newArray);
    }

    private void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid, temp);
        mergeSort(array, mid + 1, right, temp);

        int i = left;
        int j = mid + 1;
        int tmp = left;

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[tmp++] = array[i++];
            } else {
                temp[tmp++] = array[j++];
            }
        }

        while (i <= mid) {
            temp[tmp++] = array[i++];
        }

        while (j <= right) {
            temp[tmp++] = array[j++];
        }

        while (left <= right) {
            array[left] = temp[left];
            left++;
        }

    }

    public void insertionSort(int[] input) {
        for (int i = 1; i < input.length; i++) {
            int tmp = input[i];
            int j = i - 1;
            while (j >= 0 && input[j] > tmp) {
                input[j + 1] = input[j];
                j--;
            }
            input[j + 1] = tmp;
        }
    }


    /**
     * @param ints
     */
    public void selectionSort(int[] ints) {
        for (int i = 0; i < ints.length - 1; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < ints.length; j++) {
                if (ints[j] < ints[minValueIndex]) {
                    minValueIndex = j;
                }
            }
            swap(ints, i, minValueIndex);
        }
    }

    private void swap(int[] ints, int x, int y) {
        int tmp = ints[x];
        ints[x] = ints[y];
        ints[y] = tmp;
    }

    @Test
    public void test() {
        int[] ints = new int[]{10, 8, 9, 5, 6, 7, 3, 4, 2, 11, 6, 1};

        quickSort(ints);

        System.out.println(Arrays.toString(ints));
    }

}