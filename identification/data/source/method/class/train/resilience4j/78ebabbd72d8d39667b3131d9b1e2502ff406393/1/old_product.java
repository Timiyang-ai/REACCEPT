static <T> Supplier<Try<T>> decorateTrySupplier(RateLimiter rateLimiter, Supplier<Try<T>> supplier){
		return () -> {
			try{
				waitForPermission(rateLimiter);
				return supplier.get();
			}catch (RequestNotPermitted requestNotPermitted){
				return Try.failure(requestNotPermitted);
			}
		};
	}