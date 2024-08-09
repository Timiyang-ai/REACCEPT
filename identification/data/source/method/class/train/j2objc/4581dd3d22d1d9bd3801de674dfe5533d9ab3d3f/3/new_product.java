public final CharsetEncoder replaceWith(byte[] newReplacement) {
        if (newReplacement == null)
            throw new IllegalArgumentException("Null replacement");
        int len = newReplacement.length;
        if (len == 0)
            throw new IllegalArgumentException("Empty replacement");
        if (len > maxBytesPerChar)
            throw new IllegalArgumentException("Replacement too long");




        if (!isLegalReplacement(newReplacement))
            throw new IllegalArgumentException("Illegal replacement");
        this.replacement = Arrays.copyOf(newReplacement, newReplacement.length);

        implReplaceWith(this.replacement);
        return this;
    }