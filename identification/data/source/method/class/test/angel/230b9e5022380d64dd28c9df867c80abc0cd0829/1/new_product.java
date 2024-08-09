public void stop() {
    if (!stopped.getAndSet(true)) {
      LOG.info("stop heartbeat thread!");
      if (heartbeatThread != null) {
        heartbeatThread.interrupt();
        try {
          heartbeatThread.join();
        } catch (InterruptedException ie) {
          LOG.warn("InterruptedException while stopping heartbeatThread:", ie);
        }
        heartbeatThread = null;
      }

      LOG.info("stop op log merger");
      if (opLogCache != null) {
        opLogCache.stop();
        opLogCache = null;
      }

      LOG.info("stop clock cache");
      if (clockCache != null) {
        clockCache.stop();
        clockCache = null;
      }

      LOG.info("stop matrix cache");
      if (matricesCache != null) {
        matricesCache.stop();
        matricesCache = null;
      }

      LOG.info("stop user request adapater");
      if (userRequestAdapter != null) {
        userRequestAdapter.stop();
        userRequestAdapter = null;
      }

      LOG.info("stop rpc dispacher");
      if (matrixTransClient != null) {
        matrixTransClient.stop();
        matrixTransClient = null;
      }

      if (PSAgentContext.get() != null) {
        PSAgentContext.get().clear();
      }
    }
  }