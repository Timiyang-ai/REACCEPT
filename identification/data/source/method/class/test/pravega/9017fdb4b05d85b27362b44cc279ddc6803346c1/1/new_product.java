public CompletableFuture<ScaleResponse> manualScale(String scope, String stream, List<Long> segmentsToSeal,
                                                        List<AbstractMap.SimpleEntry<Double, Double>> newRanges, long scaleTimestamp,
                                                        OperationContext context) {
        final long requestId = requestTracker.getRequestIdFor("scaleStream", scope, stream, String.valueOf(scaleTimestamp));
        ScaleOpEvent event = new ScaleOpEvent(scope, stream, segmentsToSeal, newRanges, true, scaleTimestamp, requestId);

        return writeEvent(event)
                .thenCompose(x -> streamMetadataStore.submitScale(scope, stream, segmentsToSeal, newRanges,
                        scaleTimestamp, null, context, executor)
                        .handle((startScaleResponse, e) -> {
                            ScaleResponse.Builder response = ScaleResponse.newBuilder();

                            if (e != null) {
                                Throwable cause = Exceptions.unwrap(e);
                                if (cause instanceof EpochTransitionOperationExceptions.PreConditionFailureException) {
                                    response.setStatus(ScaleResponse.ScaleStreamStatus.PRECONDITION_FAILED);
                                } else {
                                    log.warn(requestId, "Scale for stream {}/{} failed with exception {}", scope, stream, cause);
                                    response.setStatus(ScaleResponse.ScaleStreamStatus.FAILURE);
                                }
                            } else {
                                log.info(requestId, "scale for stream {}/{} started successfully", scope, stream);
                                response.setStatus(ScaleResponse.ScaleStreamStatus.STARTED);
                                response.addAllSegments(
                                        startScaleResponse.getObject().getNewSegmentsWithRange().entrySet()
                                                .stream()
                                                .map(segment -> convert(scope, stream, segment))
                                                .collect(Collectors.toList()));
                                response.setEpoch(startScaleResponse.getObject().getActiveEpoch());
                            }
                            return response.build();
                        }));
    }