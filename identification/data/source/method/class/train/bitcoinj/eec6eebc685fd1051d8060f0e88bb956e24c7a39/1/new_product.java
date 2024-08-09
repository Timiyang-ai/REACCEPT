public CharSequence format(Monetary monetary) {
        // preparation
        int maxDecimals = minDecimals;
        if (decimalGroups != null)
            for (int group : decimalGroups)
                maxDecimals += group;
        int smallestUnitExponent = monetary.smallestUnitExponent();
        checkState(maxDecimals <= smallestUnitExponent,
                "The maximum possible number of decimals (%s) cannot exceed %s.", maxDecimals, smallestUnitExponent);

        // rounding
        long satoshis = Math.abs(monetary.getValue());
        long precisionDivisor = checkedPow(10, smallestUnitExponent - shift - maxDecimals);
        satoshis = checkedMultiply(divide(satoshis, precisionDivisor, roundingMode), precisionDivisor);

        // shifting
        long shiftDivisor = checkedPow(10, smallestUnitExponent - shift);
        long numbers = satoshis / shiftDivisor;
        long decimals = satoshis % shiftDivisor;

        // formatting
        String decimalsStr = String.format(Locale.US, "%0" + (smallestUnitExponent - shift) + "d", decimals);
        StringBuilder str = new StringBuilder(decimalsStr);
        while (str.length() > minDecimals && str.charAt(str.length() - 1) == '0')
            str.setLength(str.length() - 1); // trim trailing zero
        int i = minDecimals;
        if (decimalGroups != null) {
            for (int group : decimalGroups) {
                if (str.length() > i && str.length() < i + group) {
                    while (str.length() < i + group)
                        str.append('0');
                    break;
                }
                i += group;
            }
        }
        if (str.length() > 0)
            str.insert(0, decimalMark);
        str.insert(0, numbers);
        if (monetary.getValue() < 0)
            str.insert(0, negativeSign);
        else if (positiveSign != 0)
            str.insert(0, positiveSign);
        if (codes != null) {
            if (codePrefixed) {
                str.insert(0, codeSeparator);
                str.insert(0, code());
            } else {
                str.append(codeSeparator);
                str.append(code());
            }
        }

        // Convert to non-arabic digits.
        if (zeroDigit != '0') {
            int offset = zeroDigit - '0';
            for (int d = 0; d < str.length(); d++) {
                char c = str.charAt(d);
                if (Character.isDigit(c))
                    str.setCharAt(d, (char) (c + offset));
            }
        }
        return str;
    }