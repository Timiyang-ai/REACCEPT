@Test(dataProvider = "catalog")
    public void testCatalogResolver(String test, String expected, String catalogFile, String xml, SAXParser saxParser) {
        String catalog = null;
        if (catalogFile != null) {
            catalog = getClass().getResource(catalogFile).getFile();
        }
        String url = getClass().getResource(xml).getFile();
        try {
            CatalogResolver cr = CatalogManager.catalogResolver(null, catalog);
            XMLReader reader = saxParser.getXMLReader();
            reader.setEntityResolver(cr);
            MyHandler handler = new MyHandler(saxParser);
            reader.setContentHandler(handler);
            reader.parse(url);
            System.out.println(test + ": expected [" + expected + "] <> actual [" + handler.getResult() + "]");
            Assert.assertEquals(handler.getResult(), expected);
        } catch (SAXException | IOException e) {
            Assert.fail(e.getMessage());
        }
    }