protected <T extends RemoteFile> Map<String, T> addAndFilterFilesInTransaction(Class<T> remoteFileClass, Map<String, T> remoteFiles)
			throws StorageException {
		
		Map<String, T> filteredFiles = new HashMap<String, T>();

		Set<TransactionTO> transactions = new HashSet<TransactionTO>();
		Set<RemoteFile> dummyDeletedFiles = new HashSet<RemoteFile>();
		Set<RemoteFile> filesToIgnore = new HashSet<RemoteFile>();

		// Ignore files currently listed in a transaction, 
		// unless we are listing transaction files 
		
		boolean ignoreFilesInTransactions = !remoteFileClass.equals(TransactionRemoteFile.class);
		
		if (ignoreFilesInTransactions) {
			transactions = retrieveRemoteTransactions().keySet();
			filesToIgnore = getFilesInTransactions(transactions);
			dummyDeletedFiles = getDummyDeletedFiles(transactions);			
		}

		for (RemoteFile deletedFile : dummyDeletedFiles) {
			if (deletedFile.getClass().equals(remoteFileClass)) {
				T concreteDeletedFile = remoteFileClass.cast(deletedFile);
				filteredFiles.put(concreteDeletedFile.getName(), concreteDeletedFile);
			}
		}
		
		for (String fileName : remoteFiles.keySet()) {
			if (!filesToIgnore.contains(remoteFiles.get(fileName))) {
				filteredFiles.put(fileName, remoteFiles.get(fileName));
			}
		}		

		return filteredFiles;
	}