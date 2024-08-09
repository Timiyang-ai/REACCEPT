public static boolean toBoolean(int value, int trueValue, int falseValue) {
        if (value == trueValue) {
            return true;
        } else if (value == falseValue) {
            return false;
        }
        // no match
        throw new IllegalArgumentException("The Integer did not match either specified value");
    }