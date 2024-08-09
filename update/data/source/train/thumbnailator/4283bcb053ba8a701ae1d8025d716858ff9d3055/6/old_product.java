public Builder<T> scalingMode(ScalingMode config)
		{
			checkForNull(config, "Scaling mode is null.");
			updateStatus(Properties.SCALING_MODE, Status.ALREADY_SET);
			updateStatus(Properties.RESIZER, Status.CANNOT_SET);
			scalingMode = config;
			return this;
		}