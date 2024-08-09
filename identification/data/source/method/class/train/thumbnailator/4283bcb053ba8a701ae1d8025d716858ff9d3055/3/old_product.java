public Builder<T> resizer(Resizer resizer)
		{
			checkForNull(resizer, "Resizer is null.");
			updateStatus(Properties.RESIZER, Status.ALREADY_SET);
			updateStatus(Properties.SCALING_MODE, Status.CANNOT_SET);
			this.resizer = resizer;
			return this;
		}