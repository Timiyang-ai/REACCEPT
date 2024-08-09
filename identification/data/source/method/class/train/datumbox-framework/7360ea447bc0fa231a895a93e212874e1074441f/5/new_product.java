public static double automatedReadabilityIndex(String strText) {
        strText = cleanText(strText);
        int intWordCount = wordCount(strText);
        return PHPfunctions.round(((4.71 * (letterCount(strText) / (double)intWordCount)) + (0.5 * (intWordCount / (double)sentenceCount(strText))) - 21.43), 1);
    }