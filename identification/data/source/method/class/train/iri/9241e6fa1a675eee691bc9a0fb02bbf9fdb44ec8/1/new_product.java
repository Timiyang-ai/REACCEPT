@Override
    public ProcessingContext process(ProcessingContext ctx) {
        HashingPayload payload = (HashingPayload) ctx.getPayload();
        batchedHasher.submitHashingRequest(payload.getHashRequest());
        return ctx;
    }