	private String getTaskManagerStartCommand(
			Configuration cfg,
			boolean hasLogBack,
			boolean hasLog4j,
			String mainClassArgs) {

		final ContaineredTaskManagerParameters containeredParams =
			new ContaineredTaskManagerParameters(taskExecutorResourceSpec, 4, new HashMap<>());

		return KubernetesUtils.getTaskManagerStartCommand(
			cfg,
			containeredParams,
			confDirInPod,
			logDirInPod,
			hasLogBack,
			hasLog4j,
			mainClass,
			mainClassArgs
		);
	}