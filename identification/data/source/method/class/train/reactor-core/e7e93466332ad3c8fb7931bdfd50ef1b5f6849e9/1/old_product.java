public static boolean isErrorCallbackNotImplemented(@Nullable Throwable t) {
		return t != null && t.getClass().equals(ErrorCallbackNotImplemented.class);
	}