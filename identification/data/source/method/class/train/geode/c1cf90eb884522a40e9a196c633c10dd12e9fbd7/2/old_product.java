public static LinkedHashMap<String, CacheElement> buildElementMap(final Document doc) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
    final Map<String, List<String>> schemaLocationMap = XmlUtils.buildSchemaLocationMap(
        getAttribute(doc.getFirstChild(), W3C_XML_SCHEMA_INSTANCE_ATTRIBUTE_SCHEMA_LOCATION, W3C_XML_SCHEMA_INSTANCE_NS_URI));

    final LinkedHashMap<String, CacheElement> elementMap = new LinkedHashMap<String, CacheElement>();

    buildElementMapCacheType(elementMap, resolveSchema(schemaLocationMap, CacheXml.NAMESPACE));

    // if we are ever concerned with the order of extensions or children process them here.

    return elementMap;
  }