public WordSequence getNewest() {
        WordSequence next = EMPTY;

        if (size() >= 1) {
            next = new WordSequence(words.length -1);
            System.arraycopy(this.words, 1, next.words, 0, next.words.length);
        }
	return next;
    }