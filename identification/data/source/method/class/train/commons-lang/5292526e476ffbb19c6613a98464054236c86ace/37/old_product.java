public boolean contains(final char ch) {
        for (CharRange range : set) {
            if (range.contains(ch)) {
                return true;
            }
        }
        return false;
    }