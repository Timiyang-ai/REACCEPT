@DataProvider(name = "supportXMLResolver")
    public Object[][] supportXMLResolver() {
        String catalogFile = getClass().getResource("catalog.xml").getFile();
        String catalogFileUri = getClass().getResource("catalog_uri.xml").getFile();

        return new Object[][]{
            {catalogFile, "system.xml", "Test system entry"},
            {catalogFile, "rewritesystem.xml", "Test rewritesystem entry"},
            {catalogFile, "rewritesystem1.xml", "Test rewritesystem entry"},
            {catalogFile, "systemsuffix.xml", "Test systemsuffix entry"},
            {catalogFile, "delegatesystem.xml", "Test delegatesystem entry"},
            {catalogFile, "public.xml", "Test public entry"},
            {catalogFile, "delegatepublic.xml", "Test delegatepublic entry"},
            // using uri entries
            {catalogFileUri, "system.xml", "Test system entry"},
            {catalogFileUri, "rewritesystem.xml", "Test rewritesystem entry"},
            {catalogFileUri, "rewritesystem1.xml", "Test rewritesystem entry"},
            {catalogFileUri, "systemsuffix.xml", "Test systemsuffix entry"},
            {catalogFileUri, "delegateuri.xml", "Test delegateuri entry"},
            {catalogFileUri, "public.xml", "Test public entry"},
         };
    }