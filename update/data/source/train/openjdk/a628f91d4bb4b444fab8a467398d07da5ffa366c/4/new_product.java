@DataProvider(name = "supportLSResourceResolver")
    public Object[][] supportLSResourceResolver() throws Exception {
        URI catalogFile = getClass().getResource("CatalogSupport.xml").toURI();
        URI catalogFileUri = getClass().getResource("CatalogSupport_uri.xml").toURI();

        /*
         * XMLSchema.xsd has a reference to XMLSchema.dtd which in turn refers to
         * datatypes.dtd
        */
        return new Object[][]{
            {catalogFile, new StreamSource(new StringReader(xsd_xmlSchema))},
            {catalogFile, new StreamSource(new StringReader(xsd_xmlSchema_import))},
            {catalogFile, new StreamSource(new StringReader(xsd_include_company))},
            {catalogFileUri, new StreamSource(new StringReader(xsd_xmlSchema))},
            {catalogFileUri, new StreamSource(new StringReader(xsd_xmlSchema_import))},
            {catalogFileUri, new StreamSource(new StringReader(xsd_include_company))},
         };
    }