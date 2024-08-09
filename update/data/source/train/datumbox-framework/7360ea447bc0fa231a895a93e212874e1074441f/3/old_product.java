public static double flesch_kincaid_grade_level(String strText) {
        strText = clean_text(strText);
        return PHPfunctions.round(((0.39 * average_words_per_sentence(strText)) + (11.8 * average_syllables_per_word(strText)) - 15.59), 1);
    }