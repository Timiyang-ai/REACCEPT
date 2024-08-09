    private String format(Coin coin, int shift, int minDecimals, int... decimalGroups) {
        return NO_CODE.shift(shift).minDecimals(minDecimals).optionalDecimals(decimalGroups).format(coin).toString();
    }