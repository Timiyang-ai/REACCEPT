@SuppressWarnings("unchecked")
    public static Object getService(Class cls) {
		return getServiceContext().getService(cls);
	}