@DataProvider(name = "supportLSResourceResolver1")
    public Object[][] supportLSResourceResolver1() {
        String catalogFile = getClass().getResource("CatalogSupport.xml").getFile();
        String catalogFileUri = getClass().getResource("CatalogSupport_uri.xml").getFile();

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