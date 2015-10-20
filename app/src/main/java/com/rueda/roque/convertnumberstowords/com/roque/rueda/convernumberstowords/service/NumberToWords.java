package com.rueda.roque.convertnumberstowords.com.roque.rueda.convernumberstowords.service;

/**
 * Class that contains an algorithm used to convert numbers to words.
 *
 * @author roquerueda
 * @version 1.0
 * @since 17/10/15
 */
public class NumberToWords {

    /**
     * Name of the numbers that are smaller than 20.
     */
    private String[] mNumberNames = {
        "", "one", "two", "three", "four", "five",
        "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "fifteen",
        "sixteen", "seventeen", "eighteen", "nineteen"
    };

    /**
     * Teens are the names of the numbers in teens.
     */
    private String[] mTensName = {
        "", "ten", "twenty", "thirty", "forty", "fifty",
        "sixty", "seventy", "eighty", "ninety"
    };


    /**
     * Return a string representation of the number.
     * @param number Number value that will be converted to a
     * @return String representation of the number.
     */
    public String transformToEnglish(int number) {
        StringBuilder result = new StringBuilder();
        // Format should be XXX XXX XXX XXX

        // Validation of the zero value.
        if (number == 0) {
            return "zero";
        }

        // Get the numbers in sets of 3.
        // We can divide in sets of 1000.

        // (xxx) XXX XXX XXX
        // Zeros numbers that you want to skip.
        int billions = number / 1000000000;

        // XXX (xxx) XXX XXX
        int millions = (number - billions * 1000000000) / 1000000;

        // XXX XXX (xxx) XXX
        int thousands = ((number - millions * 1000000) - billions * 1000000000) / 1000;

        // XXX XXX XXX (xxx)
        int hundred = (((number - thousands * 1000) - millions * 1000000) - billions * 1000000000);

        // Billions.
        String currentNumber;
        switch (billions) {
            case 0: {
                currentNumber = "";
            } break;
            default: {
                currentNumber = convertSmallNumber(billions) + " billion ";
            } break;
        }
        result.append(currentNumber);

        // Millions.
        switch (millions) {
            case 0:{
                currentNumber = "";
            }break;
            case 1: {
                // Special case.
                currentNumber = convertSmallNumber(millions) + " million ";
            }break;
            default: {
                currentNumber = convertSmallNumber(millions) + " millions ";
            }break;
        }
        result.append(currentNumber);

        // thousands.
        switch (thousands) {
            case 0: {
                currentNumber = "";
            } break;
            case 1: {
                currentNumber = " one thousand ";
            }break;
            default: {
                currentNumber = convertSmallNumber(thousands) + " thousand ";
            }break;
        }
        result.append(currentNumber);

        currentNumber = convertSmallNumber(hundred);
        result.append(currentNumber);

        return result.toString();
    }

    /**
     * Convert a small number less than one thousand to a string
     * representation.
     */
    private String convertSmallNumber(int lessThanOneThousand) {
        // The number that we get here will be in the format
        // XXX like 999.

        // Keep what we have so far.
        String sf;

        // We need to check if the number is less than 20.
        // We can divide the number to determine if its less
        // than 20.
        if (lessThanOneThousand % 100 < 20) {
            // Add the number names
            sf = mNumberNames[lessThanOneThousand%100];
            lessThanOneThousand /= 100;

        } else {
            // A bigger number.
            // Example 999.

            // We get number 9
            sf = mNumberNames[lessThanOneThousand%10];
            lessThanOneThousand /= 10; // Remove the last 9 (99->9)

            // We get the 9 that is for the tens.
            sf = mTensName[lessThanOneThousand % 10] + sf; // Get ninety.
            // ninety + nine.
            lessThanOneThousand /= 10; // remove the second 9.
        }
        if (lessThanOneThousand == 0) {
            return sf;
        }

        return mNumberNames[lessThanOneThousand] + " hundred " + sf;
    }

}
