@Test(dataProvider = "supportXMLResolver")
    public void supportXMLResolver(String catalogFile, String xml, String expected) throws Exception {
        String xmlSource = getClass().getResource(xml).getFile();

        CatalogResolver cr = CatalogManager.catalogResolver(CatalogFeatures.defaults(), catalogFile);

        XMLInputFactory xifactory = XMLInputFactory.newInstance();
        xifactory.setProperty(XMLInputFactory.IS_COALESCING, true);
        xifactory.setProperty(XMLInputFactory.RESOLVER, cr);
        File file = new File(xmlSource);
        String systemId = file.toURI().toString();
        InputStream entityxml = new FileInputStream(file);
        XMLStreamReader streamReader = xifactory.createXMLStreamReader(systemId, entityxml);
        String result = null;
        while (streamReader.hasNext()) {
            int eventType = streamReader.next();
            if (eventType == XMLStreamConstants.START_ELEMENT) {
                eventType = streamReader.next();
                if (eventType == XMLStreamConstants.CHARACTERS) {
                    result = streamReader.getText();
                }
            }
        }
        System.out.println(": expected [" + expected + "] <> actual [" + result.trim() + "]");

        Assert.assertEquals(result.trim(), expected);
    }