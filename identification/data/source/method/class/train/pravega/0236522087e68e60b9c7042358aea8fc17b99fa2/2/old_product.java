@Override
    public Status createStream(StreamConfig streamConfig) throws TException {
        return FutureHelpers.getAndHandleExceptions(adminApi.createStream(ModelHelper.encode(streamConfig)),
                RuntimeException::new);
    }