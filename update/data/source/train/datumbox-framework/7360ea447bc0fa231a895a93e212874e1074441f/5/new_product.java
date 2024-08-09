public static double fleschKincaidReadingEase(String strText) {
        strText = cleanText(strText);
        return PHPfunctions.round((206.835 - (1.015 * averageWordsPerSentence(strText)) - (84.6 * averageSyllablesPerWord(strText))), 1);
    }