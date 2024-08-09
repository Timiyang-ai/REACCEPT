public <T> Stream<T> getSummariesStream(ListObjectsRequest req, Function<S3ObjectSummary, T> processor) {
		Iterable<S3ObjectSummary> iterable = () -> new S3ObjectSummaryIterator(client, req);
		return StreamSupport.stream(iterable.spliterator(), false).map(processor);
	}