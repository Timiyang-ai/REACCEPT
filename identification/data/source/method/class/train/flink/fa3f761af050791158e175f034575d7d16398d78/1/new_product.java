public static SnapshotDirectory temporary(@Nonnull File directory) throws IOException {
		return new TemporarySnapshotDirectory(directory);
	}