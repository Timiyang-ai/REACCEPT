public String getPercentageResult() {
        int total = 0;
        for (Status status : Status.values()) {
            total += this.statusCounter.getValueFor(status);
        }

        return Util.formatAsPercentage(this.statusCounter.getValueFor(Status.PASSED), total);
    }