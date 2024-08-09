public Class<?> getCallerClass(int callStackDepth) {
		if (callStackDepth < 0)
			throw new APIException("Call stack depth cannot be less than 0");
		
		//SecurityManager may appear more than once in classContext
		int skipClasses = 1;
		Class<?>[] classContext = getClassContext();
		for (Class<?> clazz : classContext) {
			if (SecurityManager.class.isAssignableFrom(clazz)) {
				skipClasses++;
			} else {
				break;
			}
		}
		
		//Adjust the depth so that "0" is the not this "getCallerClass" method
		return getClassContext()[callStackDepth + skipClasses];
	}