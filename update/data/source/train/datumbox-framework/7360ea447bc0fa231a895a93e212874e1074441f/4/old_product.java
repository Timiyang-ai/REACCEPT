public static double gunning_fog_score(String strText) {
        strText = clean_text(strText);
        return PHPfunctions.round(((average_words_per_sentence(strText) + percentage_words_with_three_syllables(strText)) * 0.4), 1);
    }