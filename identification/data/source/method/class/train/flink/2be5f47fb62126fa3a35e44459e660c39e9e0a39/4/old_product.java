public static WebMonitorExtension loadWebSubmissionExtension(
			GatewayRetriever<? extends DispatcherGateway> leaderRetriever,
			Time timeout,
			Map<String, String> responseHeaders,
			CompletableFuture<String> localAddressFuture,
			java.nio.file.Path uploadDir,
			Executor executor,
			Configuration configuration) throws FlinkException {

		if (isFlinkRuntimeWebInClassPath()) {
			try {
				final Constructor<?> webSubmissionExtensionConstructor = Class
					.forName("org.apache.flink.runtime.webmonitor.WebSubmissionExtension")
					.getConstructor(
						Configuration.class,
						GatewayRetriever.class,
						Map.class,
						CompletableFuture.class,
						java.nio.file.Path.class,
						Executor.class,
						Time.class);

				return (WebMonitorExtension) webSubmissionExtensionConstructor.newInstance(
					configuration,
					leaderRetriever,
					responseHeaders,
					localAddressFuture,
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