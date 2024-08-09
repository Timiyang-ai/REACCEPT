public static String bitcoinValueToPlainString(Coin value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
                
        BigDecimal valueInBTC = new BigDecimal(value.toBigInteger()).divide(new BigDecimal(Utils.COIN.toBigInteger()));
        return valueInBTC.toPlainString();
    }