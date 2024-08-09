@Override
    public DistributionStatisticConfig merge(DistributionStatisticConfig parent) {
        return DistributionStatisticConfig.builder()
                .percentilesHistogram(this.percentileHistogram == null ? parent.percentileHistogram : this.percentileHistogram)
                .percentiles(this.percentiles == null ? parent.percentiles : this.percentiles)
                .sla(this.sla == null ? parent.sla : this.sla)
                .percentilePrecision(this.percentilePrecision == null ? parent.percentilePrecision : this.percentilePrecision)
                .minimumExpectedValue(this.minimumExpectedValue == null ? parent.minimumExpectedValue : this.minimumExpectedValue)
                .maximumExpectedValue(this.maximumExpectedValue == null ? parent.maximumExpectedValue : this.maximumExpectedValue)
                .expiry(this.expiry == null ? parent.expiry : this.expiry)
                .bufferLength(this.bufferLength == null ? parent.bufferLength : this.bufferLength)
                .build();
    }