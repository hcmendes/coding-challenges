import java.util.ArrayList;
import java.util.List;

/**
 * @author github.com/hcmendes
 */
public class NonRecursiveMergeSort {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(4);
        list.add(1);
        list.add(3);
        list.add(6);
        list.add(2);

        int len = 1;
        while (len < list.size()) {

            int i = 0;

            while (i < list.size()) {

                int l1 = i;
                int r1 = i + len - 1;
                int l2 = i + len;
                int r2 = l2 + len - 1;

                if (l2 >= list.size()) break;

                if (r2 >= list.size()) r2 = list.size() - 1;

                mergeParts(list, l1, r1, l2, r2);

                i = i + len * 2;
            }

            len = len * 2;
        }

        System.out.println(list);
    }

    static void mergeParts(List<Integer> list,
                           int l1,
                           int r1,
                           int l2,
                           int r2) {
        List<Integer> orderedPartList = new ArrayList<>();

        int i = 0;
        int j = 0;

        List<Integer> part1List = list.subList(l1, r1 + 1);
        List<Integer> part2List = list.subList(l2, r2 + 1);

        while (i < part1List.size() && j < part2List.size()) {

            if (part1List.get(i) <= part2List.get(j)) {
                orderedPartList.add(part1List.get(i));
                i++;
            } else {
                orderedPartList.add(part2List.get(j));
                j++;
            }
        }

        while (i < part1List.size()) {
            orderedPartList.add(part1List.get(i));
            i++;
        }

        while (j < part2List.size()) {
            orderedPartList.add(part2List.get(j));
            j++;
        }

        for (int h = 0; h < r2 - l1 + 1; h++) {
            list.set(l1 + h, orderedPartList.get(h));
        }
    }
}
