@Test(dataProvider = "supportURIResolver")
    public void supportURIResolver(URI catalogFile, Source xsl, Source xml, String expected) throws Exception {

        CatalogResolver cr = CatalogManager.catalogResolver(CatalogFeatures.defaults(), catalogFile);

            TransformerFactory factory = TransformerFactory.newInstance();
            factory.setURIResolver(cr);
            Transformer transformer = factory.newTransformer(xsl);
            StringWriter out = new StringWriter();
            transformer.transform(xml, new StreamResult(out));
            if (expected != null) {
                Assert.assertTrue(out.toString().contains(expected), "supportURIResolver");
            }
    }