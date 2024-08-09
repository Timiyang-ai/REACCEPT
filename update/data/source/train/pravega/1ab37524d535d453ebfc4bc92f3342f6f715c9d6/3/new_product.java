@Override
    public void getScalingEvents(final String scopeName, final String streamName, final Long from, final Long to,
                                 final SecurityContext securityContext, final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "getScalingEvents");

        try {
            authenticate(scopeName + "/" + streamName, READ);
        } catch (AuthenticationException e) {
            log.warn("Get scaling events for {} failed due to authentication failure.", scopeName + "/" + streamName);
            asyncResponse.resume(Response.status(Status.UNAUTHORIZED).build());
            LoggerHelpers.traceLeave(log, "Get scaling events", traceId);
            return;
        }

        if (from < 0 || to < 0 || from > to) {
            log.warn("Received invalid request from client for scopeName/streamName: {}/{} ", scopeName, streamName);
            asyncResponse.resume(Response.status(Status.BAD_REQUEST).build());
            LoggerHelpers.traceLeave(log, "getScalingEvents", traceId);
            return;
        }

        controllerService.getScaleRecords(scopeName, streamName).thenApply(listScaleMetadata -> {
            Iterator<ScaleMetadata> metadataIterator = listScaleMetadata.iterator();
            List<ScaleMetadata> finalScaleMetadataList = new ArrayList<ScaleMetadata>();

            // referenceEvent is the Event used as reference for the events between 'from' and 'to'.
            ScaleMetadata referenceEvent = null;

            while (metadataIterator.hasNext()) {
                ScaleMetadata scaleMetadata = metadataIterator.next();
                if (scaleMetadata.getTimestamp() >= from && scaleMetadata.getTimestamp() <= to) {
                    finalScaleMetadataList.add(scaleMetadata);
                } else if ((scaleMetadata.getTimestamp() < from) &&
                            !(referenceEvent != null && referenceEvent.getTimestamp() > scaleMetadata.getTimestamp())) {
                    // This check is required to store a reference event i.e. an event before the 'from' datetime
                    referenceEvent = scaleMetadata;
                }
            }

            if (referenceEvent != null) {
                finalScaleMetadataList.add(0, referenceEvent);
            }
            log.info("Successfully fetched required scaling events for scope: {}, stream: {}", scopeName, streamName);
            return Response.status(Status.OK).entity(finalScaleMetadataList).build();
        }).exceptionally(exception -> {
            if (exception.getCause() instanceof StoreException.DataNotFoundException
                    || exception instanceof StoreException.DataNotFoundException) {
                log.warn("Stream/Scope name: {}/{} not found", scopeName, streamName);
                return Response.status(Status.NOT_FOUND).build();
            } else {
                log.warn("getScalingEvents for scopeName/streamName: {}/{} failed with exception ",
                        scopeName, streamName, exception);
                return Response.status(Status.INTERNAL_SERVER_ERROR).build();
            }
        }).thenApply(asyncResponse::resume)
                .thenAccept(x -> LoggerHelpers.traceLeave(log, "getScalingEvents", traceId));
    }