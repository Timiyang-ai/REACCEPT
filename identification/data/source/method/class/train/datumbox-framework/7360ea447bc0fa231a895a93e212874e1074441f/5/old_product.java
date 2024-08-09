public static double automated_readability_index(String strText) {
        strText = clean_text(strText);
        int intWordCount = word_count(strText);
        return PHPfunctions.round(((4.71 * (letter_count(strText) / (double)intWordCount)) + (0.5 * (intWordCount / (double)sentence_count(strText))) - 21.43), 1);
    }