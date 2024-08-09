public static RuntimeException bubble(Throwable t) {
		throwIfFatal(t);
		if(t instanceof UpstreamException){
			return (UpstreamException) t;
		}
		throw new UpstreamException(t);
	}