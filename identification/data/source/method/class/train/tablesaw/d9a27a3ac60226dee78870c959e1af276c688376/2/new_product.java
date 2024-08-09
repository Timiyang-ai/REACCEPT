@Override
    public int countMissing() {
        int count = 0;
        for (int i = 0; i < size(); i++) {
            if (getIntInternal(i) == TimeColumnType.missingValueIndicator()) {
                count++;
            }
        }
        return count;
    }