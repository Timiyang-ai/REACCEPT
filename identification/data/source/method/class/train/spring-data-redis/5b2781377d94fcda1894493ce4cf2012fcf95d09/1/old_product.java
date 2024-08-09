public Boolean expire(long timeout, TimeUnit unit) {
		return operations.expire(key, timeout, unit);
	}