public static WebMonitorExtension loadWebSubmissionExtension(
			GatewayRetriever<? extends DispatcherGateway> leaderRetriever,
			CompletableFuture<String> restAddressFuture,
			Time timeout,
			Map<String, String> responseHeaders,
			java.nio.file.Path uploadDir,
			Executor executor,
			Configuration configuration) throws FlinkException {

		if (isFlinkRuntimeWebInClassPath()) {
			try {
				final Constructor<?> webSubmissionExtensionConstructor = Class
					.forName("org.apache.flink.runtime.webmonitor.WebSubmissionExtension")
					.getConstructor(
						Configuration.class,
						CompletableFuture.class,
						GatewayRetriever.class,
						Map.class,
						java.nio.file.Path.class,
						Executor.class,
						Time.class);

				return (WebMonitorExtension) webSubmissionExtensionConstructor.newInstance(
					configuration,
					restAddressFuture,
					leaderRetriever,
					responseHeaders,
					uploadDir,
					executor,
					timeout);
			} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
				throw new FlinkException("Could not load web submission extension.", e);
			}
		} else {
			throw new FlinkException("The module flink-runtime-web could not be found in the class path. Please add " +
				"this jar in order to enable web based job submission.");
		}
	}