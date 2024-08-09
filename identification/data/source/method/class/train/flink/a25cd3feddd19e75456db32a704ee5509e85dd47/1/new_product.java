private static void uploadAndSetUserArtifacts(JobGraph jobGraph, Collection<Tuple2<String, Path>> artifactPaths, BlobClient blobClient) throws IOException {
		Collection<Tuple2<String, PermanentBlobKey>> blobKeys = uploadUserArtifacts(jobGraph.getJobID(), artifactPaths, blobClient);
		setUserArtifactBlobKeys(jobGraph, blobKeys);
	}