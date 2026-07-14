package com.nex.cms.model;

import com.nex.cms.components.RippleEffect;
import com.nex.cms.components.ShadowRenderer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import javax.swing.border.EmptyBorder;

public class MenuItem extends JButton {

    public float getAnimat() {
        return animat;
    }

    public void setAnimat(float animat) {
        this.animat = animat;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isSubMenuAble() {
        return subMenuAble;
    }

    public void setSubMenuAble(boolean subMenuAble) {
        this.subMenuAble = subMenuAble;
    }

    public int getSubMenuIndex() {
        return subMenuIndex;
    }

    public void setSubMenuIndex(int subMenuIndex) {
        this.subMenuIndex = subMenuIndex;
    }

    public int getLenth() {
        return length;
    }

    public void setLenth(int lenth) {
        this.length = lenth;
    }

    private RippleEffect rippleEffect;
    private BufferedImage shadow;
    private int shadowWidth;
    private int shadowSize = 10;
    private int index;
    private boolean subMenuAble;
    private float animat;

    private int subMenuIndex;
    private int length;

    public MenuItem(String name, int index, boolean subMenuAble) {
        super(name);
        this.index = index;
        this.subMenuAble = subMenuAble;
        setContentAreaFilled(false);
        setForeground(Color.black);
        setHorizontalAlignment(SwingConstants.LEFT);
        setBorder(new EmptyBorder(9, 10, 9, 10));
        setIconTextGap(10);
        rippleEffect = new RippleEffect(this);
        rippleEffect.setRippleColor(new Color(0, 102, 204));

    }

    private void createShadow() {
        int width = getWidth();
        int height = 5;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.BLACK);
        g2.fill(new Rectangle2D.Double(0, 0, width, height));
        shadow = new ShadowRenderer(shadowSize, 0.2f, Color.BLACK).createShadow(img);
        g2.dispose();
    }

    public void initSubMenu(int subMenuIndex, int length) {
        this.setSubMenuIndex(subMenuIndex);
        this.setLenth(length);
        setBorder(new EmptyBorder(9, 33, 9, 10));
        setBackground(new Color(255, 255, 255));
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D) graphics.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (length != 0) {
            g2.setColor(new Color(43, 141, 98));

            if (subMenuIndex == 1) {
                //f ind
                g2.drawImage(shadow, -shadowSize, -20, null);
                g2.drawLine(18, 0, 18, getHeight());
                g2.drawLine(18, getHeight() / 2, 26, getHeight() / 2);
            } else if (subMenuIndex == length - 1) {
                //l ind
                g2.drawImage(shadow, -shadowSize, getHeight() - 6, null);
                g2.drawLine(18, 0, 18, getHeight() / 2);
                g2.drawLine(18, getHeight() / 2, 26, getHeight() / 2);
            } else {
                g2.drawLine(18, 0, 18, getHeight());
                g2.drawLine(18, getHeight() / 2, 26, getHeight() / 2);
            }

        } else if (subMenuAble) {

            g2.setColor(getForeground());
            int arrowWid = 8;
            int arrowHig = 4;
            Path2D p2d = new Path2D.Double();
            p2d.moveTo(0, arrowHig * animat);
            p2d.lineTo(arrowWid / 2, (1f - animat) * arrowHig);
            p2d.lineTo(arrowWid, arrowHig * animat);
            g2.translate(getWidth() - arrowWid - 15, (getHeight() - arrowHig) / 2);
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2.draw(p2d);

        }

        g2.dispose();
        rippleEffect.reder(graphics, new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        createShadow();

    }

}
