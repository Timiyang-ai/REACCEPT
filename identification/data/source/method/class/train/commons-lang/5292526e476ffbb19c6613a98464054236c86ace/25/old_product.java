public StrBuilder appendFixedWidthPadRight(final Object obj, final int width, final char padChar) {
        if (width > 0) {
            ensureCapacity(size + width);
            String str = (obj == null ? getNullText() : obj.toString());
            if (str == null) {
                str = "";
            }
            int strLen = str.length();
            if (strLen >= width) {
                str.getChars(0, width, buffer, size);
            } else {
                int padLen = width - strLen;
                str.getChars(0, strLen, buffer, size);
                for (int i = 0; i < padLen; i++) {
                    buffer[size + strLen + i] = padChar;
                }
            }
            size += width;
        }
        return this;
    }