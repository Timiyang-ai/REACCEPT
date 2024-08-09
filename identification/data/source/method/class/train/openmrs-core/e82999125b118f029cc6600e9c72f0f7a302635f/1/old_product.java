public Class<?> getCallerClass(int callStackDepth) {
		if (callStackDepth < 0)
			throw new APIException("Call stack depth cannot be less than 0");
		
		// adjust the depth so that "0" is the not this "getCallerClass" method
		return getClassContext()[callStackDepth + 2];
	}