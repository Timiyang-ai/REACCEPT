public Retrofit build() {
        checkState(baseUrl != null, "baseUrl not set");
        final URI uri = URI.create(baseUrl);
        final String fullUri = SessionProtocol.of(uri.getScheme()) + "://" + uri.getAuthority();
        final WebClient baseHttpClient = WebClient.of(
                clientFactory, fullUri, configurator.apply(fullUri, ClientOptions.builder()).build());
        return retrofitBuilder.baseUrl(convertToOkHttpUrl(baseHttpClient, uri.getPath(), GROUP_PREFIX))
                              .callFactory(new ArmeriaCallFactory(
                                      baseHttpClient, clientFactory, configurator,
                                      streaming ? SubscriberFactory.streaming(callbackExecutor)
                                                : SubscriberFactory.blocking()))
                              .build();
    }