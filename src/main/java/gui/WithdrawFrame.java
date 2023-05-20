package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WithdrawFrame extends JFrame {
    JLabel header;
    JLabel line;
    JLabel amountLabel;
    JTextField amountField;
    JButton continueButton;
    JButton returnToMainPageButton;
    String amount;
    JFrame withdraw;
    public WithdrawFrame(){
        withdraw = new Template();

        //Initializing elements;
        header = new JLabel("Withdraw");
        line = new JLabel("_____________________________________________________________________________________");
        amountLabel = new JLabel("Enter amount");
        amountField = new JTextField();
        continueButton = new JButton("Continue");
        returnToMainPageButton = new JButton("Return to the main page");

        //Placing the elements
        header.setBounds(500, 100, 1000, 100);
        header.setFont(new Font("Courier", Font.PLAIN, 40));

        line.setBounds(325,150,500,100);

        amountLabel.setBounds(515,300,400,50);
        amountLabel.setFont(new Font("Courier", Font.PLAIN, 20));

        amountField.setBounds(500,370,150,25);

        continueButton.setBounds(525,550,100,30);

        returnToMainPageButton.setBounds(950,700,200,35);

        //ActionListener
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amount = amountField.getText();
                if(isCorrect(amount) && checkAmount(amount,0)){
                    withdraw.dispose();
                    new PreviewWithdrawFrame();

                }

            }
        });

        //Adding the elements
        withdraw.add(header);
        withdraw.add(line);
        withdraw.add(amountLabel);
        withdraw.add(amountField);
        withdraw.add(continueButton);
        withdraw.add(returnToMainPageButton);

        withdraw.setVisible(true);
        withdraw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public boolean isCorrect(String anAmount){
        boolean flag = true;
        if(amount.isBlank()) {
            JOptionPane.showMessageDialog(withdraw, "Please enter an amount!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            flag=false;
            return flag;
        }
        for(int i=0;i<anAmount.length();i++){
            if(!Character.isDigit(anAmount.charAt(i))){
                flag=false;
                JOptionPane.showMessageDialog(withdraw, "Please enter an amount!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                break;
            }
        }
        return flag;
    }

    public boolean checkAmount(String anAmount, double balance){
        double amount = Double.parseDouble(anAmount);
        boolean flag=true;
        if(amount>balance)
        {
            JOptionPane.showMessageDialog(withdraw, "Please enter a smaller amount!",
                    "Warning", JOptionPane.ERROR_MESSAGE);
            flag=false;
        }

        return flag;

    }

}
