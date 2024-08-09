@Override
    public int countMissing() {
        int count = 0;
        for (int i = 0; i < size(); i++) {
            if (getInt(i) == MISSING_VALUE) {
                count++;
            }
        }
        return count;
    }