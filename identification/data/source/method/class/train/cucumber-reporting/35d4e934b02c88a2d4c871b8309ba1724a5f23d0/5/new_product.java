public float getPercentageResult() {
        int total = 0;
        for (Status status : Status.values()) {
            total += this.statusCounter.getValueFor(status);
        }
        if (total == 0) {
            return 0;
        } else {
            return 100F * this.statusCounter.getValueFor(Status.PASSED) / total;
        }
    }