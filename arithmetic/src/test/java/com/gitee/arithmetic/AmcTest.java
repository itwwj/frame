package com.gitee.arithmetic;

import org.junit.Test;

/**
 * 求给定一个数字中1的个数
 *
 * @author jie
 */
public class AmcTest {


    /**
     * 1的个数：7000001
     * 计算耗时：1892毫秒
     */
    @Test
    public void test01() {
        long startTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 1; i <= 100000000; i++) {
            String str = String.valueOf(i);
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == 49) {
                    count++;
                }
            }
        }
        System.out.println("1的个数：" + count);
        System.out.println("计算耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
    }



    /**
     * 1的个数：7000001
     * 计算耗时：1毫秒
     */
    @Test
    public void test02() {

        long startTime = System.currentTimeMillis();
        int num = 100000000, saveNum = 1, countNum = 0, lastNum = 0;
        int copyNum = num;
        while (num != 0) {
            lastNum = num % 10;
            num /= 10;
            if (lastNum == 0) {
                // 如果是0那么正好是少了一次所以num不加1了
                countNum += num * saveNum;
            } else if (lastNum == 1) {
                // 如果是1说明当前数内少了一次所以num不加1，而且当前1所在位置
                // 有1的个数，就是去除当前1最高位，剩下位数，的个数。
                countNum += num * saveNum + copyNum % saveNum + 1;
            } else {
                // 如果非1非0.直接用公式计算
                // abcd...=(abc+1)*1+(ab+1)*10+(a+1)*100+(1)*1000...
                countNum += (num + 1) * saveNum;
            }
            saveNum *= 10;
        }
        System.out.println("1的个数：" + countNum);
        System.out.println("计算耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
    }
}
