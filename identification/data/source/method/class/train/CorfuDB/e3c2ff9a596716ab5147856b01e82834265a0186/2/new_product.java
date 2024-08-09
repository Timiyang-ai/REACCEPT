CompletableFuture<Result<List<LogData>, BatchProcessorFailure>> retryReadRecords
    (List<Long> addresses, int retriesTried) {

        try {
            AtomicInteger readRetries = new AtomicInteger(retriesTried);
            return IRetry.build(ExponentialBackoffRetry.class, RetryExhaustedException.class, () -> {
                if(readRetries.incrementAndGet() >= MAX_RETRIES){
                    throw new RetryExhaustedException("Read retries are exhausted");
                }
                Supplier<CompletableFuture<Result<List<LogData>, BatchProcessorFailure>>> pipeline =
                        () -> readRecords(addresses, readRetries.get());

                // If a pipeline completed exceptionally, than return the error.
                CompletableFuture<Result<List<LogData>, BatchProcessorFailure>> pipelineFuture =
                        pipeline.get().handle((result, exception) -> {
                            if (Optional.ofNullable(exception).isPresent()) {
                                return Result.error(BatchProcessorFailure.builder().throwable(exception).build());
                            } else {
                                return result;
                            }
                        });
                // Join the result.
                Result<List<LogData>, BatchProcessorFailure> joinResult = pipelineFuture.join();
                if (joinResult.isError()) {
                    throw new RetryNeededException();
                } else {
                    // If the result is not an error, return.
                    return CompletableFuture.completedFuture(joinResult);
                }

            }).setOptions(retry -> {
                retry.setMaxRetryThreshold(MAX_RETRY_TIMEOUT);
                retry.setRandomPortion(RANDOM_FACTOR_BACKOFF);
            }).run();
            // Map to batch processor failure if an interrupt has occurred
            // or the retries were exhausted.
        } catch (InterruptedException | RetryExhaustedException ie) {
            return CompletableFuture.completedFuture(
                    Result.error(BatchProcessorFailure.builder().throwable(ie).build()));
        }
    }