public String[] toWords(int... indexes) {
        String[] words = new String[indexes.length];
        int k = 0;
        for (int index : indexes) {
            if (contains(index))
                words[k++] = vocabulary.get(index);
            else {
                Log.warn("Out of bounds word index is used:" + index);
                words[k++] = unknownWord;
            }
        }
        return words;
    }