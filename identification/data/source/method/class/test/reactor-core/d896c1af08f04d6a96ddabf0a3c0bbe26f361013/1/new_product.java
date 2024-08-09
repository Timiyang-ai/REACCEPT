public static <T> boolean addThrowable(AtomicReferenceFieldUpdater<T, Throwable> field,
			T instance,
			Throwable exception) {
		for (; ; ) {
			Throwable current = field.get(instance);

			if (current == TERMINATED) {
				return false;
			}

			if (current instanceof CompositeException) {
				current.addSuppressed(exception);
				return true;
			}

			Throwable update;
			if (current == null) {
				update = exception;
			} else {
				update = multiple(current, exception);
			}

			if (field.compareAndSet(instance, current, update)) {
				return true;
			}
		}
	}