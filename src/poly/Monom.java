package poly;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monom {
    public String monomial;
    public int power;
    public int coef;
    public double dcoef;
    private String[] monomialBuffer;

    public Monom(String monomial) {
        if(validate(monomial))
        {
            this.monomial = monomial;
        }
        dcoef = (double)coef;
    }

    public Monom(int coef, int power) {
        this.power = power;
        this.coef = coef;
        this.dcoef = coef;
        this.monomial = format();
    }

    public Monom(double dcoef, int coef, int power) {
        this.power = power;
        this.coef = coef;
        this.dcoef = dcoef;
        this.monomial = format();
    }

    public void derivative() {
        if(power != 0) {
            coef = coef * power;
            dcoef = (int) coef;
            power--;
        } else {
            coef = 0;
        }
    }
    public void integral() {
            dcoef = (double) coef;
            power++;
            dcoef = dcoef / power;
    }

    private String format() {
        String monomialFormal = new String(this.coef + "x^" + this.power);
        return monomialFormal;
    }

    public String getMonom() {
        if(coef == 1) {
            if(power == 0) return "1";
            else if(power == 1) return "x";
            else return "x^" + power;
        } else if(coef == -1) {
            if(power == 0) return "-1";
            else if(power == 1) return "-x";
            else return "-x^" + power;
        } else {
            if(power == 0) return Integer.toString(coef);
            else if(power == 1) return coef + "x";
            else return coef + "x^" + power;
        }
    }

    public String getDoubleMonom() {
        if(dcoef == 1) {
            if(power == 0) return "1";
            else if(power == 1) return "x";
            else return "x^" + power;
        } else if(dcoef == -1) {
            if(power == 0) return "-1";
            else if(power == 1) return "-x";
            else return "-x^" + power;
        } else {
            if(power == 0) return Double.toString(dcoef);
            else if(power == 1) return dcoef + "x";
            else return dcoef + "x^" + power;
        }
    }

    public void display() {
        System.out.println("Power: " + power + " Coefficient: " + coef + "\n");
    }

    public int getCoef() {
        return this.coef;
    }

    public double getDCoef() {
        return this.dcoef;
    }

    public int getPower() {
        return this.power;
    }

    public int value(int x) {
        return (int) (dcoef * Math.pow(x, power));
    }

    public void addCoef(int x) {
        this.coef += x;
        this.dcoef = (double)coef;
    }

    public static Comparator<Monom> getCompByPower() {
        Comparator<Monom> comp = new Comparator<Monom>(){
            @Override
            public int compare(Monom s1, Monom s2) {
                return Integer.compare(s1.power, s2.power);
            }
        };
        return comp;
    }

    private boolean validate(String monomial) {
        if(!monomial.matches("^[\\^\\- a-zA-Z0-9]*")) return false;
        Pattern polyFormat = Pattern.compile("\\^");
        Matcher monomialMatcher = polyFormat.matcher(monomial);
        String groupedBuffer = new String();
        while(monomialMatcher.find()) { groupedBuffer = monomialMatcher.group(); }
        if(groupedBuffer.isEmpty()) {
            monomialBuffer = monomial.split("[a-zA-Z]");
            if(monomialBuffer.length == 0) {
                coef = power = 1;
            } else {
                coef = monomialBuffer[0].isEmpty() ? 1 : Integer.parseInt(monomialBuffer[0]);
                power = (monomialBuffer[0] == monomial) ? 0 : 1;
            }
        } else {
            monomialBuffer = monomial.split("\\^");
            try {
                String currentBuffer = new String();
                for(int i = 0; i < monomialBuffer[0].length(); i++){
                    char c = monomialBuffer[0].charAt(i);
                    if(c==45 || (c >= 46 && c <= 57)) currentBuffer += c;
                }
                coef = (currentBuffer.isEmpty()) ? 1 : Integer.parseInt(currentBuffer);
                power = Integer.parseInt(monomialBuffer[1]);
            } catch(NumberFormatException e) {
                System.out.println("The format is invalid.");
            }
        }
        return true;
    }
}
