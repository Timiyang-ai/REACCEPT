public static double colemanLiauIndex(String strText) {
        strText = cleanText(strText);
        int intWordCount = wordCount(strText);
        return PHPfunctions.round( ( (5.89 * (letterCount(strText) / (double)intWordCount)) - (0.3 * (sentenceCount(strText) / (double)intWordCount)) - 15.8 ), 1);
    }