package leecode;

/**
 * 字符串变整数
 * https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/
 */
public class StrToInt {
    public int strToInt(String str) {
        if (str == null || str.trim().length() == 0)
            return 0;
        // 处理正负号
        int sign = 1;
        // 累计
        int sum = 0;
        // 第一个开始值
        int first = 0;
        char[] array = str.trim().toCharArray();
        // 判断正负，并且移动第一个数字
        if (array[0] == '-') {
            sign = -1;
            first ++;
        } else if (array[0] == '+') {
            first ++;
        }
        // 跳过开头的0
        while (first < array.length && array[first] == '0') {
            first ++;
        }
        // 非数字开头
        if (first < array.length && (array[first] - '0' < 0 || array[first] - '0' > 9))
            return 0;

        for (int i = first;i < array.length;i ++) {
            if (array[i] - '0' >= 0 && array[i] - '0' < 10) {
                // 判断是否超过int上限
                if (sum > (Integer.MAX_VALUE - (array[i] - '0')) / 10) {
                    // 别忘了判断符号
                    return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                sum = sum * 10 + (array[i] - '0');
            } else if (sum > 0) {
                // 后面的值无效了，直接return了
                return sign * sum;
            }
        }
        return sign * sum;
    }
}
