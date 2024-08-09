public static int getRapidDefaultReadNameRegexSplit(final String readName, final char delim, final int[] tokens) {
        int tokensIdx = 0;
        int prevIdx = 0;
        for (int i = 0; i < readName.length(); i++) {
            if (readName.charAt(i) == delim) {
                if (1 < tokensIdx && tokensIdx < 5)
                    tokens[tokensIdx] = rapidParseInt(readName.substring(prevIdx, i)); // only fill in 2-4 inclusive
                tokensIdx++;
                if (4 < tokensIdx) return tokensIdx; // early return, only consider the first five tokens
                prevIdx = i + 1;
            }
        }
        if (prevIdx < readName.length()) {
            if (1 < tokensIdx && tokensIdx < 5)
                tokens[tokensIdx] = rapidParseInt(readName.substring(prevIdx, readName.length())); // only fill in 2-4 inclusive
            tokensIdx++;
        }
        return tokensIdx;
    }