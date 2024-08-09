private Stream<String> writeSummary(DistributionSummary summary, long wallTime) {
        summary.takeSnapshot();
        Stream.Builder<String> stream = Stream.builder();
        stream.add(index(summary, wallTime)
                .field("count", summary.count())
                .field("sum", summary.totalAmount())
                .field("mean", summary.mean())
                .field("max", summary.max())
                .build());

        return stream.build();
    }