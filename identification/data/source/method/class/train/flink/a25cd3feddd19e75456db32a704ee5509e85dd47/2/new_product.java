private static void uploadAndSetUserJars(JobGraph jobGraph, Collection<Path> userJars, BlobClient blobClient) throws IOException {
		Collection<PermanentBlobKey> blobKeys = uploadUserJars(jobGraph.getJobID(), userJars, blobClient);
		setUserJarBlobKeys(blobKeys, jobGraph);
	}