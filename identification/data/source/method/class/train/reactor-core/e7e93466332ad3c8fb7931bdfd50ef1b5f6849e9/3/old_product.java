public static boolean isErrorCallbackNotImplemented(Throwable t) {
		return t != null && t.getClass().equals(ErrorCallbackNotImplemented
				.class);
	}