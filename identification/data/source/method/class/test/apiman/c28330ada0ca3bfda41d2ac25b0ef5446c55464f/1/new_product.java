public static final String idFromName(String name) {
        Transliterator tr = Transliterator.getInstance("Any-Latin; Latin-ASCII");
        return removeNonWord(tr.transliterate(name));
    }