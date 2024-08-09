@SuppressWarnings("unchecked")
	public static <T> CoreSubscriber<T> drainSubscriber() {
		return (CoreSubscriber<T>)DrainSubscriber.INSTANCE;
	}