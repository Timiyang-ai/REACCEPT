public static double smog_index(String strText) {
        strText = clean_text(strText);
        return PHPfunctions.round(1.043 * Math.sqrt((words_with_three_syllables(strText) * (30.0 / sentence_count(strText))) + 3.1291), 1);
    }