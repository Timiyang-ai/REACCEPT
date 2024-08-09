@SuppressWarnings("unchecked")
    public static <T extends Object> T getService(Class<? extends T> cls) {
		return getServiceContext().getService(cls);
	}