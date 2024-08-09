public void registerInterceptors(ServerBuilder<?> builder) {
        try {
            if (serverConfig.isAuthorizationEnabled()) {
                ServiceLoader<PravegaAuthHandler> loader = ServiceLoader.load(PravegaAuthHandler.class);
                for (PravegaAuthHandler handler : loader) {
                    try {
                        handler.initialize(serverConfig);
                        synchronized (this) {
                            if (handlerMap.putIfAbsent(handler.getHandlerName(), handler) != null) {
                                log.warn("Handler with name {} already exists. Not replacing it with the latest handler");
                                continue;
                            }
                        }
                        builder.intercept(new PravegaInterceptor(handler));
                    } catch (Exception e) {
                        log.warn("Exception while initializing auth handler {}", handler, e);
                    }

                }
            }
        } catch (Exception e) {
            log.warn("Exception while loading the auth handlers", e);
        }
    }