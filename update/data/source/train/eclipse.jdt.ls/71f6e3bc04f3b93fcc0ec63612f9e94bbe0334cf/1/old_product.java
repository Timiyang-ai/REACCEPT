@Override
	public CompletableFuture<Either<List<CompletionItem>, CompletionList>> completion(CompletionParams position) {
		logInfo(">> document/completion");
		CompletionHandler handler = new CompletionHandler();
		final IProgressMonitor[] monitors = new IProgressMonitor[1];
		CompletableFuture<Either<List<CompletionItem>, CompletionList>> result = computeAsync((monitor) -> {
			monitors[0] = monitor;
			if (Boolean.getBoolean(JAVA_LSP_JOIN_ON_COMPLETION)) {
				waitForLifecycleJobs(monitor);
			}
			return handler.completion(position, monitor);
		});
		result.join();
		if (monitors[0].isCanceled()) {
			result.cancel(true);
		}
		return result;
	}