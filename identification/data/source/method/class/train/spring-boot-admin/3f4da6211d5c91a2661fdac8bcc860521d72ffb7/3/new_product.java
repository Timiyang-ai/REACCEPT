public Application deregister(ApplicationId id) {
        Application app = store.delete(id);
        if (app != null) {
            LOGGER.info("Application {} unregistered ", app);
            publisher.publishEvent(new ClientApplicationDeregisteredEvent(id));
        }
        return app;
    }