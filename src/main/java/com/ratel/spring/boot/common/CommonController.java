package com.ratel.spring.boot.common;

import com.ratel.spring.boot.util.ValidateCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by nice on 2018/9/29.
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {


    /**
     * 生成动态验证码
     *
     * @param response
     * @param request
     */
    @RequestMapping("/gifCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            // 禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            //生成随机字串
            String ValidateCode = ValidateCodeUtil.getValidateCode(4);
            //存入会话session
            request.getSession().setAttribute("CODE", ValidateCode);
            //生成图片
            BufferedImage image = new BufferedImage(150, 70, BufferedImage.TYPE_INT_RGB);
            //得到它的绘制环境，也就是画笔
            Graphics2D g2 = (Graphics2D) image.getGraphics();
            g2.setColor(Color.white); //设置画框
            g2.fillRect(0, 0, 150, 70);//填充整张图片,设置背景色
            g2.drawRect(0, 0, 150, 70);
            g2.setFont(new Font("Courier New", Font.BOLD, 30));//设置字体
            g2.setColor(Color.BLACK);//设置颜色
            ValidateCodeUtil.drawString(g2,ValidateCode);
            ImageIO.write(image, "jpeg", response.getOutputStream());
        } catch (Exception e) {
            log.error("图形验证码生成失败", e);
        }
    }


}
