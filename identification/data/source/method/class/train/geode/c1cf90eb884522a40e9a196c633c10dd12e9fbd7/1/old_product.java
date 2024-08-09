static Document createAndUpgradeDocumentFromXml(final String xmlContent) throws SAXException, ParserConfigurationException, IOException, XPathExpressionException {
    Document doc = XmlUtils.createDocumentFromXml(xmlContent);
    if (!CacheXml.VERSION_LATEST.equals(XmlUtils.getAttribute(doc.getDocumentElement(), CacheXml.VERSION, CacheXml.NAMESPACE))) {
      doc = XmlUtils.upgradeSchema(doc, CacheXml.NAMESPACE, CacheXml.LATEST_SCHEMA_LOCATION, CacheXml.VERSION_LATEST);
    }
    return doc;
  }