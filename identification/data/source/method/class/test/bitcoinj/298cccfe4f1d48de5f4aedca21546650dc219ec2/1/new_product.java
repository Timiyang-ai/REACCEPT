public void addAndActivateHDChain(DeterministicKeyChain chain) {
        checkState(isSupportsDeterministicChains(), "doesn't support deterministic chains");
        log.info("Creating and activating a new HD chain: {}", chain);
        for (ListenerRegistration<KeyChainEventListener> registration : basic.getListeners())
            chain.addEventListener(registration.listener, registration.executor);
        if (lookaheadSize >= 0)
            chain.setLookaheadSize(lookaheadSize);
        if (lookaheadThreshold >= 0)
            chain.setLookaheadThreshold(lookaheadThreshold);
        chains.add(chain);
    }