@Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof WordSequence))
            return false;

        return Arrays.equals(words, ((WordSequence) object).words);
    }