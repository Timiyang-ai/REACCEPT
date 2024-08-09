public CompletableFuture<ScaleResponse> manualScale(String scope, String stream, List<Integer> segmentsToSeal,
                                                        List<AbstractMap.SimpleEntry<Double, Double>> newRanges, long scaleTimestamp,
                                                        OperationContext context) {
        ScaleOpEvent event = new ScaleOpEvent(scope, stream, segmentsToSeal, newRanges, true, scaleTimestamp);
        return postScale(event).thenCompose(x ->
                streamMetadataStore.startScale(scope, stream, segmentsToSeal, newRanges, scaleTimestamp, false,
                        context, executor)
                        .thenComposeAsync(startScaleResponse -> {
                            AtomicBoolean scaling = new AtomicBoolean(true);
                            return FutureHelpers.loop(scaling::get, () ->
                                    streamMetadataStore.getState(scope, stream, null, executor)
                                            .thenAccept(state -> scaling.set(state.equals(State.SCALING))), executor)
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