package poly;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class View {
    public JFrame mainFrame;
    public JLabel firstPolyResult, firstPolyPoly,
                    secondPolyResult, secondPolyPoly,
                    sumResult, sumPoly,
                    subResult, subPoly,
                    multiplyResult, multiplyPoly,
                    derivativeResult, derivativePoly,
                    integralResult, integralPoly;
                    //divisionResult, divisionPoly;
    public JPanel value, poly1, poly2;
    public JTextField polynom1, polynom2, xValue;
    public JButton addition, subtraction, multiplication, derivation, integration;
    //division;
    public int x;

    public View() {
        GUI();
    }

    private void GUI() {
        JLabel headerLabel;
        mainFrame = new JFrame("Polynomial Calculator");
        mainFrame.setSize(444, 650);
        mainFrame.setLayout(new GridLayout(1, 1));
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.darkGray);
        mainFrame.add(mainPanel);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(0, 1));
        headerPanel.setBackground(Color.darkGray);
        headerLabel = new JLabel("", JLabel.CENTER);
        headerLabel.setText("<html><font color='white'>Enter data here:</font></html>");
        headerPanel.add(headerLabel);

        value = new JPanel();
        value.setLayout(new FlowLayout());
        poly1 = new JPanel();
        poly1.setLayout(new FlowLayout());
        poly2 = new JPanel();
        poly2.setLayout(new FlowLayout());

        xValue = new JTextField(5);
        polynom1 = new JTextField(20);
        polynom2 = new JTextField(20);

        value.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.darkGray));
        value.setBackground(Color.darkGray);
        value.add(new JLabel("<html><font color='white'>x= </font></html>x= ", JLabel.LEFT));
        value.add(xValue);

        poly1.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.darkGray));
        poly1.setBackground(Color.darkGray);
        poly1.add(new JLabel("<html><font color='white'>First Polynomial:</font></html>", JLabel.LEFT));
        poly1.add(polynom1);

        poly2.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.darkGray));
        poly2.setBackground(Color.darkGray);
        poly2.add(new JLabel("<html><font color='FFFFFF'>Scnd Polynomial:</font></html>", JLabel.LEFT));
        poly2.add(polynom2);

        firstPolyResult = new JLabel("", JLabel.LEFT);
        firstPolyResult.setPreferredSize(new Dimension(333,21));
        firstPolyResult.setText("<html><font color='FFFFFF'>First polynomial details</font></html>");
        firstPolyPoly = new JLabel("",JLabel.LEFT);
        firstPolyPoly.setVerticalTextPosition(JLabel.TOP);
        firstPolyPoly.setPreferredSize(new Dimension(333,21));
        JPanel firstPolyPanel = new JPanel();
        firstPolyPanel.setLayout(new GridLayout(2,1));
        firstPolyPanel.setBackground(Color.darkGray);
        firstPolyPanel.add(firstPolyPoly);
        firstPolyPanel.add(firstPolyResult);
        firstPolyPanel.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.darkGray));

        secondPolyResult = new JLabel("", JLabel.LEFT);
        secondPolyResult.setPreferredSize(new Dimension(333,21));
        secondPolyResult.setText("<html><font color='FFFFFF'>Second polynomial details</font></html>");
        secondPolyPoly = new JLabel("",JLabel.LEFT);
        secondPolyPoly.setVerticalTextPosition(JLabel.TOP);
        secondPolyPoly.setPreferredSize(new Dimension(333,21));
        JPanel secondPolyPanel = new JPanel();
        secondPolyPanel.setLayout(new GridLayout(2,1));
        secondPolyPanel.setBackground(Color.darkGray);
        secondPolyPanel.add(secondPolyPoly);
        secondPolyPanel.add(secondPolyResult);
        secondPolyPanel.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.darkGray));

        sumResult = new JLabel("", JLabel.LEFT);
        sumResult.setPreferredSize(new Dimension(333,28));
        sumResult.setText("<html><font color='FFFFFF'>The SUM</font></html>");
        sumPoly = new JLabel("",JLabel.LEFT);
        sumPoly.setVerticalTextPosition(JLabel.TOP);
        sumPoly.setPreferredSize(new Dimension(333,28));
        JPanel sumPanel = new JPanel();
        sumPanel.setLayout(new GridLayout(2,1));
        sumPanel.setBackground(Color.darkGray);
        sumPanel.add(sumPoly);
        sumPanel.add(sumResult);
        sumPanel.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.darkGray));

        subResult = new JLabel("", JLabel.LEFT);
        subResult.setPreferredSize(new Dimension(333,28));
        subResult.setText("<html><font color='FFFFFF'>The SUB</font></html>");
        subPoly = new JLabel("",JLabel.LEFT);
        subPoly.setVerticalTextPosition(JLabel.TOP);
        subPoly.setPreferredSize(new Dimension(333,28));
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(2,1));
        subPanel.setBackground(Color.darkGray);
        subPanel.add(subPoly);
        subPanel.add(subResult);
        subPanel.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.darkGray));

        multiplyResult = new JLabel("", JLabel.LEFT);
        multiplyResult.setPreferredSize(new Dimension(333,28));
        multiplyResult.setText("<html><font color='FFFFFF'>The PRODUCT</font></html>");
        multiplyPoly = new JLabel("",JLabel.LEFT);
        multiplyPoly.setVerticalTextPosition(JLabel.TOP);
        multiplyPoly.setPreferredSize(new Dimension(333,28));
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(2,1));
        productPanel.setBackground(Color.darkGray);
        productPanel.add(multiplyPoly);
        productPanel.add(multiplyResult);
        productPanel.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.darkGray));

        derivativeResult = new JLabel("", JLabel.LEFT);
        derivativeResult.setPreferredSize(new Dimension(333,28));
        derivativeResult.setText("<html><font color='FFFFFF'>The DERIVATIVE of the first polynomial</font></html>");
        derivativePoly = new JLabel("",JLabel.LEFT);
        derivativePoly.setVerticalTextPosition(JLabel.TOP);
        derivativePoly.setPreferredSize(new Dimension(333,28));
        JPanel derivPanel = new JPanel();
        derivPanel.setLayout(new GridLayout(2,1));
        derivPanel.setBackground(Color.darkGray);
        derivPanel.add(derivativePoly);
        derivPanel.add(derivativeResult);
        derivPanel.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.darkGray));

        integralResult = new JLabel("", JLabel.LEFT);
        integralResult.setPreferredSize(new Dimension(333,28));
        integralResult.setText("<html><font color='FFFFFF'>The INTEGRAL of the first polynomial</font></html>");
        integralPoly = new JLabel("",JLabel.LEFT);
        integralPoly.setVerticalTextPosition(JLabel.TOP);
        integralPoly.setPreferredSize(new Dimension(333,28));
        JPanel integPanel = new JPanel();
        integPanel.setLayout(new GridLayout(2,1));
        integPanel.setBackground(Color.darkGray);
        integPanel.add(integralPoly);
        integPanel.add(integralResult);
        integPanel.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.darkGray));

        /*divisionResult = new JLabel("", JLabel.LEFT);
        divisionResult.setPreferredSize(new Dimension(333,28));
        divisionResult.setText("<html><font color='FFFFFF'>The DIVISION of the first polynomial</font></html>");
        divisionPoly = new JLabel("",JLabel.LEFT);
        divisionPoly.setVerticalTextPosition(JLabel.TOP);
        divisionPoly.setPreferredSize(new Dimension(333,28));
        JPanel divisionPanel = new JPanel();
        divisionPanel.setLayout(new GridLayout(2,1));
        divisionPanel.setBackground(Color.darkGray);
        divisionPanel.add(divisionPoly);
        divisionPanel.add(divisionResult);
        divisionPanel.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.darkGray));*/

        JPanel operators = new JPanel();
        operators.setLayout(new FlowLayout());
        GridLayout op = new GridLayout(1,5);
        operators.setLayout(op);
        addition = View.createSimpleButton("+");
        subtraction = View.createSimpleButton("-");
        multiplication = View.createSimpleButton("x");
        derivation = View.createSimpleButton("'");
        integration = View.createSimpleButton("\t∫");
        //division = View.createSimpleButton("/");
        operators.add(addition);
        operators.add(subtraction);
        operators.add(multiplication);
        operators.add(derivation);
        operators.add(integration);
        //operators.add(division);

        mainPanel.add(headerPanel);
        mainPanel.add(value);
        mainPanel.add(poly1);
        mainPanel.add(poly2);
        mainPanel.add(operators);
        mainPanel.add(firstPolyPanel);
        mainPanel.add(secondPolyPanel);
        mainPanel.add(sumPanel);
        mainPanel.add(subPanel);
        mainPanel.add(productPanel);
        mainPanel.add(derivPanel);
        mainPanel.add(integPanel);
        //mainPanel.add(divisionPanel);
        mainPanel.setVisible(true);

    }

    private static JButton createSimpleButton(String text) {
        JButton button = new JButton("<html><p style='font-size:21px;font-family:Helvetica;'>"+text+"</p></html>");
        button.setForeground(Color.yellow);
        button.setBackground(Color.darkGray);
        Border line = BorderFactory.createLineBorder(Color.darkGray);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        return button;
    }

    public void polynomialCalculatorPanel(){
        JButton first_ok = new JButton("<html><p style='color:black;font-family:Helvetica;font-weight:300;line-height:100px;'><b>&#9989</b></p></html>");
        JButton second_ok =  new JButton("<html><p style='color:black;font-family:Helvetica;font-weight:300;line-height:100px;'><b>&#9989</b></p></html>");
        first_ok.setActionCommand("ok1");
        second_ok.setActionCommand("ok2");
        addition.setActionCommand("plus");
        subtraction.setActionCommand("minus");
        multiplication.setActionCommand("multiply");
        derivation.setActionCommand("derivative");
        integration.setActionCommand("integral");
        //division.setActionCommand("division");
        first_ok.addActionListener(new Controller(this));
        second_ok.addActionListener(new Controller(this));
        addition.addActionListener(new Controller(this));
        subtraction.addActionListener(new Controller(this));
        multiplication.addActionListener(new Controller(this));
        derivation.addActionListener(new Controller(this));
        integration.addActionListener(new Controller(this));
        //division.addActionListener(new Controller(this));

        poly1.add(first_ok);
        poly2.add(second_ok);

        mainFrame.setVisible(true);
    }
}
