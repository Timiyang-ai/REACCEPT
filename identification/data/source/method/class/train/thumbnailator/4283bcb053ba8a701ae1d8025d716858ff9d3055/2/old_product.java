public Builder<T> dithering(Dithering config)
		{
			checkForNull(config, "Dithering is null.");
			updateStatus(Properties.DITHERING, Status.ALREADY_SET);
			dithering = config;
			return this;
		}