public PostExecutionInterceptorContext excludeCallTree(String reason) {
		if (!mustPreserveCallTree) {
			logger.debug("Excluding call tree because {}", reason);
			excludeCallTree = true;
		}
		return this;
	}