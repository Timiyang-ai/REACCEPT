@Test(dataProvider = "supportLSResourceResolver")
    public void supportLSResourceResolver(URI catalogFile, Source schemaSource) throws SAXException {

        CatalogResolver cr = CatalogManager.catalogResolver(CatalogFeatures.defaults(), catalogFile);

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        factory.setResourceResolver(cr);
        Schema schema = factory.newSchema(schemaSource);

    }