@SuppressWarnings("unchecked")
	public static <T> CoreSubscriber<@NonNull T> drainSubscriber() {
		return (CoreSubscriber<T>)DrainSubscriber.INSTANCE;
	}