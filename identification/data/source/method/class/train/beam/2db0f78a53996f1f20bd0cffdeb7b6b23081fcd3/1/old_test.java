  static SourceSplitResponse performSplit(
      com.google.api.services.dataflow.model.Source source,
      PipelineOptions options,
      @Nullable Long desiredBundleSizeBytes,
      @Nullable Integer numBundlesLimitForTest,
      @Nullable Long apiByteLimitForTest)
      throws Exception {
    SourceSplitRequest splitRequest = new SourceSplitRequest();
    splitRequest.setSource(source);
    if (desiredBundleSizeBytes != null) {
      splitRequest.setOptions(
          new SourceSplitOptions().setDesiredBundleSizeBytes(desiredBundleSizeBytes));
    }
    SourceOperationResponse response =
        WorkerCustomSources.performSplitWithApiLimit(
            splitRequest,
            options,
            MoreObjects.firstNonNull(
                numBundlesLimitForTest, WorkerCustomSources.DEFAULT_NUM_BUNDLES_LIMIT),
            MoreObjects.firstNonNull(
                apiByteLimitForTest, WorkerCustomSources.DATAFLOW_SPLIT_RESPONSE_API_SIZE_LIMIT));
    return response.getSplit();
  }