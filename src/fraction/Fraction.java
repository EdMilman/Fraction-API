package fraction;

/**
 * A fraction API to represent any fraction, and a variety of mathematical operations that can be performed on
 * fractions
 *
 * @author Edward Milman
 */
public class Fraction implements Comparable {
    private int numerator;
    private int denominator;
    private static final int WHOLE_NUMBER_DENOM = 1;

    /**
     * constructor for a fraction when given both numerator and denominator. will normalise given values. negative
     * fractions are represented as -1/2
     *
     * @param numerator   - integer for the numerator
     * @param denominator - integer for the denominator
     */
    public Fraction(int numerator, int denominator) {
        normalise(numerator, denominator);
    }

    /**
     * alternative constructor when given just a whole integer. will set the fraction to be the number given / 1
     *
     * @param wholeNumber - integer to be set as numerator
     */
    public Fraction(int wholeNumber) {
        this(wholeNumber, WHOLE_NUMBER_DENOM);
    }

    /**
     * constructor for a fraction when given a string. any alphabetic characters input will throw an exception.
     * any white space in the string will be removed before parsing, and remaining numbers and / will be dealt
     * with accordingly
     *
     * @param fraction - string to be processed
     */
    public Fraction(String fraction) {
        // throw exception to alpha characters
        if (fraction.matches(".*[a-z].*")) {
            throw new NumberFormatException("Fraction will only accept integer numbers");
        }
        // remove all whitespace
        fraction = fraction.replaceAll("\\s+", "");
        // if string contains '/' split and process, else process single integer
        if (fraction.contains("/")) {
            String[] str = fraction.split("/");
            normalise(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        } else {
            this.numerator = Integer.parseInt(fraction);
            this.denominator = WHOLE_NUMBER_DENOM;
        }
    }

    /**
     * will sum a given fraction to the fraction being operated on
     * @param f - the fraction to be added
     * @return new fraction of the sum of the 2 fractions
     */
    public Fraction add(Fraction f) {
        int numerator = (this.numerator * f.denominator) + (f.numerator * this.denominator);
        int denominator = this.denominator * f.denominator;
        // corner case (-1/2 + 1/2) || (-5/6 + 5/6) will not normalise to 0/1
        if (numerator == 0) {
            denominator = 1;
        }
        return new Fraction(numerator, denominator);
    }

    /**
     * will subtract a given fraction from the fraction being operated on
     * @param f - the fraction to be subtracted
     * @return new fraction of the result of the subtraction
     */
    public Fraction subtract(Fraction f) {
        int numerator = (this.numerator * f.denominator) - (f.numerator * this.denominator);
        int denominator = this.denominator * f.denominator;
        if (numerator == 0) {
            denominator = 1;
        }
        return new Fraction(numerator, denominator);
    }

    /**
     * will multiply a given fraction by the fraction being operated on
     * @param f - the fraction to be multiplied by
     * @return new fraction of the result of the multiplication
     */
    public Fraction multiply(Fraction f) {
        return new Fraction(this.numerator * f.numerator, this.denominator * f.denominator);
    }

    /**
     * will divide a given fraction being operated on with the fraction given
     * @param f - the fraction to be divided with
     * @return new fraction of the result of the division
     */
    public Fraction divide(Fraction f) {
        if (f.numerator == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return new Fraction(this.numerator * f.denominator, this.denominator * f.numerator);
    }

    /**
     * gives an absolute value of any fraction operated on
     * @return new fraction with the absolute value
     */
    public Fraction abs() {
        return new Fraction(Math.abs(this.numerator), this.denominator);
    }

    /**
     * gives a negated value of the fraction operated on
     * @return new fraction of negated value of the fraction
     */
    public Fraction negate() {
        return new Fraction(this.numerator * -1, this.denominator);
    }

    /**
     * will invert the fraction being operated on a/b becomes b/a
     * @return a new fraction of the inverse of the original
     */
    public Fraction inverse() {
        return new Fraction(this.denominator, numerator);
    }

    /**
     * equals function to compare a fraction with an object
     * @param o - the object to compare the fraction to
     * @return boolean, false if the fraction equals the object, true otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Fraction)) {
            return false;
        }
        Fraction f = (Fraction) o;
        return (this.numerator != f.numerator || this.denominator != f.denominator) ? false : true;
    }

    /**
     * function to compare a fraction with another object. throws an exception if compared to a non fraction
     * @param o the object to be compared to
     * @return 0 if the fractions are equal, a negative integer if the fraction is less than the argument, a positive
     * integer otherwise
     */
    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Fraction)) {
            throw new ClassCastException("Fractions can only be compared to other fractions.");
        }
        Fraction f = (Fraction) o;
        if (this.equals(f)) {
            return 0;
        }
        int lcd = LCD(this.denominator, f.denominator);
        return (this.numerator * (lcd / this.denominator)) - (f.numerator * (lcd / f.denominator));
    }

    /**
     * function to print a user friendly version of the fraction
     * @return string format of the fraction
     */
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    /**
     * helper function that will find the greatest common divisor of 2 given integers
     * @param a first integer
     * @param b second integer
     * @return integer greatest common divisor of the given arguments
     */
    protected int GCD(int a, int b) {
        if (a != 0) {
            while (a != b) {
                if (a > b) {
                    a -= b;
                } else {
                    b -= a;
                }
            }
        }
        return b;
    }

    /**
     * helper function that will find the lowest common denominator of 2 given integers
     * @param a first integer
     * @param b second integer
     * @return integer of the lowest common denominator of the given arguments
     */
    protected int LCD(int a, int b) { // set to protected to allow for testing, should be private otherwise
        return (a * b) / GCD(a, b);
    }

    /**
     * helper function to normalise a fraction
     * @param numerator numerator to normalise
     * @param denominator denominator to normalise
     */
    private void normalise(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be 0");
        }
        if (denominator < 0) {
            denominator *= -1;
            if (numerator > 0) {
                numerator *= -1;
            }
        }
        int gcd = GCD(Math.abs(numerator), denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }
}
