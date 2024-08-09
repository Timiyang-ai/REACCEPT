@Override
	public CompletableFuture<Either<List<CompletionItem>, CompletionList>> completion(TextDocumentPositionParams position) {
		logInfo(">> document/completion");
		CompletionHandler handler = new CompletionHandler();
		return handler.completion(position);
	}