@SuppressWarnings("unchecked")
	public static <T> Subscriber<T> drainSubscriber() {
		return (Subscriber<T>)DrainSubscriber.INSTANCE;
	}