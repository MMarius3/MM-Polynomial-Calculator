package poly;

import java.util.ArrayList;
import java.util.Collections;

public class Polynom {
    String[] monomString;
    ArrayList<Monom> monom = new ArrayList<Monom>();
    public int x;

    public Polynom(String[] monomString, int x) {
        this.monomString = monomString;
        this.x = x;
        for(String monomial : monomString) {
            System.out.println(monomial);
            monom.add(new Monom(monomial));
        }
        for(Monom monomial : monom) {
            Monom temp = monomial;
            temp.display();
        }
        this.sortByPower();
    }

    private Polynom(int x) {
        this.x = x;
    }

    public void clean() {
        for(int i=0;i<monom.size();i++) {
            if(monom.get(i).getCoef() == 0) monom.remove(i);
        }
    }

    public String display() {
        String resultPoly = new String("");
        for(Monom monomial : monom) {
            if(monomial.coef < 0) {
                resultPoly += monomial.getMonom();
            } else {
                resultPoly += (monom.indexOf(monomial) == 0) ? monomial.getMonom() : "+" + monomial.getMonom();
            }
            System.out.println(resultPoly);
        }
        System.out.println(resultPoly);
        return resultPoly;
    }

    public String doubleDisplay() {
        String resultPoly = new String("");
        for(Monom monomial : monom) {
            if(monomial.dcoef < 0) {
                resultPoly += monomial.getDoubleMonom();
            } else {
                resultPoly += (monom.indexOf(monomial) == 0) ? monomial.getDoubleMonom() : "+" + monomial.getDoubleMonom();
            }
            System.out.println(resultPoly);
        }
        System.out.println(resultPoly);
        return resultPoly;
    }

    public double value() {
        double s = monom.stream().mapToDouble(monom -> monom.value(x)).sum();
        return s;
    }

    private void sortByPower() {
        for(int i=0;i<monom.size();i++) {
            int power = monom.get(i).getPower();
            for(int j=i+1;j<monom.size();j++) {
                int temp_power = monom.get(j).getPower();
                if(power == temp_power) {
                    monom.get(i).addCoef(monom.get(j).getCoef());
                    this.monom.remove(j);
                }
            }
        }
        Collections.sort(this.monom, Monom.getCompByPower());
    }

    /*public int getDegree() {
        int degree = -1;
        for(Monom monomial : this.monom) {
            if(degree < monomial.getPower()) degree = monomial.getPower();
        }
        return degree;
    }*/

    public Polynom derivative() {
        Polynom polyResult = new Polynom(x);
        for(Monom monomial : this.monom) {
            Monom temp = monomial;
            temp.derivative();
            polyResult.monom.add(new Monom(temp.getCoef(), temp.getPower()));
        }
        return polyResult;
    }

    public Polynom integral() {
        Polynom polyResult = new Polynom(x);
        for(Monom monomial : this.monom) {
            Monom temp = monomial;
            temp.integral();
            polyResult.monom.add(new Monom(temp.getDCoef(), temp.getCoef(), temp.getPower()));
        }
        return polyResult;
    }

    public Polynom add(Polynom poly2) {
        Polynom poly3 = new Polynom(x);

        for(Monom monomial : this.monom) {
            int p1 = monomial.getPower();
            int c1 = monomial.getCoef();

            int i = poly2.getPowerIndex(p1);
            if(i == -1) {
                poly3.monom.add(new Monom(c1, p1));
            } else {
                int p3 = poly2.monom.get(i).getPower();
                int c3 = poly2.monom.get(i).getCoef();
                poly3.monom.add(new Monom(c1+c3, p3));
                poly2.monom.remove(i);
            }
        }
        for(Monom monomial : poly2.monom) {
            int p2 = monomial.getPower();
            int c2 = monomial.getCoef();
            poly3.monom.add(new Monom(c2, p2));
        }
        return poly3;
    }

    public Polynom sub(Polynom poly2) {
        Polynom poly3 = new Polynom(x);

        for(Monom monomial : this.monom) {
            int p1 = monomial.getPower();
            int c1 = monomial.getCoef();

            int i = poly2.getPowerIndex(p1);
            if(i == -1) {
                poly3.monom.add(new Monom(c1, p1));
            } else {
                int p3 = poly2.monom.get(i).getPower();
                int c3 = poly2.monom.get(i).getCoef();
                poly3.monom.add(new Monom(c1-c3, p3));
                poly2.monom.remove(i);
            }
        }
        for(Monom monomial : poly2.monom) {
            int p2 = monomial.getPower();
            int c2 = monomial.getCoef();
            poly3.monom.add(new Monom(c2, p2));
        }
        return poly3;
    }

    public Polynom multiply(Polynom poly2) {
        Polynom poly3 = new Polynom(x);

        for(Monom monomial : this.monom) {
            int p1 = monomial.getPower();
            int c1 = monomial.getCoef();
            for(Monom monomial2 : poly2.monom)
            {
                int p2 = monomial2.getPower();
                int c2 = monomial2.getCoef();
                int i = poly3.getPowerIndex(p1+p2);
                if(i == -1)
                {
                    poly3.monom.add(new Monom(c1 * c2, p1 + p2));
                } else {
                    int c3 = poly3.monom.get(i).getCoef();
                    poly3.monom.get(i).coef = c1 * c2 + c3;
                }
            }
        }
        return poly3;
    }

    /*boolean equalsZero() {
        for(Monom monomial : this.monom) {
            if(monomial.getCoef() != 0) return false;
        }
        return true;
    }*/

    /*public Polynom divideQuotient(Polynom poly2) {
        Polynom quotientPoly = new Polynom(x);
        Polynom reminderPoly = this;

        while(!reminderPoly.equalsZero() && reminderPoly.getDegree() >= poly2.getDegree()) {
            int i = reminderPoly.getPowerIndex(reminderPoly.getDegree());
            int j = poly2.getPowerIndex(poly2.getDegree());
            String[] monomString = {""};
            Monom t = new Monom(reminderPoly.monom.get(i).dcoef / poly2.monom.get(j).dcoef,
                    reminderPoly.monom.get(i).coef / poly2.monom.get(j).coef,
                    reminderPoly.monom.get(i).power - reminderPoly.monom.get(j).power);
            monomString[0] = t.monomial;
            Polynom polyT = new Polynom(monomString, x);
            quotientPoly = quotientPoly.add(polyT);
            reminderPoly = reminderPoly.sub(polyT.multiply((poly2)));
            quotientPoly.clean();
            reminderPoly.clean();
            quotientPoly.display();
            reminderPoly.display();
        }
        return quotientPoly;
    }

    public Polynom divideReminder(Polynom poly2) {
        Polynom quotientPoly = new Polynom(x);
        Polynom reminderPoly = this;

        while(!reminderPoly.equalsZero() && reminderPoly.getDegree() >= poly2.getDegree()) {
            int i = reminderPoly.getPowerIndex(reminderPoly.getDegree());
            int j = poly2.getPowerIndex(poly2.getDegree());
            String[] monomString = {""};
            Monom t = new Monom("");
            t.dcoef = reminderPoly.monom.get(i).dcoef / poly2.monom.get(j).dcoef;
            t.power = reminderPoly.monom.get(i).power - reminderPoly.monom.get(j).power;
            monomString[0] = t.monomial;
            Polynom polyT = new Polynom(monomString, x);
            quotientPoly = quotientPoly.add(polyT);
            reminderPoly = reminderPoly.sub(polyT.multiply((poly2)));
        }
        return reminderPoly;
    }*/

    private int getPowerIndex(int power) {
        for(Monom monomial : monom) {
            Monom mon = monomial;
            if(power == mon.getPower())
                return monom.indexOf(monomial);
        }
        return -1;
    }
}
