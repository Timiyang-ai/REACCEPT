public CompletableFuture<ScaleResponse> manualScale(String scope, String stream, List<Integer> segmentsToSeal,
                                                        List<AbstractMap.SimpleEntry<Double, Double>> newRanges, long scaleTimestamp,
                                                        OperationContext context) {
        ScaleOpEvent event = new ScaleOpEvent(scope, stream, segmentsToSeal, newRanges, true, scaleTimestamp);
        return postScale(event).thenCompose(x ->
                streamMetadataStore.startScale(scope, stream, segmentsToSeal, newRanges, scaleTimestamp, false,
                        context, executor)
                        .thenComposeAsync(startScaleResponse -> {
                            int activeEpoch = startScaleResponse.getActiveEpoch();
                            AtomicBoolean scaling = new AtomicBoolean(true);
                            return FutureHelpers.loop(scaling::get, () -> FutureHelpers.delayedFuture(() ->
                                            streamMetadataStore.getActiveEpoch(scope, stream, context, true, executor)
                                                    .thenAccept(state -> scaling.set(state.getKey() == activeEpoch)),
                                    1000, executor), executor)
                                    .thenApply(r -> startScaleResponse);
                        })
                        .handle((startScaleResponse, e) -> {
                            ScaleResponse.Builder response = ScaleResponse.newBuilder();

                            if (e != null) {
                                Throwable cause = ExceptionHelpers.getRealException(e);
                                if (cause instanceof ScaleOperationExceptions.ScalePreConditionFailureException) {
                                    response.setStatus(ScaleResponse.ScaleStreamStatus.PRECONDITION_FAILED);
                                } else {
                                    response.setStatus(ScaleResponse.ScaleStreamStatus.FAILURE);
                                }
                            } else {
                                response.setStatus(ScaleResponse.ScaleStreamStatus.SUCCESS);
                                response.addAllSegments(
                                        startScaleResponse.getSegmentsCreated()
                                                .stream()
                                                .map(segment -> convert(scope, stream, segment))
                                                .collect(Collectors.toList()));
                            }
                            return response.build();
                        }));
    }