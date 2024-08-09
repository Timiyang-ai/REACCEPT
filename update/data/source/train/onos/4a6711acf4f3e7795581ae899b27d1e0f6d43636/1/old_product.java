public static String getCamelCase(String yangIdentifier, YangToJavaNamingConflictUtil conflictResolver) {

        if (conflictResolver != null) {
            String replacementForHyphen = conflictResolver.getReplacementForHyphen();
            String replacementForPeriod = conflictResolver.getReplacementForPeriod();
            String replacementForUnderscore = conflictResolver.getReplacementForUnderscore();
            if (replacementForPeriod != null) {
                yangIdentifier = yangIdentifier.replaceAll(REGEX_FOR_PERIOD,
                        PERIOD + replacementForPeriod.toLowerCase() + PERIOD);
            }
            if (replacementForUnderscore != null) {
                yangIdentifier = yangIdentifier.replaceAll(REGEX_FOR_UNDERSCORE,
                        UNDER_SCORE + replacementForUnderscore.toLowerCase() + UNDER_SCORE);
            }
            if (replacementForHyphen != null) {
                yangIdentifier = yangIdentifier.replaceAll(REGEX_FOR_HYPHEN,
                        HYPHEN + replacementForHyphen.toLowerCase() + HYPHEN);
            }
        }
        yangIdentifier = yangIdentifier.replaceAll(REGEX_FOR_IDENTIFIER_SPECIAL_CHAR, COLAN);
        String[] strArray = yangIdentifier.split(COLAN);
        if (strArray[0].isEmpty()) {
            List<String> stringArrangement = new ArrayList<String>();
            for (int i = 1; i < strArray.length; i++) {
                stringArrangement.add(strArray[i]);
            }
            strArray = stringArrangement.toArray(new String[stringArrangement.size()]);
        }
        return applyCamelCaseRule(strArray);
    }