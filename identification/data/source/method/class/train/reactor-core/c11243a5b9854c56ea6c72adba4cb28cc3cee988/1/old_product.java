public static boolean isBubbling(Throwable t){
		return t == CancelException.INSTANCE || t instanceof CancelException;
	}