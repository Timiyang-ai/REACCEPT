CompletableFuture<Result<List<LogData>, BatchProcessorFailure>> readRecords(List<Long> addresses,
                                                                                AtomicInteger initRetries) {
        return CompletableFuture.supplyAsync(() ->
                Result.of(() -> addressSpaceView.simpleProtocolRead(addresses, readOptions))
                        .mapError(BatchProcessorError::new))
                .thenApply(readResult ->
                        readResult
                                .flatMap(records -> checkReadRecords(addresses, records)))
                .thenCompose(checkedReadResult -> {
                    if (checkedReadResult.isError()) {
                        return retryReadRecords(checkedReadResult.getError().getAddresses(),
                                initRetries);
                    } else {
                        return CompletableFuture.completedFuture(checkedReadResult
                                .mapError(e -> BatchProcessorFailure
                                        .builder().throwable(e).build()));
                    }
                });
    }