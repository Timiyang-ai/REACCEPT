public float getPercentageResult() {
        int total = 0;
        for (Status status : Status.values()) {
            total += this.statusCounter.getValueFor(status);
        }
        return 100 * this.statusCounter.getValueFor(Status.PASSED) / total;
    }