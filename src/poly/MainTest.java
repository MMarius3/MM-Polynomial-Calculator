package poly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testAddition1() {

        String[] monomString1 = {"4x^2", "3x", "1"};
        String[] monomString2 = {"9x" , "2"};

        Polynom poly1 = new Polynom(monomString1, 5);
        Polynom poly2 = new Polynom(monomString2, 5);
        Polynom polyResult = poly1.add(poly2);
        assertEquals("3+12x+4x^2", polyResult.display());
    }

    @Test
    void testAddition2() {

        String[] monomString1 = {"x", "-9"};
        String[] monomString2 = {"5x^2"};

        Polynom poly1 = new Polynom(monomString1, 5);
        Polynom poly2 = new Polynom(monomString2, 5);
        Polynom polyResult = poly1.add(poly2);
        assertEquals("-9+x+5x^2", polyResult.display());
    }

    @Test
    void testSubtraction1() {

        String[] monomString1 = {"4x^2", "3x", "1"};
        String[] monomString2 = {"9x" , "2"};

        Polynom poly1 = new Polynom(monomString1, 5);
        Polynom poly2 = new Polynom(monomString2, 5);
        Polynom polyResult = poly1.sub(poly2);
        assertEquals("-1-6x+4x^2", polyResult.display());
    }

    @Test
    void testSubtraction2() {

        String[] monomString1 = {"x", "-9"};
        String[] monomString2 = {"5x^2"};

        Polynom poly1 = new Polynom(monomString1, 5);
        Polynom poly2 = new Polynom(monomString2, 5);
        Polynom polyResult = poly1.sub(poly2);
        assertEquals("-9+x+5x^2", polyResult.display());
    }

    @Test
    void testMultiplication1() {

        String[] monomString1 = {"4x^2", "3x", "1"};
        String[] monomString2 = {"9x" , "2"};

        Polynom poly1 = new Polynom(monomString1, 5);
        Polynom poly2 = new Polynom(monomString2, 5);
        Polynom polyResult = poly1.multiply(poly2);
        assertEquals("2+15x+35x^2+36x^3", polyResult.display());
    }

    @Test
    void testMultiplication2() {

        String[] monomString1 = {"x", "-9"};
        String[] monomString2 = {"5x^2"};

        Polynom poly1 = new Polynom(monomString1, 5);
        Polynom poly2 = new Polynom(monomString2, 5);
        Polynom polyResult = poly1.multiply(poly2);
        assertEquals("-45x^2+5x^3", polyResult.display());
    }

    @Test
    void testDerivation1() {

        String[] monomString = {"4x^2", "3x", "1"};

        Polynom poly = new Polynom(monomString, 5);
        Polynom polyResult = poly.derivative();
        polyResult.clean();
        assertEquals("3+8x", polyResult.display());
    }

    @Test
    void testDerivation2() {

        String[] monomString = {"x", "-9"};

        Polynom poly = new Polynom(monomString, 5);
        Polynom polyResult = poly.derivative();
        polyResult.clean();
        assertEquals("1", polyResult.display());
    }

    @Test
    void testIntegration1() {

        String[] monomString = {"4x^2", "3x", "1"};

        Polynom poly = new Polynom(monomString, 5);
        Polynom polyResult = poly.integral();
        assertEquals("x+1.5x^2+1.3333333333333333x^3", polyResult.doubleDisplay());
    }

    @Test
    void testIntegration2() {

        String[] monomString = {"x", "-9"};

        Polynom poly = new Polynom(monomString, 5);
        Polynom polyResult = poly.integral();
        assertEquals("-9.0x+0.5x^2", polyResult.doubleDisplay());
    }
}