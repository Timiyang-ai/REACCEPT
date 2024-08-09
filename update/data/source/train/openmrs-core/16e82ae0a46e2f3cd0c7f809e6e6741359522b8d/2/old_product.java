public List<GlobalProperty> saveGlobalProperties(List<GlobalProperty> props) throws APIException {
		log.debug("saving a list of global properties");
		
		// delete all properties not in this new list
		for (GlobalProperty gp : getGlobalProperties()) {
			if (!props.contains(gp))
				purgeGlobalProperty(gp);
		}
		
		// add all of the new properties
		for (GlobalProperty prop : props) {
			if (prop.getProperty() != null && prop.getProperty().length() > 0) {
				saveGlobalProperty(prop);
			}
		}
		
		return props;
	}