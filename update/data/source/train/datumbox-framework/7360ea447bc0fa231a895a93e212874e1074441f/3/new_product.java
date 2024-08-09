public static double fleschKincaidGradeLevel(String strText) {
        strText = cleanText(strText);
        return PHPfunctions.round(((0.39 * averageWordsPerSentence(strText)) + (11.8 * averageSyllablesPerWord(strText)) - 15.59), 1);
    }