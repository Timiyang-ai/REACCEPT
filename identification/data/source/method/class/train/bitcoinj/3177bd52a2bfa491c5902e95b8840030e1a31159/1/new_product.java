public MonetaryFormat repeatOptionalDecimals(int decimals, int repetitions) {
        checkArgument(repetitions >= 0);
        List<Integer> decimalGroups = new ArrayList<>(repetitions);
        for (int i = 0; i < repetitions; i++)
            decimalGroups.add(decimals);
        return new MonetaryFormat(negativeSign, positiveSign, zeroDigit, decimalMark, minDecimals, decimalGroups,
                shift, roundingMode, codes, codeSeparator, codePrefixed);
    }