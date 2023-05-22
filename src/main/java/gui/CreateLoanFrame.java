package gui;

import model.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateLoanFrame extends JFrame{
    JFrame frame = new Template();
    private JLabel header;
    private JLabel label1;
    private JTextField amountField;
    private JLabel amountLabel;
    private JTextField dosesField;
    private JLabel dosesLabel;
    private JLabel dosesInformation;
    private JLabel reasonLabel;
    private JComboBox<String> dropDownList;
    private JButton submitButton;
    private JButton returnToTheMainPageButton;
    private double loanAmount;
    private double balance = 1000; //ΘΑ ΠΑΙΡΝΕΙ ΤΙΜΗ ΑΠΟ ΤΗΝ ΒΑΣΗ
    private int doses;

    public CreateLoanFrame(){
        header = Utils.setHeader("Take a loan");
        header.setBounds(500,100,300,50);

        label1 = new JLabel("Enter your information to register the loan");
        label1.setBounds(500, 110, 500, 100 );

        amountField = new JTextField("The max amount you can loan is: " +balance*5);
        amountLabel = new JLabel("Amount");
        amountLabel.setBounds(450,250,50,50);
        amountField.setBounds(500,260,230,35);
        amountField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                amountField.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        dosesField = new JTextField();
        dosesLabel = new JLabel("Doses");
        dosesInformation = new JLabel("You can select 1 to 12 doses");
        dosesLabel.setBounds(450,310,50,50);
        dosesField.setBounds(500,320,230,35);
        dosesInformation.setBounds(500,350,200,35);

        reasonLabel = new JLabel("Reason");
        String[] choices = { "I want to make a big purchace","I want to start my business", "I have a health issue","I want to buy a house","Other"};
        dropDownList = new JComboBox<String>(choices);
        reasonLabel.setBounds(450,390,50,50);
        dropDownList.setBounds(500,400,200,35);

        submitButton = new JButton("Submit");
        submitButton.setBounds(500,550,200,35);

        returnToTheMainPageButton = Utils.returnToMainPageButton(frame);
        returnToTheMainPageButton.setBounds(970,720,200,35);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String loanAmountString = amountField.getText();
                String dosesString = dosesField.getText();

                if(loanAmountString.matches("[0-9]+"))
                    loanAmount = Double.parseDouble(loanAmountString);
                else
                    JOptionPane.showMessageDialog(submitButton, "Enter a valid amount", "ERROR", JOptionPane.ERROR_MESSAGE);

                if(dosesString.matches("[0-9]+"))
                    doses = Integer.parseInt(dosesString);
                else
                    JOptionPane.showMessageDialog(submitButton, "Enter a valid dose", "ERROR", JOptionPane.ERROR_MESSAGE);

                if(doses<1 || doses>12)
                    JOptionPane.showMessageDialog(submitButton, "You can select 1 to 12 doses", "ERROR", JOptionPane.ERROR_MESSAGE);

                if(CheckLoanAmount(loanAmount, balance) && (doses>=1 && doses<=12) && loanAmountString.matches("[0-9]+") && dosesString.matches("[0-9]+")){
                    //frame success
                    double totalAmount = CalculateTotalLoanAmount(loanAmount, doses);
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                    String currentDate = formatter.format(date);
                    new LoanSuccessFrame(currentDate, doses);
                    frame.dispose(); //με το που πατάμε το κουμπί ανοίγει το LoanSuccessFrame και κλείνει το παράθυρο των δανείων.
                }

                if(!CheckLoanAmount(loanAmount, balance)){
                    //frame denied
                    new LoanDeniedFrame();
                    frame.dispose();
                }
            }

        });

        frame.add(header);
        frame.add(label1);
        frame.add(amountField);
        frame.add(amountLabel);
        frame.add(dosesField);
        frame.add(dosesLabel);
        frame.add(dosesInformation);
        frame.add(reasonLabel);
        frame.add(dropDownList);
        frame.add(submitButton);
        frame.add(returnToTheMainPageButton);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public boolean CheckLoanAmount(double loanAmount, double balance){
        if(balance*5 >= loanAmount)
            return true;
        else
            return  false;
    }

    public double CalculateTotalLoanAmount(double loanAmount, int doses){
        double totalLoanAmount = 0.0;
        double interests; //τόκοι
        if(doses <= 6)
            interests = loanAmount * 0.2;
        else
            interests = loanAmount * 0.04;

        totalLoanAmount = loanAmount +interests;
        return totalLoanAmount;
    }
}
