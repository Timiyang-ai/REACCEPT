@Override
  public void loadValidators(final Props props, final Logger log) {
    this.validators = new LinkedHashMap<>();
    // Add the default validator
    final DirectoryFlowLoader flowLoader = new DirectoryFlowLoader(props, log);
    this.validators.put(flowLoader.getValidatorName(), flowLoader);

    if (!props.containsKey(ValidatorConfigs.XML_FILE_PARAM)) {
      logger.warn(
          "Azkaban properties file does not contain the key " + ValidatorConfigs.XML_FILE_PARAM);
      return;
    }
    final String xmlPath = props.get(ValidatorConfigs.XML_FILE_PARAM);
    final File file = new File(xmlPath);
    if (!file.exists()) {
      logger.error("Azkaban validator configuration file " + xmlPath + " does not exist.");
      return;
    }

    // Creating the document builder to parse xml.
    final DocumentBuilderFactory docBuilderFactory =
        DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = null;
    try {
      builder = docBuilderFactory.newDocumentBuilder();
    } catch (final ParserConfigurationException e) {
      throw new ValidatorManagerException(
          "Exception while parsing validator xml. Document builder not created.", e);
    }

    Document doc = null;
    try {
      doc = builder.parse(file);
    } catch (final SAXException e) {
      throw new ValidatorManagerException("Exception while parsing " + xmlPath
          + ". Invalid XML.", e);
    } catch (final IOException e) {
      throw new ValidatorManagerException("Exception while parsing " + xmlPath
          + ". Error reading file.", e);
    }

    final NodeList tagList = doc.getChildNodes();
    final Node azkabanValidators = tagList.item(0);

    final NodeList azkabanValidatorsList = azkabanValidators.getChildNodes();
    for (int i = 0; i < azkabanValidatorsList.getLength(); ++i) {
      final Node node = azkabanValidatorsList.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        if (node.getNodeName().equals(VALIDATOR_TAG)) {
          parseValidatorTag(node, props, log);
        }
      }
    }
  }