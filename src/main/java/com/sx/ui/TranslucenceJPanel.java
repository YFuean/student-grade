package com.sx.ui;

import javax.swing.*;
import java.awt.*;

/**
 * 绘制半透明面板
 */
public class TranslucenceJPanel extends JPanel {
    private float transparency;
//    public TranslucenceJPanel(){
//    }
    //transparency:透明度
    public void setTransparent(float transparency) {
        this.transparency = transparency;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphics2d = (Graphics2D) g.create();
        graphics2d.setComposite(AlphaComposite.SrcOver.derive(transparency));
        graphics2d.setColor(getBackground());
        graphics2d.fillRect(0, 0, getWidth(), getHeight());
        graphics2d.dispose();
    }
}
