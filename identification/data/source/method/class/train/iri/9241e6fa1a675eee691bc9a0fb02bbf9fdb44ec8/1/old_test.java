    @Test
    public void process() {
        BatchedHasher fakeBatchedCurl = BatchedHasherFactory.create(BatchedHasherFactory.Type.FakeBatchedCURL81);
        HashingStage stage = new HashingStage(fakeBatchedCurl);
        HashingPayload hashingPayload = new HashingPayload(null, SampleTransaction.TRITS_OF_SAMPLE_TX,
                SampleTransaction.BYTES_DIGEST_OF_SAMPLE_TX, Hash.NULL_HASH);
        ProcessingContext ctx = new ProcessingContext(null, hashingPayload);
        hashingPayload.setHashRequest(new HashRequest(SampleTransaction.TRITS_OF_SAMPLE_TX, trits -> {
            ctx.setNextStage(TransactionProcessingPipeline.Stage.VALIDATION);
        }));
        stage.process(ctx);

        assertEquals("should submit the validation stage next", TransactionProcessingPipeline.Stage.VALIDATION,
                ctx.getNextStage());
    }