public static double smogIndex(String strText) {
        strText = cleanText(strText);
        return PHPfunctions.round(1.043 * Math.sqrt((wordsWithThreeSyllables(strText) * (30.0 / sentenceCount(strText))) + 3.1291), 1);
    }