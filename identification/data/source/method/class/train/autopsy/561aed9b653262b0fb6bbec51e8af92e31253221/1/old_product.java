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
        String[] splitCCN;
        if (separator != null) {
            //there is a seperator, strip if for canoncial form of CCN
            cannonicalCCN = CharMatcher.anyOf(separator.toString()).removeFrom(rawCCN);
            splitCCN = rawCCN.split(separator.toString());
        } else {
            //else use 'defualt'values
            cannonicalCCN = rawCCN;
            splitCCN = new String[]{cannonicalCCN};
        }

        // validate digit grouping for 15, 16, and 19 digit cards
        switch (cannonicalCCN.length()) {
            case 15:
                if (false == isValid15DigitGrouping(splitCCN)) {
                    return false;
                }
                break;
            case 16:
                if (false == isValid16DigitGrouping(splitCCN)) {
                    return false;
                }
                break;
            case 19:
                if (false == isValid19DigitGrouping(splitCCN)) {
                    return false;
                }
                break;
            default:
                if (false == isValidOtherDigitGrouping(splitCCN)) {
                    return false;
                }
        }

        return CREDIT_CARD_NUM_LUHN_CHECK.isValid(cannonicalCCN);
    }