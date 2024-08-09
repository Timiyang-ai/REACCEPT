@VisibleForTesting
  static List<Sample> getSamples(
      final String name,
      List<TagKey> tagKeys,
      List</*@Nullable*/ TagValue> tagValues,
      AggregationData aggregationData) {
    Preconditions.checkArgument(
        tagKeys.size() == tagValues.size(), "Tag keys and tag values have different sizes.");
    final List<Sample> samples = Lists.newArrayList();
    final List<String> labelNames = new ArrayList<String>(tagKeys.size());
    final List<String> labelValues = new ArrayList<String>(tagValues.size());
    for (TagKey tagKey : tagKeys) {
      labelNames.add(Collector.sanitizeMetricName(tagKey.getName()));
    }
    for (TagValue tagValue : tagValues) {
      String labelValue = tagValue == null ? "" : tagValue.asString();
      labelValues.add(labelValue);
    }

    aggregationData.match(
        new Function<SumDataDouble, Void>() {
          @Override
          public Void apply(SumDataDouble arg) {
            samples.add(new Sample(name, labelNames, labelValues, arg.getSum()));
            return null;
          }
        },
        new Function<SumDataLong, Void>() {
          @Override
          public Void apply(SumDataLong arg) {
            samples.add(new Sample(name, labelNames, labelValues, arg.getSum()));
            return null;
          }
        },
        new Function<CountData, Void>() {
          @Override
          public Void apply(CountData arg) {
            samples.add(new Sample(name, labelNames, labelValues, arg.getCount()));
            return null;
          }
        },
        new Function<DistributionData, Void>() {
          @Override
          public Void apply(DistributionData arg) {
            for (long bucketCount : arg.getBucketCounts()) {
              samples.add(
                  new MetricFamilySamples.Sample(
                      name + SAMPLE_SUFFIX_BUCKET, labelNames, labelValues, bucketCount));
            }
            samples.add(
                new MetricFamilySamples.Sample(
                    name + SAMPLE_SUFFIX_COUNT, labelNames, labelValues, arg.getCount()));
            samples.add(
                new MetricFamilySamples.Sample(
                    name + SAMPLE_SUFFIX_SUM,
                    labelNames,
                    labelValues,
                    arg.getCount() * arg.getMean()));
            return null;
          }
        },
        new Function<LastValueDataDouble, Void>() {
          @Override
          public Void apply(LastValueDataDouble arg) {
            samples.add(new Sample(name, labelNames, labelValues, arg.getLastValue()));
            return null;
          }
        },
        new Function<LastValueDataLong, Void>() {
          @Override
          public Void apply(LastValueDataLong arg) {
            samples.add(new Sample(name, labelNames, labelValues, arg.getLastValue()));
            return null;
          }
        },
        new Function<AggregationData, Void>() {
          @Override
          public Void apply(AggregationData arg) {
            // TODO(songya): remove this once Mean aggregation is completely removed. Before that
            // we need to continue supporting Mean, since it could still be used by users and some
            // deprecated RPC views.
            if (arg instanceof AggregationData.MeanData) {
              AggregationData.MeanData meanData = (AggregationData.MeanData) arg;
              samples.add(
                  new MetricFamilySamples.Sample(
                      name + SAMPLE_SUFFIX_COUNT, labelNames, labelValues, meanData.getCount()));
              samples.add(
                  new MetricFamilySamples.Sample(
                      name + SAMPLE_SUFFIX_SUM,
                      labelNames,
                      labelValues,
                      meanData.getCount() * meanData.getMean()));
              return null;
            }
            throw new IllegalArgumentException("Unknown Aggregation.");
          }
        });

    return samples;
  }