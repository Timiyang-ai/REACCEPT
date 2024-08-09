@VisibleForTesting
  static List<Sample> getSamples(
      final String name,
      final List<String> labelNames,
      List</*@Nullable*/ TagValue> tagValues,
      AggregationData aggregationData,
      final Aggregation aggregation) {
    Preconditions.checkArgument(
        labelNames.size() == tagValues.size(), "Label names and tag values have different sizes.");
    final List<Sample> samples = Lists.newArrayList();
    final List<String> labelValues = new ArrayList<String>(tagValues.size());
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
            // For histogram buckets, manually add the bucket boundaries as "le" labels. See
            // https://github.com/prometheus/client_java/commit/ed184d8e50c82e98bb2706723fff764424840c3a#diff-c505abbde72dd6bf36e89917b3469404R241
            @SuppressWarnings("unchecked")
            Distribution distribution = (Distribution) aggregation;
            List<Double> boundaries = distribution.getBucketBoundaries().getBoundaries();
            List<String> labelNamesWithLe = new ArrayList<String>(labelNames);
            labelNamesWithLe.add(LABEL_NAME_BUCKET_BOUND);
            long cumulativeCount = 0;
            for (int i = 0; i < arg.getBucketCounts().size(); i++) {
              List<String> labelValuesWithLe = new ArrayList<String>(labelValues);
              // The label value of "le" is the upper inclusive bound.
              // For the last bucket, it should be "+Inf".
              String bucketBoundary =
                  doubleToGoString(
                      i < boundaries.size() ? boundaries.get(i) : Double.POSITIVE_INFINITY);
              labelValuesWithLe.add(bucketBoundary);
              cumulativeCount += arg.getBucketCounts().get(i);
              samples.add(
                  new MetricFamilySamples.Sample(
                      name + SAMPLE_SUFFIX_BUCKET,
                      labelNamesWithLe,
                      labelValuesWithLe,
                      cumulativeCount));
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