public final CharsetDecoder replaceWith(String newReplacement) {
        if (newReplacement == null)
            throw new IllegalArgumentException("Null replacement");
        int len = newReplacement.length();
        if (len == 0)
            throw new IllegalArgumentException("Empty replacement");
        if (len > maxCharsPerByte)
            throw new IllegalArgumentException("Replacement too long");

        this.replacement = newReplacement;






        implReplaceWith(this.replacement);
        return this;
    }