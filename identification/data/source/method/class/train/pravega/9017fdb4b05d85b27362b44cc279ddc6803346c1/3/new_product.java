public CompletableFuture<ScaleResponse> manualScale(String scope, String stream, List<Integer> segmentsToSeal,
                                                        List<AbstractMap.SimpleEntry<Double, Double>> newRanges, long scaleTimestamp,
                                                        OperationContext context) {
        ScaleOpEvent event = new ScaleOpEvent(scope, stream, segmentsToSeal, newRanges, true, scaleTimestamp);
        return writeEvent(event).thenCompose(x ->
                streamMetadataStore.startScale(scope, stream, segmentsToSeal, newRanges, scaleTimestamp, false,
                        context, executor)
                        .handle((startScaleResponse, e) -> {
                            ScaleResponse.Builder response = ScaleResponse.newBuilder();

                            if (e != null) {
                                Throwable cause = ExceptionHelpers.getRealException(e);
                                if (cause instanceof ScaleOperationExceptions.ScalePreConditionFailureException) {
                                    response.setStatus(ScaleResponse.ScaleStreamStatus.PRECONDITION_FAILED);
                                } else {
                                    log.debug("Scale for stream {}/{} failed with exception {}", scope, stream, cause);
                                    response.setStatus(ScaleResponse.ScaleStreamStatus.FAILURE);
                                }
                            } else {
                                log.info("scale for stream {}/{} started successfully", scope, stream);
                                response.setStatus(ScaleResponse.ScaleStreamStatus.STARTED);
                                response.addAllSegments(
                                        startScaleResponse.getSegmentsCreated()
                                                .stream()
                                                .map(segment -> convert(scope, stream, segment))
                                                .collect(Collectors.toList()));
                                response.setEpoch(startScaleResponse.getActiveEpoch());
                            }
                            return response.build();
                        }));
    }