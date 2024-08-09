public void parse() throws ModuleException {
		StringReader stringReader = null;
		try {
			Document updateDoc = null;
			try {
				stringReader = new StringReader(content);
				InputSource inputSource = new InputSource(stringReader);
				inputSource.setSystemId("./");
				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				
				// Disable resolution of external entities. See TRUNK-3942 
				db.setEntityResolver(new EntityResolver() {
					
					public InputSource resolveEntity(String publicId, String systemId) {
						return new InputSource(new StringReader(""));
					}
				});
				
				updateDoc = db.parse(inputSource);
			}
			catch (Exception e) {
				log.warn("Unable to parse content");
				throw new ModuleException("Error parsing update.rdf file: " + content, e);
			}
			
			Element rootNode = updateDoc.getDocumentElement();
			
			String configVersion = rootNode.getAttribute("configVersion");
			
			if (!validConfigVersions().contains(configVersion))
				throw new ModuleException("Invalid configVersion: '" + configVersion + "' found In content: " + content);
			
			if ("1.0".equals(configVersion)) {
				// the only update in the xml file is the 'best fit'
				this.moduleId = getElement(rootNode, configVersion, "moduleId");
				this.currentVersion = getElement(rootNode, configVersion, "currentVersion");
				this.downloadURL = getElement(rootNode, configVersion, "downloadURL");
			} else if ("1.1".equals(configVersion)) {
				
				this.moduleId = rootNode.getAttribute("moduleId");
				
				NodeList nodes = rootNode.getElementsByTagName("update");
				this.currentVersion = ""; // default to the lowest version possible
				
				// loop over all 'update' tags
				for (Integer i = 0; i < nodes.getLength(); i++) {
					Element currentNode = (Element) nodes.item(i);
					String currentVersion = getElement(currentNode, configVersion, "currentVersion");
					// if the currently saved version is less than the current tag
					if (ModuleUtil.compareVersion(this.currentVersion, currentVersion) < 0) {
						String requireOpenMRSVersion = getElement(currentNode, configVersion, "requireOpenMRSVersion");
						// if the openmrs code version is compatible, this node is a winner
						if (requireOpenMRSVersion == null
						        || ModuleUtil.matchRequiredVersions(OpenmrsConstants.OPENMRS_VERSION_SHORT,
						            requireOpenMRSVersion)) {
							this.currentVersion = currentVersion;
							this.downloadURL = getElement(currentNode, configVersion, "downloadURL");
						}
					}
				}
			}
		}
		catch (ModuleException e) {
			// rethrow the moduleException
			throw e;
		}
		finally {
			if (stringReader != null)
				stringReader.close();
		}
		
	}