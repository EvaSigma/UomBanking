package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionTypeFrame extends JFrame {
    private JFrame frame;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;
    private JButton paymentButton;
    private JButton returnToMainPageButton;
    private JLabel header;
    private JLabel line;


    public TransactionTypeFrame(){

        frame = new Template();

        header = Utils.setHeader("Transaction Type");
        header.setBounds(400,100,1000,100);

        line = new JLabel("________________________________________________________________________________________________________");
        line.setBounds(250,150,1000,100);

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        transferButton = new JButton("Transfer");
        paymentButton = new JButton("Payment");

        depositButton.setBounds(520,250,120,30);
        withdrawButton.setBounds(520,320,120,30);
        transferButton.setBounds(520,390,120,30);
        paymentButton.setBounds(520,460,120,30);

        returnToMainPageButton = Utils.returnToMainPageButton(frame);
        returnToMainPageButton.setBounds(480,560,200,30);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                new DepositFrame();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                new WithdrawFrame();
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                new TransferFrame();
            }
        });

        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                new PaymentFrame();
            }
        });

        frame.add(header);
        frame.add(line);
        frame.add(depositButton);
        frame.add(withdrawButton);
        frame.add(transferButton);
        frame.add(paymentButton);
        frame.add(returnToMainPageButton);


        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBackground(Color.LIGHT_GRAY);

    }
}

