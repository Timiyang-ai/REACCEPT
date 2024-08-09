public WordSequence getNewest() {
        assert size() > 1;
        WordSequence next = new WordSequence(words.length -1);
	for (int i = 0; i < next.words.length; i++) {
	    next.words[i] = this.words[i + 1];
	}
	return next;
    }