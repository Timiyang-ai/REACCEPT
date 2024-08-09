public static List<ModuleFilterDefinition> retrieveFilterDefinitions(Module module) throws ModuleException {
		
		List<ModuleFilterDefinition> filters = new Vector<ModuleFilterDefinition>();
		
		try {
			Element rootNode = module.getConfig().getDocumentElement();
			NodeList filterNodes = rootNode.getElementsByTagName("filter");
			if (filterNodes.getLength() > 0) {
				for (int i = 0; i < filterNodes.getLength(); i++) {
					ModuleFilterDefinition filter = new ModuleFilterDefinition(module);
					Node node = filterNodes.item(i);
					NodeList configNodes = node.getChildNodes();
					for (int j = 0; j < configNodes.getLength(); j++) {
						Node configNode = configNodes.item(j);
						if ("filter-name".equals(configNode.getNodeName())) {
							filter.setFilterName(configNode.getTextContent().trim());
						} else if ("filter-class".equals(configNode.getNodeName())) {
							filter.setFilterClass(configNode.getTextContent().trim());
						} else if ("init-param".equals(configNode.getNodeName())) {
							NodeList paramNodes = configNode.getChildNodes();
							String paramName = "";
							String paramValue = "";
							for (int k = 0; k < paramNodes.getLength(); k++) {
								Node paramNode = paramNodes.item(k);
								if ("param-name".equals(paramNode.getNodeName())) {
									paramName = paramNode.getTextContent().trim();
								} else if ("param-value".equals(paramNode.getNodeName())) {
									paramValue = paramNode.getTextContent().trim();
								}
							}
							filter.addInitParameter(paramName, paramValue);
						}
					}
					filters.add(filter);
				}
			}
		}
		catch (Exception e) {
			throw new ModuleException("Unable to parse filters in module configuration.", e);
		}
		log.debug("Retrieved " + filters.size() + " filters for " + module + ": " + filters);
		return filters;
	}