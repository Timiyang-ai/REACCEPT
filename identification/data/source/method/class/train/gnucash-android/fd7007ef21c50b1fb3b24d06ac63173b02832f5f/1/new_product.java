@Override
    public String toString() {
        BigDecimal numerator = new BigDecimal(mValueNum);
        BigDecimal denominator = new BigDecimal(mValueDenom);
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(6);
        return formatter.format(numerator.divide(denominator, MathContext.DECIMAL32));
    }