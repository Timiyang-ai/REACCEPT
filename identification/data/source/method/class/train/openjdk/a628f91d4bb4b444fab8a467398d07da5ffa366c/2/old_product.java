@DataProvider(name = "supportLSResourceResolver")
    public Object[][] supportLSResourceResolver() {
        String catalogFile = getClass().getResource("CatalogSupport.xml").getFile();
        String catalogFileUri = getClass().getResource("CatalogSupport_uri.xml").getFile();

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