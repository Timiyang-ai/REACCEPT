public String[] toWords(int... indexes) {
        String[] words = new String[indexes.length];
        int k = 0;
        for (int index : indexes) {
            if (contains(index))
                words[k++] = vocabulary[index];
            else
                words[k++] = OUT_OF_VOCABULARY;
        }
        return words;
    }