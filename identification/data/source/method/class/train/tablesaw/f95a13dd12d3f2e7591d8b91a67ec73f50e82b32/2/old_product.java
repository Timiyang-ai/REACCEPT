public C fillMissing(C other) {
        C newCol = emptyCopy();
        for (int i = 0; i < this.size(); i++) {
            if (isMissing(i)) {
                newCol.set(i, other.getObject(i));
            } else {
                newCol.set(1, getObject(i));
            }
        }
        return newCol;
    }