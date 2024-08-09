@Override
	public void uploadPart(RefCountedFSOutputStream file) throws IOException {
		// this is to guarantee that nobody is
		// writing to the file we are uploading.
		checkState(file.isClosed());

		final CompletableFuture<PartETag> future = new CompletableFuture<>();
		uploadsInProgress.add(future);

		final long partLength = file.getPos();
		currentUploadInfo.registerNewPart(partLength);

		file.retain(); // keep the file while the async upload still runs
		uploadThreadPool.execute(new UploadTask(s3MPUploader, currentUploadInfo, file, future));
	}