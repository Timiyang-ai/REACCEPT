public static double coleman_liau_index(String strText) {
        strText = clean_text(strText);
        int intWordCount = word_count(strText);
        return PHPfunctions.round( ( (5.89 * (letter_count(strText) / (double)intWordCount)) - (0.3 * (sentence_count(strText) / (double)intWordCount)) - 15.8 ), 1);
    }