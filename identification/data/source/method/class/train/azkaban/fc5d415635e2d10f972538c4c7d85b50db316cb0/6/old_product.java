@Override
  public void loadValidators(Props props, Logger log) {
    validators = new LinkedHashMap<String, ProjectValidator>();
    // Add the default validator
    DirectoryFlowLoader flowLoader = new DirectoryFlowLoader(props, log);
    validators.put(flowLoader.getValidatorName(), flowLoader);

    if (!props.containsKey(ValidatorConfigs.XML_FILE_PARAM)) {
      logger.warn("Azkaban properties file does not contain the key " + ValidatorConfigs.XML_FILE_PARAM);
      return;
    }
    String xmlPath = props.get(ValidatorConfigs.XML_FILE_PARAM);
    File file = new File(xmlPath);
    if (!file.exists()) {
      logger.error("Azkaban validator configuration file " + xmlPath + " does not exist.");
      return;
    }

    // Creating the document builder to parse xml.
    DocumentBuilderFactory docBuilderFactory =
        DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = null;
    try {
      builder = docBuilderFactory.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      throw new ValidatorManagerException(
          "Exception while parsing validator xml. Document builder not created.", e);
    }

    Document doc = null;
    try {
      doc = builder.parse(file);
    } catch (SAXException e) {
      throw new ValidatorManagerException("Exception while parsing " + xmlPath
          + ". Invalid XML.", e);
    } catch (IOException e) {
      throw new ValidatorManagerException("Exception while parsing " + xmlPath
          + ". Error reading file.", e);
    }

    NodeList tagList = doc.getChildNodes();
    Node azkabanValidators = tagList.item(0);

    NodeList azkabanValidatorsList = azkabanValidators.getChildNodes();
    for (int i = 0; i < azkabanValidatorsList.getLength(); ++i) {
      Node node = azkabanValidatorsList.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        if (node.getNodeName().equals(VALIDATOR_TAG)) {
          parseValidatorTag(node, props, log);
        }
      }
    }
  }