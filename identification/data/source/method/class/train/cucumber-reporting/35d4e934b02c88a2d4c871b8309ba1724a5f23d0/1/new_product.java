public String getPercentageResult() {
        int total = 0;
        for (Status status : Status.values()) {
            total += this.statusCounter.getValueFor(status);
        }

        // value '1F' is to force floating conversion instead of loosing decimal part
        return Util.PERCENT_FORMATTER.format(1F * this.statusCounter.getValueFor(Status.PASSED) / total);
    }