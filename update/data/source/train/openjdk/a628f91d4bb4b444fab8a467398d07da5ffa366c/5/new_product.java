@DataProvider(name = "supportLSResourceResolver1")
    public Object[][] supportLSResourceResolver1() throws Exception {
        URI catalogFile = getClass().getResource("CatalogSupport.xml").toURI();
        URI catalogFileUri = getClass().getResource("CatalogSupport_uri.xml").toURI();

        /*
         * val_test.xml has a reference to system.dtd and val_test.xsd
        */
        SAXSource ss = new SAXSource(new InputSource(xml_val_test));
        ss.setSystemId(xml_val_test_id);

        return new Object[][]{
            {catalogFile, ss},
            {catalogFileUri, ss},
         };
    }