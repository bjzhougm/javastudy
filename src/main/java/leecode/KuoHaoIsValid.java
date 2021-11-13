package leecode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://mp.weixin.qq.com/s/HkDnPxuOAT3GmbMgMmIAgg
 */
public class KuoHaoIsValid {
    public boolean isValid(String s) {
        int len = s.length(); // 括号的长度
        if (len % 2 == 1) { // 括号不是成对出现直接返回 false
            return false;
        }
        // 把所有对比的括号存入 map，对比时用
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        // 定义栈，用于存取括号（辅助比较）
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) { // 循环所有字符
            char c = s.charAt(i);
            if (map.containsKey(c)) { // 为右边的括号，如 ')'、'}' 等
                if (stack.isEmpty() || stack.peek() != map.get(c)) { // 栈为空或括号不匹配
                    return false;
                }
                stack.pop(); // 是一对括号，执行出栈（消除左右括号）
            } else { // 左边括号，直接入栈
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

    }
}
