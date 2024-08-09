@Test(dataProvider = "supportLSResourceResolver1")
    public void supportLSResourceResolver1(URI catalogFile, Source source) throws Exception {

        CatalogResolver cr = CatalogManager.catalogResolver(CatalogFeatures.defaults(), catalogFile);

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Validator validator = factory.newSchema().newValidator();
        validator.setResourceResolver(cr);
        validator.validate(source);
    }