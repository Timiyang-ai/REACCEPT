@SuppressWarnings({"unchecked", "rawtypes"})
	public static <T extends RestfulGateway> Collection<Tuple2<RestHandlerSpecification, ChannelInboundHandler>> tryLoadJarHandlers(
			GatewayRetriever<T> leaderRetriever,
			CompletableFuture<String> restAddressFuture,
			Time timeout,
			Map<String, String> responseHeaders,
			java.nio.file.Path uploadDir,
			Executor executor,
			Configuration configuration) {

		if (!isFlinkRuntimeWebInClassPath()) {
			return Collections.emptyList();
		}

		final String jarHandlerPackageName = "org.apache.flink.runtime.webmonitor.handlers.";
		try {
			final Constructor<?> jarUploadHandlerConstrutor = Class
				.forName(jarHandlerPackageName + "JarUploadHandler")
				.getConstructor(
					CompletableFuture.class,
					GatewayRetriever.class,
					Time.class,
					Map.class,
					MessageHeaders.class,
					java.nio.file.Path.class,
					Executor.class);

			final MessageHeaders jarUploadMessageHeaders = (MessageHeaders) Class
				.forName(jarHandlerPackageName + "JarUploadMessageHeaders")
				.newInstance();

			final ChannelInboundHandler jarUploadHandler = (ChannelInboundHandler) jarUploadHandlerConstrutor
				.newInstance(
					restAddressFuture,
					leaderRetriever,
					timeout,
					responseHeaders,
					jarUploadMessageHeaders,
					uploadDir,
					executor);

			final Constructor<?> jarListHandlerConstructor = Class
				.forName(jarHandlerPackageName + "JarListHandler")
				.getConstructor(
					CompletableFuture.class,
					GatewayRetriever.class,
					Time.class,
					Map.class,
					MessageHeaders.class,
					File.class,
					Executor.class);

			final MessageHeaders jarListHeaders = (MessageHeaders) Class
				.forName(jarHandlerPackageName + "JarListHeaders")
				.newInstance();

			final ChannelInboundHandler jarListHandler = (ChannelInboundHandler) jarListHandlerConstructor
				.newInstance(
					restAddressFuture,
					leaderRetriever,
					timeout,
					responseHeaders,
					jarListHeaders,
					uploadDir.toFile(),
					executor);

			final Constructor<?> jarRunHandlerConstructor = Class
				.forName(jarHandlerPackageName + "JarRunHandler")
				.getConstructor(
					CompletableFuture.class,
					GatewayRetriever.class,
					Time.class,
					Map.class,
					MessageHeaders.class,
					java.nio.file.Path.class,
					Configuration.class,
					Executor.class);

			final MessageHeaders jarRunHandlerHeaders = (MessageHeaders) Class
				.forName(jarHandlerPackageName + "JarRunHeaders")
				.newInstance();

			final ChannelInboundHandler jarRunHandler = (ChannelInboundHandler) jarRunHandlerConstructor
				.newInstance(
					restAddressFuture,
					leaderRetriever,
					timeout,
					responseHeaders,
					jarRunHandlerHeaders,
					uploadDir,
					configuration,
					executor);

			return Arrays.asList(
				Tuple2.of(jarUploadMessageHeaders, jarUploadHandler),
				Tuple2.of(jarListHeaders, jarListHandler),
				Tuple2.of(jarRunHandlerHeaders, jarRunHandler));
		} catch (ClassNotFoundException | InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}