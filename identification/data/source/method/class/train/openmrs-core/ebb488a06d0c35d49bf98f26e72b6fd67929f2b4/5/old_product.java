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
			
			this.moduleId = getElement(rootNode, configVersion, "moduleId");
			this.currentVersion = getElement(rootNode, configVersion, "currentVersion");
			this.downloadURL = getElement(rootNode, configVersion, "downloadURL");
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