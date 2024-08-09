protected AmqpSession createSession(String sessionName, Session session, SessionHandler handler) {
        return new ReactorSession(session, handler, sessionName, reactorProvider, handlerProvider,
            getClaimsBasedSecurityNode(), tokenManagerProvider, messageSerializer,
            connectionOptions.getRetry().getTryTimeout());
    }