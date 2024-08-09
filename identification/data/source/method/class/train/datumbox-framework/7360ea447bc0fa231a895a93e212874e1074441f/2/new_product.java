public static double gunningFogScore(String strText) {
        strText = cleanText(strText);
        return PHPfunctions.round(((averageWordsPerSentence(strText) + percentageWordsWithThreeSyllables(strText)) * 0.4), 1);
    }