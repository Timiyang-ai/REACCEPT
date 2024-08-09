public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof WordSequence) {
	    WordSequence other = (WordSequence) o;
            if (this.wordIDs.length == other.wordIDs.length) {
                for (int i = 0; i < this.wordIDs.length; i++) {
                    if (this.wordIDs[i] != other.wordIDs[i]) {
                        return false;
                    }
                }
                return true;
            } 
	} 
        return false;
    }