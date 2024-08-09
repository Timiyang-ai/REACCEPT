protected <T extends RemoteFile> Map<String, T> addAndFilterFilesInTransaction(Class<T> remoteFileClass, Map<String, T> remoteFiles)
			throws StorageException {
		Map<String, T> filteredFiles = new HashMap<String, T>();

		Set<TransactionTO> transactions = new HashSet<TransactionTO>();
		Set<RemoteFile> dummyDeletedFiles = new HashSet<RemoteFile>();
		Set<RemoteFile> filesToIgnore = new HashSet<RemoteFile>();

		if (!remoteFileClass.equals(TransactionRemoteFile.class)) {
			// If we are listing transaction files, we don't want to ignore any
			transactions = retrieveRemoteTransactions().keySet();
			filesToIgnore = getFilesInTransactions(transactions);
			dummyDeletedFiles = getDummyDeletedFiles(transactions);
		}

		for (RemoteFile deletedFile : dummyDeletedFiles) {
			if (deletedFile.getClass().equals(remoteFileClass)) {
				T deletedTFile = remoteFileClass.cast(deletedFile);
				filteredFiles.put(deletedTFile.getName(), deletedTFile);
			}
		}

		for (String fileName : remoteFiles.keySet()) {
			if (!filesToIgnore.contains(remoteFiles.get(fileName))) {
				filteredFiles.put(fileName, remoteFiles.get(fileName));
			}
		}

		return filteredFiles;
	}