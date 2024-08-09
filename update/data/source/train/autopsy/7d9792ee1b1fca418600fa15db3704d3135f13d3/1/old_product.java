public boolean isValidCCN(String rawCCN) {
        //check for a valid separator
        boolean hasSpace = StringUtils.contains(rawCCN, ' ');
        boolean hasDash = StringUtils.contains(rawCCN, '-');
        if (hasSpace && hasDash) {
            return false;    //can only have dashes or spaces, not both.
        }

        Character separator = null;
        if (hasSpace) {
            separator = ' ';
        } else if (hasDash) {
            separator = '-';
        }

        final String cannonicalCCN;
        if (separator == null) {
            cannonicalCCN = rawCCN;
        } else {
            //if there is a seperator, strip if for canoncial form of CCN
            cannonicalCCN = CharMatcher.anyOf(" -").removeFrom(rawCCN);

            //and validate digit grouping
            if (cannonicalCCN.length() == 16) {
                String[] splitCCN = rawCCN.split(separator.toString());
                if (Arrays.stream(splitCCN).anyMatch(s -> s.length() != 4)
                        || splitCCN.length != 4) {
                    return false;
                }
            }
            if (cannonicalCCN.length() == 15
                    && (cannonicalCCN.startsWith("34") || cannonicalCCN.startsWith("37"))) {
                String[] splitCCN = rawCCN.split(separator.toString());

                if (splitCCN.length != 3) {
                    return false;
                } else if (false == (splitCCN[0].length() == 4 && splitCCN[1].length() == 6 && splitCCN[2].length() == 5)) {
                    return false;
                }
            }
        }
        return CREDIT_CARD_NUM_LUHN_CHECK.isValid(cannonicalCCN);
    }