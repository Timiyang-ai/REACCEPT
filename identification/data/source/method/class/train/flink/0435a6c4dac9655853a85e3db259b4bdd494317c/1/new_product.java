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
		final String jvmMemOpts = TaskExecutorResourceUtils.generateJvmParametersStr(taskExecutorResourceSpec);
		String args = TaskExecutorResourceUtils.generateDynamicConfigsStr(taskExecutorResourceSpec);
		if (mainArgs != null) {
			args += " " + mainArgs;
		}
		return getCommonStartCommand(
			flinkConfig,
			ClusterComponent.TASK_MANAGER,
			jvmMemOpts,
			configDirectory,
			logDirectory,
			hasLogback,
			hasLog4j,
			mainClass,
			args
		);
	}