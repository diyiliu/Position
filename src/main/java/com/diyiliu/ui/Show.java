package com.diyiliu.ui;

import com.diyiliu.util.BaiduApiUtil;
import com.diyiliu.util.GpsCorrectUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Description: Show
 * Author: DIYILIU
 * Update: 2016-04-29 13:54
 */
public class Show {

    private JTextField lat;
    private JTextField lng;
    private JTextField enlat;
    private JTextField enlng;
    private JTextArea address;
    private JPanel mainPanel;
    private JPanel inPanel;
    private JPanel outPanel;
    private JButton action;

    public static void main(String[] args) {

        try {
            // 设置样式
            String ui = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(ui);

        } catch (Exception e) {
            e.printStackTrace();
        }

        final Show mainView = new Show();
        final JFrame frame = new JFrame("配置面板");

        frame.setContentPane(mainView.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        // 设置窗口居中
        int WIDTH = frame.getWidth();
        int HEIGHT = frame.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screamSize = kit.getScreenSize();
        frame.setBounds((screamSize.width - WIDTH) / 2, (screamSize.height - HEIGHT) / 2, WIDTH, HEIGHT);

        mainView.action.addActionListener((ActionEvent e) -> {

            mainView.toDo(mainView);
        });
    }

    public void toDo(Show mainView){

        double lat = Double.valueOf(mainView.lat.getText());
        double lng = Double.valueOf(mainView.lng.getText());

        com.diyiliu.bean.Point enPoint = GpsCorrectUtil.transform(lat, lng);

        mainView.enlat.setText(String.format("%.6f ",keepDecimal(enPoint.getLat(), 6)));
        mainView.enlng.setText(String.format("%.6f ",keepDecimal(enPoint.getLng(), 6)));

        String address = BaiduApiUtil.decPosition(lat, lng);

        mainView.address.append(address);
    }

    public static double keepDecimal(double d, int digit) {

        BigDecimal decimal = new BigDecimal(d);
        decimal = decimal.setScale(digit, RoundingMode.HALF_UP);

        return decimal.doubleValue();
    }
}
