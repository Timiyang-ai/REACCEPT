@SuppressWarnings("null") // str cannot be null
    public StrBuilder insert(final int index, String str) {
        validateIndex(index);
        if (str == null) {
            str = nullText;
        }
        int strLen = (str == null ? 0 : str.length());
        if (strLen > 0) {
            int newSize = size + strLen;
            ensureCapacity(newSize);
            System.arraycopy(buffer, index, buffer, index + strLen, size - index);
            size = newSize;
            str.getChars(0, strLen, buffer, index); // str cannot be null here
        }
        return this;
    }