public C fillMissing(C other) {
        C newCol = emptyCopy();
        for (int i = 0; i < this.size(); i++) {
            if (isMissing(i)) {
                newCol.appendCell(other.getUnformattedString(i));
            } else {
                newCol.appendCell(getUnformattedString(i));
            }
        }
        return newCol;
    }