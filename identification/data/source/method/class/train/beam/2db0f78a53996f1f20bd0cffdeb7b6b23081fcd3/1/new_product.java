public static SourceOperationResponse performSplit(
      SourceSplitRequest request, PipelineOptions options) throws Exception {
    return performSplitWithApiLimit(request, options, DATAFLOW_SPLIT_RESPONSE_API_SIZE_BYTES);
  }