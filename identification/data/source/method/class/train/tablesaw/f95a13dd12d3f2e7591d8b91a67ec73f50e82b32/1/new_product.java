public C fillMissing(T defaultVal) {
        C newCol = emptyCopy();
        for (int i = 0; i < this.size(); i++) {
            if (isMissing(i)) {
                newCol.append(defaultVal);
            } else {
                newCol.appendCell(getUnformattedString(i));
            }
        }
        return newCol;
    }