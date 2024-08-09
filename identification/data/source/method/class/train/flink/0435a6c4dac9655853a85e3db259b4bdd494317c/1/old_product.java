public static String getTaskManagerStartCommand(
			Configuration flinkConfig,
			ContaineredTaskManagerParameters tmParams,
			String configDirectory,
			String logDirectory,
			boolean hasLogback,
			boolean hasLog4j,
			String mainClass,
			@Nullable String mainArgs) {
		final TaskExecutorResourceSpec taskExecutorResourceSpec = tmParams.getTaskExecutorResourceSpec();
		return getCommonStartCommand(
			flinkConfig,
			ClusterComponent.TASK_MANAGER,
			TaskExecutorResourceUtils.generateJvmParametersStr(taskExecutorResourceSpec),
			configDirectory,
			logDirectory,
			hasLogback,
			hasLog4j,
			mainClass,
			mainArgs
		);
	}