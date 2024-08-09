	@Test
	public void uploadAndSetUserArtifacts() throws Exception {
		java.nio.file.Path tmpDir = temporaryFolder.newFolder().toPath();
		JobGraph jobGraph = new JobGraph();

		Collection<DistributedCache.DistributedCacheEntry> localArtifacts = Arrays.asList(
			new DistributedCache.DistributedCacheEntry(Files.createFile(tmpDir.resolve("art1")).toString(), true, true),
			new DistributedCache.DistributedCacheEntry(Files.createFile(tmpDir.resolve("art2")).toString(), true, false),
			new DistributedCache.DistributedCacheEntry(Files.createFile(tmpDir.resolve("art3")).toString(), false, true),
			new DistributedCache.DistributedCacheEntry(Files.createFile(tmpDir.resolve("art4")).toString(), true, false)
		);

		Collection<DistributedCache.DistributedCacheEntry> distributedArtifacts = Arrays.asList(
			new DistributedCache.DistributedCacheEntry("hdfs://localhost:1234/test", true, false)
		);

		for (DistributedCache.DistributedCacheEntry entry : localArtifacts) {
			jobGraph.addUserArtifact(entry.filePath, entry);
		}
		for (DistributedCache.DistributedCacheEntry entry : distributedArtifacts) {
			jobGraph.addUserArtifact(entry.filePath, entry);
		}

		final int totalNumArtifacts = localArtifacts.size() + distributedArtifacts.size();

		assertEquals(totalNumArtifacts, jobGraph.getUserArtifacts().size());
		assertEquals(0, jobGraph.getUserArtifacts().values().stream().filter(entry -> entry.blobKey != null).count());

		ClientUtils.extractAndUploadJobGraphFiles(jobGraph, () -> new BlobClient(new InetSocketAddress("localhost", blobServer.getPort()), new Configuration()));

		assertEquals(totalNumArtifacts, jobGraph.getUserArtifacts().size());
		assertEquals(localArtifacts.size(), jobGraph.getUserArtifacts().values().stream().filter(entry -> entry.blobKey != null).count());
		assertEquals(distributedArtifacts.size(), jobGraph.getUserArtifacts().values().stream().filter(entry -> entry.blobKey == null).count());
		// 1 unique key for each local artifact, and null for distributed artifacts
		assertEquals(localArtifacts.size() + 1, jobGraph.getUserArtifacts().values().stream().map(entry -> entry.blobKey).distinct().count());
		for (DistributedCache.DistributedCacheEntry original : localArtifacts) {
			assertState(original, jobGraph.getUserArtifacts().get(original.filePath), false, jobGraph.getJobID());
		}
		for (DistributedCache.DistributedCacheEntry original : distributedArtifacts) {
			assertState(original, jobGraph.getUserArtifacts().get(original.filePath), true, jobGraph.getJobID());
		}
	}