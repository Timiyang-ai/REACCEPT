CompletableFuture<Result<List<LogData>, BatchProcessorFailure>> retryReadRecords
    (List<Long> addresses, AtomicInteger readRetries) {

        try {
            return IRetry.build(ExponentialBackoffRetry.class, RetryExhaustedException.class, () -> {

                Supplier<CompletableFuture<Result<List<LogData>, BatchProcessorFailure>>> pipeline =
                        () -> readRecords(addresses, readRetries);

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
                    // If an error occurred, increment retries.
                    readRetries.incrementAndGet();

                    if (readRetries.get() >= MAX_RETRIES) {
                        throw new RetryExhaustedException("Read retries are exhausted");
                    } else {
                        log.warn("Retried {} times", readRetries.get());
                        throw new RetryNeededException();
                    }
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