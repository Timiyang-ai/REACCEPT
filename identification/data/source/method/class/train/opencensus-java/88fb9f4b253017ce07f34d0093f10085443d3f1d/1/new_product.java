@VisibleForTesting
  static List<Sample> getSamples(
      final String name,
      final List<String> labelNames,
      List<LabelValue> labelValuesList,
      Value value) {
    Preconditions.checkArgument(
        labelNames.size() == labelValuesList.size(), "Keys and Values don't have same size.");
    final List<Sample> samples = Lists.newArrayList();

    final List<String> labelValues = new ArrayList<String>(labelValuesList.size());
    for (LabelValue labelValue : labelValuesList) {
      String val = labelValue == null ? "" : labelValue.getValue();
      labelValues.add(val == null ? "" : val);
    }

    return value.match(
        new Function<Double, List<Sample>>() {
          @Override
          public List<Sample> apply(Double arg) {
            samples.add(new Sample(name, labelNames, labelValues, arg));
            return samples;
          }
        },
        new Function<Long, List<Sample>>() {
          @Override
          public List<Sample> apply(Long arg) {
            samples.add(new Sample(name, labelNames, labelValues, arg));
            return samples;
          }
        },
        new Function<Distribution, List<Sample>>() {
          @Override
          public List<Sample> apply(final Distribution arg) {
            BucketOptions bucketOptions = arg.getBucketOptions();
            List<Double> boundaries = new ArrayList<>();

            if (bucketOptions != null) {
              boundaries =
                  bucketOptions.match(
                      new Function<ExplicitOptions, List<Double>>() {
                        @Override
                        public List<Double> apply(ExplicitOptions arg) {
                          return arg.getBucketBoundaries();
                        }
                      },
                      Functions.<List<Double>>throwIllegalArgumentException());
            }

            List<String> labelNamesWithLe = new ArrayList<String>(labelNames);
            labelNamesWithLe.add(LABEL_NAME_BUCKET_BOUND);
            long cumulativeCount = 0;

            for (int i = 0; i < arg.getBuckets().size(); i++) {
              List<String> labelValuesWithLe = new ArrayList<String>(labelValues);
              // The label value of "le" is the upper inclusive bound.
              // For the last bucket, it should be "+Inf".
              String bucketBoundary =
                  doubleToGoString(
                      i < boundaries.size() ? boundaries.get(i) : Double.POSITIVE_INFINITY);
              labelValuesWithLe.add(bucketBoundary);
              cumulativeCount += arg.getBuckets().get(i).getCount();
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
                    name + SAMPLE_SUFFIX_SUM, labelNames, labelValues, arg.getSum()));
            return samples;
          }
        },
        new Function<Summary, List<Sample>>() {
          @Override
          public List<Sample> apply(Summary arg) {
            Long count = arg.getCount();
            if (count != null) {
              samples.add(
                  new MetricFamilySamples.Sample(
                      name + SAMPLE_SUFFIX_COUNT, labelNames, labelValues, count));
            }
            Double sum = arg.getSum();
            if (sum != null) {
              samples.add(
                  new MetricFamilySamples.Sample(
                      name + SAMPLE_SUFFIX_SUM, labelNames, labelValues, sum));
            }

            List<ValueAtPercentile> valueAtPercentiles = arg.getSnapshot().getValueAtPercentiles();
            List<String> labelNamesWithQuantile = new ArrayList<String>(labelNames);
            labelNamesWithQuantile.add(LABEL_NAME_QUANTILE);
            for (ValueAtPercentile valueAtPercentile : valueAtPercentiles) {
              List<String> labelValuesWithQuantile = new ArrayList<String>(labelValues);
              labelValuesWithQuantile.add(
                  doubleToGoString(valueAtPercentile.getPercentile() / 100));
              samples.add(
                  new MetricFamilySamples.Sample(
                      name,
                      labelNamesWithQuantile,
                      labelValuesWithQuantile,
                      valueAtPercentile.getValue()));
            }
            return samples;
          }
        },
        Functions.<List<Sample>>throwIllegalArgumentException());
  }