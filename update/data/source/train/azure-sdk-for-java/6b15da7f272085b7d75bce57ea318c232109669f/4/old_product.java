@Override
    public Mono<AmqpSession> createSession(String sessionName) {
        AmqpSession existingSession = sessionMap.get(sessionName);
        if (existingSession != null) {
            return Mono.just(existingSession);
        }

        return connectionMono.map(connection -> sessionMap.computeIfAbsent(sessionName, key -> {
            final SessionHandler handler =
                handlerProvider.createSessionHandler(connectionId, getHost(), sessionName, connectionOptions.timeout());
            final Session session = connection.session();

            BaseHandler.setHandler(session, handler);
            return new ReactorSession(session, handler, sessionName, reactorProvider, handlerProvider,
                this.getCBSNode(), tokenResourceProvider, connectionOptions.timeout());
        }));
    }