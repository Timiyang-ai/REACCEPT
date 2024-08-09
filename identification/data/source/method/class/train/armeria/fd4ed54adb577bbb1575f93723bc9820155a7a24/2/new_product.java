public Retrofit build() {
        checkState(baseUrl != null, "baseUrl not set");
        final URI uri = URI.create(baseUrl);
        final String fullUri = SessionProtocol.of(uri.getScheme()) + "://" + uri.getAuthority();
        final HttpClient baseHttpClient = HttpClient.of(
                clientFactory, fullUri, configurator.apply(fullUri, new ClientOptionsBuilder()).build());
        return retrofitBuilder.baseUrl(convertToOkHttpUrl(baseHttpClient, uri.getPath(), GROUP_PREFIX))
                              .callFactory(new ArmeriaCallFactory(baseHttpClient, clientFactory, configurator))
                              .build();
    }