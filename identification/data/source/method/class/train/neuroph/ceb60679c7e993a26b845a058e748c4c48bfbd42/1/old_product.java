public Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty("transferFunction.yHigh", new Double(yHigh).toString());
		properties.setProperty("transferFunction.yLow", new Double(yLow).toString());
		return properties;
	}