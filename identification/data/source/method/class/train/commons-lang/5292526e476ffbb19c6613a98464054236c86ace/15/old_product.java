public StrBuilder replaceFirst(final String searchStr, final String replaceStr) {
        int searchLen = (searchStr == null ? 0 : searchStr.length());
        if (searchLen > 0) {
            int index = indexOf(searchStr, 0);
            if (index >= 0) {
                int replaceLen = (replaceStr == null ? 0 : replaceStr.length());
                replaceImpl(index, index + searchLen, searchLen, replaceStr, replaceLen);
            }
        }
        return this;
    }