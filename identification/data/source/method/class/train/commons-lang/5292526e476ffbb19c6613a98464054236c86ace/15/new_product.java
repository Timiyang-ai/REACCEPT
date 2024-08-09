public StrBuilder replaceFirst(final String searchStr, final String replaceStr) {
        final int searchLen = (searchStr == null ? 0 : searchStr.length());
        if (searchLen > 0) {
            final int index = indexOf(searchStr, 0);
            if (index >= 0) {
                final int replaceLen = (replaceStr == null ? 0 : replaceStr.length());
                replaceImpl(index, index + searchLen, searchLen, replaceStr, replaceLen);
            }
        }
        return this;
    }