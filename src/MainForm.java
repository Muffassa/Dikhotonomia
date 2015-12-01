import sample.Function;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by muffass on 30.11.15.
 */
public class MainForm  {
    private JTextField x0TextField;
    private JTextField hTextField;
    private JTextPane resultField;
    private JButton calculateButton;
    private JLabel x0Label;
    private JLabel hLable;
    private JLabel eLable;
    private JLabel lLable;
    private JTextField eTextField;
    private JTextField lTextField;
    private JPanel mainPanel;
    private JTextField functionFiled;
    double x;
    double h;
    double eps;
    double l;

    MainForm()
    {


        JFrame jFrame = new JFrame("123");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(600, 400);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                x = Double.parseDouble(x0TextField.getText());
                h = Double.parseDouble(hTextField.getText());
                eps = Double.parseDouble(eTextField.getText());
                l = Double.parseDouble(lTextField.getText());


                Function function = new Function(functionFiled.getText());

                double result = function.findMin(x, h, eps, l);
                Double[] interval = function.getResultInterval();

                resultField.setText("Ответ: x = "+result+"\nНа интервале ["+interval[0]+";"+interval[1]+"]");
            }
        });

        jFrame.add(mainPanel);
        jFrame.setVisible(true);

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm();
            }
        });
    }

}
