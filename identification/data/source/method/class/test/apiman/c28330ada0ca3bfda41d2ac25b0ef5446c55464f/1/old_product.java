public static final String idFromName(String name) {
        Transliterator tr = Transliterator.getInstance("Any-Latin");
        return removeNonWord(tr.transliterate(name));
    }