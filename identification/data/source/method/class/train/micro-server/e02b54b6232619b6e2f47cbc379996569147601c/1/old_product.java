public <T> Stream<T> getSummariesStream(ListObjectsRequest req, Function<S3ObjectSummary, T> processor) {
		return getAllSummaries(req).stream().map(processor);
	}