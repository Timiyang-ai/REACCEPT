public void print(Calendrical calendrical, Appendable appendable, DateTimeFormatSymbols symbols) throws IOException {
        int value = calendrical.getValue(fieldRule, false);
        String str = (value == Integer.MIN_VALUE ? "2147483648" : Integer.toString(Math.abs(value)));
        if (str.length() > maxWidth) {
            throw new CalendricalFormatFieldException(fieldRule, value, maxWidth);
        }
        str = FormatUtil.convertToI18N(str, symbols);
        
        if (value >= 0) {
            switch (signStyle) {
                case EXCEEDS_PAD:
                    if (minWidth < 10 && value >= EXCEED_POINTS[minWidth]) {
                        appendable.append(symbols.getPositiveSignChar());
                    }
                    break;
                case ALWAYS:
                    appendable.append(symbols.getPositiveSignChar());
                    break;
            }
        } else {
            switch (signStyle) {
                case NORMAL:
                case EXCEEDS_PAD:
                case ALWAYS:
                    appendable.append(symbols.getNegativeSignChar());
                    break;
                case NEGATIVE_ERROR:
                    throw new CalendricalFormatFieldException(fieldRule, value);
            }
        }
        for (int i = 0; i < minWidth - str.length(); i++) {
            appendable.append(symbols.getZeroChar());
        }
        appendable.append(str);
    }