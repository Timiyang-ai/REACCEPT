public Retrofit build() {
        checkState(baseUrl != null, "baseUrl not set");
        final URI uri = URI.create(baseUrl);
        final Scheme scheme = Scheme.of(SerializationFormat.NONE, SessionProtocol.of(uri.getScheme()));
        final String fullUri = scheme.uriText() + "://" + uri.getAuthority();
        final HttpClient baseHttpClient =
                Clients.newClient(clientFactory, fullUri, HttpClient.class,
                                  configurator.apply(fullUri, new ClientOptionsBuilder()).build());
        return retrofitBuilder.baseUrl(convertToOkHttpUrl(baseHttpClient, uri.getPath(), GROUP_PREFIX))
                              .callFactory(new ArmeriaCallFactory(baseHttpClient, clientFactory, configurator,
                                                                  GROUP_PREFIX))
                              .build();
    }