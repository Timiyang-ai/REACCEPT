private static void uploadAndSetUserArtifacts(JobGraph jobGraph, BlobClient blobClient) throws IOException {
		Collection<Tuple2<String, Path>> artifactPaths = jobGraph.getUserArtifacts().entrySet().stream()
			.map(entry -> Tuple2.of(entry.getKey(), new Path(entry.getValue().filePath)))
			.collect(Collectors.toList());

		Collection<Tuple2<String, PermanentBlobKey>> blobKeys = uploadUserArtifacts(jobGraph.getJobID(), artifactPaths, blobClient);
		setUserArtifactBlobKeys(jobGraph, blobKeys);
	}