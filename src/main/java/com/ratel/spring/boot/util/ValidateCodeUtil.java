package com.ratel.spring.boot.util;

import java.awt.*;
import java.util.Random;

/**
 * 生成验证码工具类
 * Created by nice on 2018/9/29.
 */
public class ValidateCodeUtil {
    //初始化种子
    protected static char[] CODE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    /**
     * 得到随机字符串
     * @param num
     * @return
     */
    public static String getValidateCode(int num) {
        Random random=new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < num; i++) {
            char c = CODE[random.nextInt(CODE.length - 1)];
            stringBuffer.append(c);
        }
        return stringBuffer.toString();
    }

    public static void  drawString(Graphics2D g2, String ValidateCode){
        Random random=new Random();
        String str1[] = new String[ValidateCode.length()];
        for (int i = 0; i < str1.length; i++) {
            str1[i] = ValidateCode.substring(i, i + 1);
            int w;
            int x = (i + 1) % 3;
            //随机生成验证码字符水平偏移量
            if (x == random.nextInt(7)) {
                w = 30 - random.nextInt(7);
            } else {
                w = 30 + random.nextInt(7);
            }
            //随机生成颜色
            g2.setColor(getRandColor());
            g2.drawString(str1[i], 20 * i + 10, w);
        }
    }
    /**
     * 随机产生定义的颜色
     *
     * @return
     */
    private static Color getRandColor() {
        Random random = new Random();
        Color color[] = new Color[10];
        color[0] = new Color(32, 158, 25);
        color[1] = new Color(218, 42, 19);
        color[2] = new Color(31, 75, 208);
        color[3] = new Color(0, 102, 182);
        color[4] = new Color(171, 0, 85);
        return color[random.nextInt(5)];
    }
}
