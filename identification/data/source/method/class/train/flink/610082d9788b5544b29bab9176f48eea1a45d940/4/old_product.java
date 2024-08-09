@Override
	public void configure(Configuration parameters)
	{
		super.configure(parameters);
		
		String delimString = parameters.getString(RECORD_DELIMITER, "\n");
		if (delimString == null) {
			throw new IllegalArgumentException("The delimiter not be null.");
		}

		this.delimiter = delimString.getBytes();
		
		// set the number of samples
		this.numLineSamples = DEFAULT_NUM_SAMPLES;
		String samplesString = parameters.getString(NUM_STATISTICS_SAMPLES, null);
		
		if (samplesString != null) {
			try {
				this.numLineSamples = Integer.parseInt(samplesString);
			}
			catch (NumberFormatException nfex) {
				if (LOG.isWarnEnabled())
					LOG.warn("Invalid value for number of samples to take: " + samplesString +
							". Using default value of " + DEFAULT_NUM_SAMPLES);
			}
		}
	}