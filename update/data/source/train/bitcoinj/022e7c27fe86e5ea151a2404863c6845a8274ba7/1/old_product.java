public static String bitcoinValueToPlainString(BigInteger value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
                
        BigDecimal valueInBTC = new BigDecimal(value).divide(new BigDecimal(Utils.COIN));
        return valueInBTC.toPlainString();
    }