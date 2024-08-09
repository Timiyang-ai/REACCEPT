@Override
    public void createStream(final String scopeName, final CreateStreamRequest createStreamRequest,
            final SecurityContext securityContext, final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "createStream");

        try {
            NameUtils.validateUserStreamName(createStreamRequest.getStreamName());
        } catch (IllegalArgumentException | NullPointerException e) {
            log.warn("Create stream failed due to invalid stream name {}", createStreamRequest.getStreamName());
            asyncResponse.resume(Response.status(Status.BAD_REQUEST).build());
            LoggerHelpers.traceLeave(log, "createStream", traceId);
            return;
        }
        StreamConfiguration streamConfiguration = ModelHelper.getCreateStreamConfig(createStreamRequest, scopeName);
        //TODO: Retrieve token from headers and authenticate.
        controllerService.createStream(streamConfiguration, System.currentTimeMillis())
                .thenApply(streamStatus -> {
                    Response resp = null;
                    if (streamStatus.getStatus() == CreateStreamStatus.Status.SUCCESS) {
                        log.info("Successfully created stream: {}/{}", scopeName, streamConfiguration.getStreamName());
                        resp = Response.status(Status.CREATED).
                                entity(ModelHelper.encodeStreamResponse(streamConfiguration)).build();
                    } else if (streamStatus.getStatus() == CreateStreamStatus.Status.STREAM_EXISTS) {
                        log.warn("Stream already exists: {}/{}", scopeName, streamConfiguration.getStreamName());
                        resp = Response.status(Status.CONFLICT).build();
                    } else if (streamStatus.getStatus() == CreateStreamStatus.Status.SCOPE_NOT_FOUND) {
                        log.warn("Scope not found: {}", scopeName);
                        resp = Response.status(Status.NOT_FOUND).build();
                    } else if (streamStatus.getStatus() == CreateStreamStatus.Status.INVALID_STREAM_NAME) {
                        log.warn("Invalid stream name: {}", streamConfiguration.getStreamName());
                        resp = Response.status(Status.BAD_REQUEST).build();
                    } else {
                        log.warn("createStream failed for : {}/{}", scopeName, streamConfiguration.getStreamName());
                        resp = Response.status(Status.INTERNAL_SERVER_ERROR).build();
                    }
                    return resp;
                }).exceptionally(exception -> {
                    log.warn("createStream for {}/{} failed {}: ", scopeName, streamConfiguration.getStreamName(),
                             exception);
                    return Response.status(Status.INTERNAL_SERVER_ERROR).build();
                }).thenApply(asyncResponse::resume)
                .thenAccept(x -> LoggerHelpers.traceLeave(log, "createStream", traceId));
    }