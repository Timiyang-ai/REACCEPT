@DataProvider(name = "supportURIResolver")
    public Object[][] supportURIResolver() {
        String catalogFile = getClass().getResource("CatalogSupport.xml").getFile();
        String catalogFileUri = getClass().getResource("CatalogSupport_uri.xml").getFile();
        SAXSource xslSource = new SAXSource(new InputSource(new File(xsl_doc).toURI().toASCIIString()));

        /*
         * val_test.xml has a reference to system.dtd and val_test.xsd
        */
        SAXSource ss = new SAXSource(new InputSource(xml_val_test));
        ss.setSystemId(xml_val_test_id);

        return new Object[][]{
            {catalogFile, new SAXSource(new InputSource(new File(xsl_doc).toURI().toASCIIString())),
                new StreamSource(new File(xml_doc)), "Resolved by a catalog"},
            {catalogFileUri, new SAXSource(new InputSource(new StringReader(xsl_include))),
                new StreamSource(new StringReader(xml_xsl)), null},
         };
    }