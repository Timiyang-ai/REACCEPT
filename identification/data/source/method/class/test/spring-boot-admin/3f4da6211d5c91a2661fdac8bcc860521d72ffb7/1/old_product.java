public Application deregister(String id) {
		Application app = store.delete(id);
		if (app != null) {
			LOGGER.info("Application {} unregistered ", app);
			publisher.publishEvent(new ClientApplicationDeregisteredEvent(this, app));
		}
		return app;
	}