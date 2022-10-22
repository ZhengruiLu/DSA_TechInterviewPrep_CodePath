package session2;

public class Main {
    public static void main(String[] args) {
        System.out.println(IntegerToString(123));
        System.out.println(IntegerToString(-6714));
    }

    /*
    8. String to Integer (atoi)
    https://leetcode.com/problems/string-to-integer-atoi/
     */
    public static int myAtoi(String s) {
        //check the sign
        char[] chars = s.toCharArray();
        int n = chars.length, sign = 1;
        int ans = 0, idx = 0;

        //skip all spaces
        while (idx < n && chars[idx] == ' ') {
            idx++;
        }

        //check the sign
        if (idx < n && chars[idx] == '-'){
            sign = -1;
            idx++;
        } else if (idx < n && chars[idx] == '+'){
            idx++;
        }

        //read digits
        while (idx < n && Character.isDigit(chars[idx])) {
            //overflow or underflow?
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10
                    && chars[idx] - '0' > Integer.MAX_VALUE % 10)) {
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            ans = ans * 10 + (chars[idx] - '0');
            idx++;
        }


        return sign * ans;
    }

    //integer to string
    public static String IntegerToString(int num) {
        //edge
        if (num == 0) return "0";

        //record the sign
        boolean isNegative = num < 0 ? true : false;

        //use abs of num
        num = Math.abs(num);

        //get digit and append into ans stringBuilder
        StringBuilder sb = new StringBuilder();

        while (num != 0) {
            int digit = num % 10;
            sb.append(String.valueOf(digit)); //or Character.forDigit(C, 10)

            num /= 10;
        }
        //add the sign
        if (isNegative) sb.append('-');

        sb.reverse();

        return sb.toString();
    }
}
