public void start() {
        if (session == null) { // initial handshake
            session = findSessionToResume();
        } else { // start session renegotiation
            if (clientHello != null && this.status != FINISHED) {
                // current negotiation has not completed
                return; // ignore
            }
            if (!session.isValid()) {
                session = null;
            }
        }
        if (session != null) {
            isResuming = true;
        } else if (parameters.getEnableSessionCreation()){    
            isResuming = false;
            session = new SSLSessionImpl(parameters.getSecureRandom());
            session.protocol = ProtocolVersion.getLatestVersion(parameters
                    .getEnabledProtocols());
            recordProtocol.setVersion(session.protocol.version);
        } else {
            fatalAlert(AlertProtocol.HANDSHAKE_FAILURE, "SSL Session may not be created ");
        }
        startSession();
    }