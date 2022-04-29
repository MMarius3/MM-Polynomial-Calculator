package poly;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    View panel;

    public Controller(View panel) {
        this.panel = panel;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        panel.x = (panel.xValue.getText().isEmpty()) ? 0 : Integer.parseInt(panel.xValue.getText());

        if(command.equals("ok1"))  {
            String polynomialBuffer = panel.polynom1.getText();
            Polynom poly = this.buildPolynomial(polynomialBuffer);
            panel.firstPolyPoly.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>F(x) = " + poly.display() + "</b></p></html>");
            panel.firstPolyResult.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>F(" + poly.x + ") = " + poly.value() + "</b></p></html>");
            for(int i = 0; i < poly.monomString.length; i++) {
                System.out.println(poly.monomString[i]);
            }
        } else if(command.equals("ok2")) {
            String polynomialBuffer = panel.polynom2.getText();
            Polynom poly = this.buildPolynomial(polynomialBuffer);
            panel.secondPolyPoly.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>Q(x) = " + poly.display() + "</b></p></html>");
            panel.secondPolyResult.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>Q(" + poly.x + ") = " + poly.value() + "</b></p></html>");
        } else if(command.equals("plus")) {
            String polynomialBuffer1 = panel.polynom1.getText();
            String polynomialBuffer2 = panel.polynom2.getText();
            Polynom poly = this.add(polynomialBuffer1, polynomialBuffer2);
            panel.sumPoly.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>(F + Q)(x) = " + poly.display() + "</b></p></html>");
            panel.sumResult.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>(F + Q)(" + poly.x + ") = " + poly.value() + "</b></p></html>");
        } else if(command.equals("minus")) {
            String polynomialBuffer1 = panel.polynom1.getText();
            String polynomialBuffer2 = panel.polynom2.getText();
            Polynom poly = this.sub(polynomialBuffer1, polynomialBuffer2);
            panel.subPoly.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>(F - Q)(x) = " + poly.display() + "</b></p></html>");
            panel.subResult.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>(F - Q)(" + poly.x + ") = " + poly.value() + "</b></p></html>");
        } else if(command.equals("multiply")) {
            String polynomialBuffer1 = panel.polynom1.getText();
            String polynomialBuffer2 = panel.polynom2.getText();
            Polynom poly = this.multiply(polynomialBuffer1, polynomialBuffer2);
            panel.multiplyPoly.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>(F * Q)(x) = " + poly.display() + "</b></p></html>");
            panel.multiplyResult.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>(F * Q)(" + poly.x + ") = " + poly.value() + "</b></p></html>");
        } else if(command.equals("derivative")) {
            String polynomialBuffer = panel.polynom1.getText();
            Polynom poly = this.derivative(polynomialBuffer);
            panel.derivativePoly.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>F'(x) = " + poly.display() + "</b></p></html>");
            panel.derivativeResult.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>F'(" + poly.x + ") = " + poly.value() + "</b></p></html>");
        } else if(command.equals("integral")) {
            String polynomialBuffer = panel.polynom1.getText();
            Polynom poly = this.integral(polynomialBuffer);
            panel.integralPoly.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>\t∫F(x)dx = " + poly.doubleDisplay() + " + C</b></p></html>");
            panel.integralResult.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>\t∫F(x)dx(" + poly.x + ") = " + poly.value() + " + c</b></p></html>");
        }/* else if(command.equals("division")) {
            String polynomialBuffer1 = panel.polynom1.getText();
            String polynomialBuffer2 = panel.polynom2.getText();
            Polynom polyQuotient = this.divideQuotient(polynomialBuffer1, polynomialBuffer2);
            Polynom polyReminder = this.divideReminder(polynomialBuffer1, polynomialBuffer2);
            panel.divisionPoly.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>F(x) / Q(x) = " + polyQuotient.doubleDisplay() + "</b></p></html>");
            panel.divisionResult.setText("<html><p style='color:yellow;font-family:Helvetica;font-weight:100;line-height:15px;'><b>(F(x) / Q(x))(" + polyQuotient.x + ") = " + polyQuotient.value() + "</b></p></html>");
        }*/
    }

    private Polynom buildPolynomial(String polynomialBuffer) {
        String monomials[];
        String resultPolynomialBuffer;
            String actualPolynomialBuffer = (polynomialBuffer.contains("-")) ? polynomialBuffer.replace("-", "+-") : polynomialBuffer;
            resultPolynomialBuffer = (actualPolynomialBuffer.charAt(0) == '+') ? actualPolynomialBuffer.substring(1) : actualPolynomialBuffer;
            monomials = resultPolynomialBuffer.split("\\+");
            Polynom poly = new Polynom(monomials, panel.x);
            poly.clean();
            return poly;
    }

    private Polynom add(String polynomialBuffer1, String polynomialBuffer2) {
        Polynom poly1, poly2, polyResult;
        poly1 = buildPolynomial(polynomialBuffer1);
        poly2 = buildPolynomial(polynomialBuffer2);
        polyResult = poly1.add(poly2);
        polyResult.clean();
        return polyResult;
    }

    private Polynom sub(String polynomialBuffer1, String polynomialBuffer2) {
        Polynom poly1, poly2, polyResult;
        poly1 = buildPolynomial(polynomialBuffer1);
        poly2 = buildPolynomial(polynomialBuffer2);
        polyResult = poly1.sub(poly2);
        polyResult.clean();
        return polyResult;
    }

    private Polynom multiply(String polynomialBuffer1, String polynomialBuffer2) {
        Polynom poly1, poly2, polyResult;
        poly1 = buildPolynomial(polynomialBuffer1);
        poly2 = buildPolynomial(polynomialBuffer2);
        polyResult = poly1.multiply(poly2);
        polyResult.clean();
        return polyResult;
    }

    private Polynom derivative(String polynomialBuffer) {
        Polynom polyResult;
        polyResult = buildPolynomial(polynomialBuffer);
        polyResult.derivative();
        polyResult.clean();
        return polyResult;
    }

    private Polynom integral(String polynomialBuffer) {
        Polynom polyResult;
        polyResult = buildPolynomial(polynomialBuffer);
        polyResult.integral();
        polyResult.clean();
        return polyResult;
    }

    /*private Polynom divideQuotient(String polynomialBuffer1, String polynomialBuffer2) {
        Polynom poly1, poly2, polyResult;
        poly1 = buildPolynomial(polynomialBuffer1);
        poly2 = buildPolynomial(polynomialBuffer2);
        polyResult = poly1.divideQuotient(poly2);
        polyResult.clean();
        return polyResult;
    }

    private Polynom divideReminder(String polynomialBuffer1, String polynomialBuffer2) {
        Polynom poly1, poly2, polyResult;
        poly1 = buildPolynomial(polynomialBuffer1);
        poly2 = buildPolynomial(polynomialBuffer2);
        polyResult = poly1.divideReminder(poly2);
        polyResult.clean();
        return polyResult;
    }*/
}
