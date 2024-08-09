public Builder<T> rendering(Rendering config)
		{
			checkForNull(config, "Rendering is null.");
			updateStatus(Properties.RESIZER_FACTORY, Status.CANNOT_SET);
			updateStatus(Properties.RENDERING, Status.ALREADY_SET);
			rendering = config;
			return this;
		}