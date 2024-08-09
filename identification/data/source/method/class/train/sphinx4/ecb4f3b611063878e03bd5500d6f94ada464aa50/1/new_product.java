public WordSequence getOldest() {
        WordSequence next = EMPTY;

        if (size() >= 1) {
            next = new WordSequence(words.length -1);
            for (int i = 0; i < next.words.length; i++) {
                next.words[i] = this.words[i];
            }
        }
	return next;
    }