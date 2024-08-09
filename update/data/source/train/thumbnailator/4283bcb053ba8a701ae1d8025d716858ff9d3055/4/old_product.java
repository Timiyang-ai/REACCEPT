public Builder<T> antialiasing(Antialiasing config)
		{
			checkForNull(config, "Antialiasing is null.");
			updateStatus(Properties.ANTIALIASING, Status.ALREADY_SET);
			antialiasing = config;
			return this;
		}