public Builder<T> resizer(Resizer resizer)
		{
			checkForNull(resizer, "Resizer is null.");
			updateStatus(Properties.RESIZER, Status.ALREADY_SET);
			updateStatus(Properties.RESIZER_FACTORY, Status.CANNOT_SET);
			updateStatus(Properties.SCALING_MODE, Status.CANNOT_SET);
			this.resizerFactory = new FixedResizerFactory(resizer);
			return this;
		}