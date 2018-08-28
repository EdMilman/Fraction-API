package fraction;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * created by Edward Milman on 17/11/2017
 *
 * @author Edward Milman
 */


class FractionTest {

    private static Fraction a;
    private static Fraction b;
    private static Fraction c;
    private static Fraction d;
    private static Fraction e;
    private static Fraction f;
    private static Fraction g;
    private static Fraction h;
    private static Fraction i;
    private static Fraction j;
    private static Fraction k;
    private static Fraction l;
    private static Fraction m;
    private static Fraction n;
    private static Fraction o;
    private static Fraction p;

    @BeforeAll
    public static void setUp(){
        a = new Fraction(1, 2);
        b = new Fraction(3, 5);
        c = new Fraction(5, 10);
        d = new Fraction(-1, 2);
        e = new Fraction(1, -2);
        f = new Fraction(-50, 100);
        g = new Fraction(12, -36);
        h = new Fraction(1);
        i = new Fraction(-20);
        j = new Fraction("     1            ");
        k = new Fraction("               3       /       - 5");
        l = new Fraction("    66/   43");
        m = new Fraction("-      55      /87");
        n = new Fraction("0");
        o = new Fraction(0, 1);
        p = new Fraction(0);
    }

    @Test
    void add() {
        assertEquals(j, a.add(c));
        assertEquals(new Fraction(-1), d.add(e));
        assertEquals(new Fraction(-61, 3), i.add(g));
        assertEquals(new Fraction(-55, 87), m.add(n));
        assertEquals(n, c.add(d));
        assertEquals(o, o.add(p));
        assertEquals(o, h.subtract(h));

    }

    @Test
    void subtract() {
        assertEquals(p, a.subtract(a));
        assertEquals(new Fraction( -41, 2), i.subtract(a));
        assertEquals(n, o.subtract(p));
        assertEquals(f, a.subtract(j));
    }

    @Test
    void multiply() {
        assertEquals(new Fraction(3, 10), a.multiply(b));
        assertEquals(new Fraction(-1, 4), a.multiply(d));
        assertEquals(new Fraction(1, 4), d.multiply(d));
        assertEquals(o, i.multiply(n));
        assertEquals(p, p.multiply(p));
        assertEquals(new Fraction(1, 6), f.multiply(g));
    }

    @Test
    void divide() {
        assertEquals(j, a.divide(a));
        assertEquals(new Fraction(-1, 1), i.divide(i));
        assertEquals(new Fraction(-8, 35), new Fraction(1, 5).divide(new Fraction(-7, 8)));
    }

    @Test
    void divideBy0(){
        assertThrows(ArithmeticException.class,() -> a.divide(p));
    }

    @Test
    void abs() {
        assertEquals(a, a.abs());
        assertEquals(a, d.abs());
        assertEquals(a, f.abs());
        assertEquals(b, k.abs());
        assertEquals(new Fraction(41, 100), new Fraction(-41, 100).abs());
        assertEquals(o, p.abs());
    }

    @Test
    void negate() {
        assertEquals(a, d.negate());
        assertEquals(d, a.negate());
        assertEquals(b, k.negate());
        assertEquals(k, b.negate());
        assertEquals(o, p.negate());
    }

    @Test
    void inverse() {
        assertEquals(new Fraction(2), a.inverse());
        assertEquals(new Fraction(5, 3), b.inverse());
        assertEquals(new Fraction(-87, 55) , m.inverse());
        assertEquals(new Fraction(-2, 1), f.inverse());
        assertThrows(ArithmeticException.class, () -> o.inverse());
    }

    @Test
    void equals() {
        assertTrue(a.equals(c));
        assertTrue(d.equals(e));
        assertTrue(e.equals(f));
        assertFalse(a.equals(b));
        assertFalse(o.equals(4));
        assertFalse(o.equals("onion"));
    }

    @Test
    void compareTo() {
        assertTrue(a.compareTo(b) < 0);
        assertTrue(a.compareTo(c) == 0);
        assertTrue(e.compareTo(g) < 0);
        assertTrue(new Fraction(41, 100).compareTo(new Fraction(42, 100)) < 0);
    }

    @Test
    void compareToThrow(){
        assertThrows(ClassCastException.class, () -> o.compareTo(4));
        assertThrows(ClassCastException.class, () -> i.compareTo("i wont work"));
        assertThrows(ClassCastException.class, () -> i.compareTo(Math.PI));
    }

    @Test
    void testToString() {
        // test standard
        assertEquals("1/2", a.toString());
        assertEquals("3/5", b.toString());
        // test normalised
        assertEquals("1/2", c.toString());
        // test neg
        assertEquals("-1/2", d.toString());
        assertEquals("-1/2", e.toString());
        // test normalised neg
        assertEquals("-1/2", f.toString());
        assertEquals("-1/3" , g.toString());
        // test 'wholenumber' constructor
        assertEquals("1/1", h.toString());
        assertEquals("-20/1", i.toString());
        // test "string" constructor
        assertEquals("1/1", j.toString());
        assertEquals("-3/5", k.toString());
        assertEquals("66/43", l.toString());
        assertEquals("-55/87", m.toString());
        // test 0
        assertEquals("0/1", n.toString());
        assertEquals("0/1", o.toString());
        assertEquals("0/1", p.toString());
    }

    @Test
    void testToStringThrow(){
        assertThrows(ArithmeticException.class, ()-> f = new Fraction(1,0));
        assertThrows(ArithmeticException.class, ()-> f = new Fraction(100000,0));
        assertThrows(ArithmeticException.class, ()-> f = new Fraction(0,0));
        assertThrows(NumberFormatException.class, () -> f = new Fraction("this should not work"));
    }

    @Test
    void GCD() {
        assertEquals(4, f.GCD(4, 12));
        assertEquals(5, f.GCD(5, 20));
        assertEquals(2, f.GCD(100, 2));
        assertEquals(1, f.GCD(3, 5));
    }

    @Test
    void LCD(){
        assertEquals(10, f.LCD(2, 5));
        assertEquals(332, f.LCD(4, 83));
        assertEquals(456, f.LCD(19, 24));
    }
}