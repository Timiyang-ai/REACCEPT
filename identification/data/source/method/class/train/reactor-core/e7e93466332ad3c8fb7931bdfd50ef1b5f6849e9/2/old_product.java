public static boolean isErrorCallbackNotImplemented(Throwable cause) {
		return cause != null && cause.getClass().equals(ErrorCallbackNotImplemented
				.class);
	}