package com.sx.utils;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImgThread extends Object implements Runnable{
    private  String[] imgs ={
            "https://yfuean-student-manager.oss-cn-shanghai.aliyuncs.com/img/1.png",
            "https://yfuean-student-manager.oss-cn-shanghai.aliyuncs.com/img/2.png",
            "https://yfuean-student-manager.oss-cn-shanghai.aliyuncs.com/img/3.png",
            "https://yfuean-student-manager.oss-cn-shanghai.aliyuncs.com/img/3.png"};
    private JLabel imgLabel;
    public void setImgLabel(JLabel imgLabel){
        this.imgLabel=imgLabel;
    }
    @Override
    public void run () {
        int i=0;
        int length=imgs.length-1;
        while (true){
            //通过路径构造File对象
            try {
                URL url = new URL(imgs[i]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn .setRequestMethod("GET");
                conn.setConnectTimeout(5*1000);
                InputStream inStream = conn.getInputStream();
                ByteArrayOutputStream out1 = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len= 0;
                while ((len = inStream.read(buffer) ) != -1){
                    out1.write(buffer,0,len);
                }
                byte[] date = out1.toByteArray();
                inStream.read(date);
                Icon icon = new ImageIcon(date);
                imgLabel.setIcon(icon);
                Thread.sleep(1000);
                inStream.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("IO异常");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            if (i == length){
                i = 0;
            }
        }
    }
}
