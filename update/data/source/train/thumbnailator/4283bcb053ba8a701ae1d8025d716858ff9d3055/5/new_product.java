public Builder<T> alphaInterpolation(AlphaInterpolation config)
		{
			checkForNull(config, "Alpha interpolation is null.");
			updateStatus(Properties.RESIZER_FACTORY, Status.CANNOT_SET);
			updateStatus(Properties.ALPHA_INTERPOLATION, Status.ALREADY_SET);
			alphaInterpolation = config;
			return this;
		}