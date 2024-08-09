@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof WordSequence) {
            WordSequence other = (WordSequence) o;
            if (words.length == other.words.length) {
                for (int i = 0; i < words.length; i++) {
                    if (!words[i].equals(other.words[i])) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }