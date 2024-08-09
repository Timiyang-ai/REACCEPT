public Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty("transferFunction.yHigh", String.valueOf(yHigh));
		properties.setProperty("transferFunction.yLow", String.valueOf(yLow));
		return properties;
	}