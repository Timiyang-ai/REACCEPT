public static SourceOperationResponse performSplit(
      SourceSplitRequest request, PipelineOptions options) throws Exception {
    Source<?> anySource = deserializeFromCloudSource(request.getSource().getSpec());
    checkArgument(
        anySource instanceof BoundedSource, "Cannot split a non-Bounded source: %s", anySource);
    BoundedSource<?> source = (BoundedSource<?>) anySource;

    // Compute the desired bundle size given by the service, or default if none was provided.
    long desiredBundleSizeBytes = DEFAULT_DESIRED_BUNDLE_SIZE_BYTES;
    SourceSplitOptions splitOptions = request.getOptions();
    if (splitOptions != null && splitOptions.getDesiredBundleSizeBytes() != null) {
      desiredBundleSizeBytes = splitOptions.getDesiredBundleSizeBytes();
    }

    // Try generating initial splits normally.
    SourceSplitResponse splits = performSplit(source, options, desiredBundleSizeBytes);
    long serializedSize = DataflowApiUtils.computeSerializedSizeBytes(splits);

    // If split response is too large, scale desired size for expected DATAFLOW_API_SIZE_BYTES/2.
    if (serializedSize > DATAFLOW_SPLIT_RESPONSE_API_SIZE_BYTES) {
      double expansion = 2 * (double) serializedSize / DATAFLOW_SPLIT_RESPONSE_API_SIZE_BYTES;
      long expandedBundleSizeBytes = (long) (desiredBundleSizeBytes * expansion);
      LOG.warn(
          "Splitting source {} into bundles of estimated size {} bytes produced {} bundles, which"
              + " have total serialized size {} bytes. As this is too large for the Google Cloud"
              + " Dataflow API, retrying splitting once with increased desiredBundleSizeBytes {}"
              + " to reduce the number of splits.",
          source,
          desiredBundleSizeBytes,
          splits.getBundles().size(),
          serializedSize,
          expandedBundleSizeBytes);
      splits = performSplit(source, options, expandedBundleSizeBytes);
    }

    return new SourceOperationResponse().setSplit(splits);
  }