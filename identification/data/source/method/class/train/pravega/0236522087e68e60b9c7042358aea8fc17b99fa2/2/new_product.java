@Override
    public Status createStream(StreamConfig streamConfig) throws TException {
        String stream = streamConfig.getName();

        if (streamStore.createStream(stream, ModelHelper.encode(streamConfig))) {
            streamStore.getActiveSegments(stream)
                .getCurrent()
                .stream()
                .parallel()
                .forEach(i -> notifyNewSegment(streamConfig.getScope(), stream, i));
            return Status.SUCCESS;
        } else {
            return Status.FAILURE;
        }
    }