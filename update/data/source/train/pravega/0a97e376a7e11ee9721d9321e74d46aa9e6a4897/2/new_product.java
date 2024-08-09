@Override
    public void createStream(final String scopeName, final CreateStreamRequest createStreamRequest,
            final SecurityContext securityContext, final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "createStream");

        String streamName = createStreamRequest.getStreamName();
        try {
            NameUtils.validateUserStreamName(streamName);
        } catch (IllegalArgumentException | NullPointerException e) {
            log.warn("Create stream failed due to invalid stream name {}", streamName);
            asyncResponse.resume(Response.status(Status.BAD_REQUEST).build());
            LoggerHelpers.traceLeave(log, "createStream", traceId);
            return;
        }

        try {
            restAuthHelper.authenticateAuthorize(getAuthorizationHeader(),
                    AuthResourceRepresentation.ofStreamsInScope(scopeName), READ_UPDATE);
        } catch (AuthException e) {
            log.warn("Create stream for {} failed due to authentication failure.", streamName);
            asyncResponse.resume(Response.status(Status.fromStatusCode(e.getResponseCode())).build());
            LoggerHelpers.traceLeave(log, "createStream", traceId);
            return;
        }

        StreamConfiguration streamConfiguration = ModelHelper.getCreateStreamConfig(createStreamRequest);
        controllerService.createStream(scopeName, streamName, streamConfiguration, System.currentTimeMillis())
                .thenApply(streamStatus -> {
                    Response resp = null;
                    if (streamStatus.getStatus() == CreateStreamStatus.Status.SUCCESS) {
                        log.info("Successfully created stream: {}/{}", scopeName, streamName);
                        resp = Response.status(Status.CREATED).
                                entity(ModelHelper.encodeStreamResponse(scopeName, streamName, streamConfiguration)).build();
                    } else if (streamStatus.getStatus() == CreateStreamStatus.Status.STREAM_EXISTS) {
                        log.warn("Stream already exists: {}/{}", scopeName, streamName);
                        resp = Response.status(Status.CONFLICT).build();
                    } else if (streamStatus.getStatus() == CreateStreamStatus.Status.SCOPE_NOT_FOUND) {
                        log.warn("Scope not found: {}", scopeName);
                        resp = Response.status(Status.NOT_FOUND).build();
                    } else if (streamStatus.getStatus() == CreateStreamStatus.Status.INVALID_STREAM_NAME) {
                        log.warn("Invalid stream name: {}", streamName);
                        resp = Response.status(Status.BAD_REQUEST).build();
                    } else {
                        log.warn("createStream failed for : {}/{}", scopeName, streamName);
                        resp = Response.status(Status.INTERNAL_SERVER_ERROR).build();
                    }
                    return resp;
                }).exceptionally(exception -> {
                             log.warn("createStream for {}/{} failed: ", scopeName, streamName, exception);
                             return Response.status(Status.INTERNAL_SERVER_ERROR).build();
                }).thenApply(asyncResponse::resume)
                .thenAccept(x -> LoggerHelpers.traceLeave(log, "createStream", traceId));
    }