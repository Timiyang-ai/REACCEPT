public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof WordSequence) {
	    WordSequence other = (WordSequence) o;
            if (this.words.length == other.words.length) {
                for (int i = 0; i < this.words.length; i++) {
                    if (this.words[i] ==  other.words[i]) {
                        continue;
                    }
                    if (!this.words[i].equals(other.words[i])) {
                        return false;
                    }
                }
                return true;
            } 
	} 
        return false;
    }