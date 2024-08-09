public static Float valueOf(String s) throws NumberFormatException {
        return new Float(FloatingDecimal.getThreadLocalInstance().readJavaFormatString(s).floatValue());
    }