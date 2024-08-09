public Builder<T> rendering(Rendering config)
		{
			checkForNull(config, "Rendering is null.");
			updateStatus(Properties.RENDERING, Status.ALREADY_SET);
			rendering = config;
			return this;
		}