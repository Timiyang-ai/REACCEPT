public WordSequence getOldest() {
        WordSequence next = EMPTY;

        if (size() >= 1) {
            next = new WordSequence(wordIDs.length -1);
            for (int i = 0; i < next.wordIDs.length; i++) {
                next.wordIDs[i] = this.wordIDs[i];
            }
        }
	return next;
    }