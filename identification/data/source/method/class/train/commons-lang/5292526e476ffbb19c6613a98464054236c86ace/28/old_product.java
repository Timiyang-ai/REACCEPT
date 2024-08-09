public StrBuilder replaceAll(final String searchStr, final String replaceStr) {
        int searchLen = (searchStr == null ? 0 : searchStr.length());
        if (searchLen > 0) {
            int replaceLen = (replaceStr == null ? 0 : replaceStr.length());
            int index = indexOf(searchStr, 0);
            while (index >= 0) {
                replaceImpl(index, index + searchLen, searchLen, replaceStr, replaceLen);
                index = indexOf(searchStr, index + replaceLen);
            }
        }
        return this;
    }