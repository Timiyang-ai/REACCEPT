	private <T extends RemoteFile> void uploadDownloadListDelete(TransferManager transferManager, File tempFromDir, File tempToDir,
			Class<T> remoteFileClass, T[] remoteFiles) throws Exception {
		for (RemoteFile remoteFile : remoteFiles) {
			File originalLocalFile = new File(tempFromDir, remoteFile.getName());
			File downloadedLocalFile = new File(tempToDir, remoteFile.getName());

			TestFileUtil.createNonRandomFile(originalLocalFile, 5 * 1024);

			transferManager.upload(originalLocalFile, remoteFile);
			transferManager.download(remoteFile, downloadedLocalFile);

			String checksumOriginalFile = StringUtil.toHex(TestFileUtil.createChecksum(originalLocalFile));
			String checksumDownloadedFile = StringUtil.toHex(TestFileUtil.createChecksum(downloadedLocalFile));

			assertEquals("Uploaded file differs from original file, for file " + originalLocalFile, checksumOriginalFile, checksumDownloadedFile);
		}

		Map<String, T> listLocalFilesAfterUpload = transferManager.list(remoteFileClass);
		assertEquals(remoteFiles.length, listLocalFilesAfterUpload.size());

		for (RemoteFile remoteFile : remoteFiles) {
			transferManager.delete(remoteFile);
		}

		Map<String, T> listLocalFileAfterDelete = transferManager.list(remoteFileClass);
		assertEquals(0, listLocalFileAfterDelete.size());
	}