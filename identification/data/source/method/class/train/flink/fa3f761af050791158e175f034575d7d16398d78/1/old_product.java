public static SnapshotDirectory temporary(@Nonnull Path directory) throws IOException {
		return new TemporarySnapshotDirectory(directory);
	}