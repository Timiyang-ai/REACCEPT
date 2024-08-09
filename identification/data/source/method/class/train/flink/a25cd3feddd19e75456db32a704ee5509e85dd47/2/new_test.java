	@Test
	public void uploadAndSetUserJars() throws Exception {
		java.nio.file.Path tmpDir = temporaryFolder.newFolder().toPath();
		JobGraph jobGraph = new JobGraph();

		Collection<Path> jars = Arrays.asList(
			new Path(Files.createFile(tmpDir.resolve("jar1.jar")).toString()),
			new Path(Files.createFile(tmpDir.resolve("jar2.jar")).toString()));

		jars.forEach(jobGraph::addJar);

		assertEquals(jars.size(), jobGraph.getUserJars().size());
		assertEquals(0, jobGraph.getUserJarBlobKeys().size());

		ClientUtils.extractAndUploadJobGraphFiles(jobGraph, () -> new BlobClient(new InetSocketAddress("localhost", blobServer.getPort()), new Configuration()));

		assertEquals(jars.size(), jobGraph.getUserJars().size());
		assertEquals(jars.size(), jobGraph.getUserJarBlobKeys().size());
		assertEquals(jars.size(), jobGraph.getUserJarBlobKeys().stream().distinct().count());

		for (PermanentBlobKey blobKey : jobGraph.getUserJarBlobKeys()) {
			blobServer.getFile(jobGraph.getJobID(), blobKey);
		}
	}