public static int getRapidDefaultReadNameRegexSplit(final String readName, final char delim, final int[] tokens) {
        int tokensIdx = 0;
        int prevIdx = 0;
        int numFields = 1;
        for (int i = 0; i < readName.length(); i++) {
            if (readName.charAt(i) == delim) numFields++;
        }
        int startOffset = numFields - 2 - 1; // zero-based (ex. 7 -> 4, 5 -> 2)
        if (startOffset < 0) return -1;
        int endOffset = startOffset + 2; // zero-based
        for (int i = 0; i < readName.length(); i++) {
            if (readName.charAt(i) == delim) {
                if (startOffset <= tokensIdx && tokensIdx <= endOffset)
                    tokens[tokensIdx - startOffset] = rapidParseInt(readName.substring(prevIdx, i)); // only fill in 2-4 inclusive for 5 fields
                tokensIdx++;
                prevIdx = i + 1;
            }
        }
        if (prevIdx < readName.length()) {
            if (startOffset <= tokensIdx && tokensIdx <= endOffset)
                tokens[tokensIdx - startOffset] = rapidParseInt(readName.substring(prevIdx, readName.length())); // only fill in 2-4 inclusive
            tokensIdx++;
        }
        return tokensIdx;
    }