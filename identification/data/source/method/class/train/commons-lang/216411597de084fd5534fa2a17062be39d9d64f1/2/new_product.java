public static int hexDigitToInt(char hexDigit) {
        final int digit = Character.digit(hexDigit, 16);
        if (digit < 0) { 
            throw new IllegalArgumentException("Cannot interpret '"
                + hexDigit
                + "' as a hexadecimal digit");
        }
        return digit;
    }