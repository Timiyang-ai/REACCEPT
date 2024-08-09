String execute(Collector collector, CollectorServer collectorServer, String counterName, // NOPMD
			String sessionId, String threadId, String jobId, String cacheId) throws IOException {
		// CHECKSTYLE:ON
		return execute(collector, collectorServer, null, counterName, sessionId, threadId, jobId,
				cacheId);
	}