public static boolean isSavepoint(CheckpointProperties props) {
		return STANDARD_SAVEPOINT.equals(props);
	}