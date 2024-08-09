private IdentityHashMap<String, String> getExtensions(Element root, String configVersion) {
		
		IdentityHashMap<String, String> extensions = new IdentityHashMap<String, String>();
		
		NodeList extensionNodes = root.getElementsByTagName("extension");
		if (extensionNodes.getLength() > 0) {
			log.debug("# extensions: " + extensionNodes.getLength());
			int i = 0;
			while (i < extensionNodes.getLength()) {
				Node node = extensionNodes.item(i);
				NodeList nodes = node.getChildNodes();
				int x = 0;
				String point = "", extClass = "";
				while (x < nodes.getLength()) {
					Node childNode = nodes.item(x);
					if ("point".equals(childNode.getNodeName())) {
						point = childNode.getTextContent().trim();
					} else if ("class".equals(childNode.getNodeName())) {
						extClass = childNode.getTextContent().trim();
					}
					x++;
				}
				log.debug("point: " + point + " class: " + extClass);
				
				// point and class are required
				if (point.length() > 0 && extClass.length() > 0) {
					if (point.contains(Extension.extensionIdSeparator)) {
						log.warn("Point id contains illegal character: '" + Extension.extensionIdSeparator + "'");
					} else {
						extensions.put(point, extClass);
					}
				} else {
					log
					        .warn("'point' and 'class' are required for extensions. Given '" + point + "' and '" + extClass
					                + "'");
				}
				i++;
			}
		}
		
		return extensions;
		
	}