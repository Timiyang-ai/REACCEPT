@Override
    public void listStreams(final String scopeName, final String showInternalStreams,
                            final SecurityContext securityContext, final AsyncResponse asyncResponse) {
        long traceId = LoggerHelpers.traceEnter(log, "listStreams");

        try {
            authenticate(scopeName, READ);
        } catch (AuthenticationException e) {
            log.warn("List streams for {} failed due to authentication failure.", scopeName);
            asyncResponse.resume(Response.status(Status.UNAUTHORIZED).build());
            LoggerHelpers.traceLeave(log, "listStreams", traceId);
            return;
        }
        boolean showOnlyInternalStreams = showInternalStreams != null && showInternalStreams.equals("true");
        controllerService.listStreamsInScope(scopeName)
                .thenApply(streamsList -> {
                    StreamsList streams = new StreamsList();
                    streamsList.forEach(stream -> {
                        // If internal streams are requested select only the ones that have the special stream names
                        // otherwise display the regular user created streams.
                        if (!showOnlyInternalStreams ^ stream.getStreamName().startsWith(INTERNAL_NAME_PREFIX)) {
                            streams.addStreamsItem(ModelHelper.encodeStreamResponse(stream));
                        }
                    });
                    log.info("Successfully fetched streams for scope: {}", scopeName);
                    return Response.status(Status.OK).entity(streams).build();
                }).exceptionally(exception -> {
                    if (exception.getCause() instanceof StoreException.DataNotFoundException
                            || exception instanceof StoreException.DataNotFoundException) {
                        log.warn("Scope name: {} not found", scopeName);
                        return Response.status(Status.NOT_FOUND).build();
                    } else {
                        log.warn("listStreams for {} failed with exception: {}", scopeName, exception);
                        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
                    }
                }).thenApply(asyncResponse::resume)
                .thenAccept(x -> LoggerHelpers.traceLeave(log, "listStreams", traceId));
    }