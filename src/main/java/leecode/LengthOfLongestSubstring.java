package leecode;

/**
 * 最长的无重复子串
 * https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/
 */
public class LengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        int head = 0;
        int tail = 0;
        if(s.length() < 2) return s.length();
        int res = 1;
        while(tail < s.length()-1){
            tail++;
            //如果不包含当前字符，就更新最长的长度
            if(!s.substring(head,tail).contains(s.substring(tail,tail+1))){
                res = Math.max(tail- head + 1,res);
            }
            //如果包含的话，更新头指针
            else {
                while(s.substring(head,tail).contains(s.substring(tail,tail+1))){
                    head++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s ="12314562";
        System.out.println(LengthOfLongestSubstring.lengthOfLongestSubstring(s));
    }
}
