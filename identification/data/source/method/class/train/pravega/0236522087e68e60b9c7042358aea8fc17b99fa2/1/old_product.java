@Override
    public Status createStream(StreamConfig streamConfig) throws TException {
        return FutureHelpers.getAndHandleExceptions(createStreamInternal(ModelHelper.encode(streamConfig)), RuntimeException::new);
    }