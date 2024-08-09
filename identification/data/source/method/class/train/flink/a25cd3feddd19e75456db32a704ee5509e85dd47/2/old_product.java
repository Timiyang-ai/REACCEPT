private static void uploadAndSetUserJars(JobGraph jobGraph, BlobClient blobClient) throws IOException {
		Collection<PermanentBlobKey> blobKeys = uploadUserJars(jobGraph.getJobID(), jobGraph.getUserJars(), blobClient);
		setUserJarBlobKeys(blobKeys, jobGraph);
	}