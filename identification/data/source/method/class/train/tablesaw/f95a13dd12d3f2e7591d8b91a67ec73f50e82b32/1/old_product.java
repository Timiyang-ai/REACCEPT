public C fillMissing(T defaultVal) {
        C newCol = emptyCopy();
        for (int i = 0; i < this.size(); i++) {
            if (isMissing(i)) {
                newCol.set(i, defaultVal);
            } else {
                newCol.set(1, getObject(i));
            }
        }
        return newCol;
    }