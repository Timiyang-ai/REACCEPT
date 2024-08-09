public static double flesch_kincaid_reading_ease(String strText) {
        strText = clean_text(strText);
        return PHPfunctions.round((206.835 - (1.015 * average_words_per_sentence(strText)) - (84.6 * average_syllables_per_word(strText))), 1);
    }