public void print(Calendrical calendrical, Appendable appendable, DateTimeFormatSymbols symbols) throws IOException {
        int value = rule.getInt(calendrical);
        BigDecimal fraction = rule.convertIntToFraction(value);
        if (fraction.scale() == 0) {  // scale is zero if value is zero
            if (minWidth > 0) {
                appendable.append(symbols.getDecimalPointChar());
                for (int i = 0; i < minWidth; i++) {
                    appendable.append(symbols.getZeroChar());
                }
            }
        } else {
            int outputScale = Math.min(Math.max(fraction.scale(), minWidth), maxWidth);
            fraction = fraction.setScale(outputScale, RoundingMode.FLOOR);
            String str = fraction.toPlainString().substring(2);
            str = symbols.convertNumberToI18N(str);
            appendable.append(symbols.getDecimalPointChar());
            appendable.append(str);
        }
    }