public Builder<T> antialiasing(Antialiasing config)
		{
			checkForNull(config, "Antialiasing is null.");
			updateStatus(Properties.RESIZER_FACTORY, Status.CANNOT_SET);
			updateStatus(Properties.ANTIALIASING, Status.ALREADY_SET);
			antialiasing = config;
			return this;
		}