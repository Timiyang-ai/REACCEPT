@Test
    public void testBuildSearch() throws IOException, CorruptIndexException, ParseException {
        System.out.println("buildSearch");
        Set<String> productWeightings = new HashSet<String>(1);
        productWeightings.add("struts2");

        Set<String> vendorWeightings = new HashSet<String>(1);
        vendorWeightings.add("apache");

        String vendor = "apache software foundation";
        String product = "struts 2 core";
        String version = "2.1.2";
        CPEAnalyzer instance = new CPEAnalyzer();

        String queryText = instance.buildSearch(vendor, product, version, null, null);
        String expResult = " product:( struts 2 core )  AND  vendor:( apache software foundation )  AND version:(2.1.2^0.7 )";
        Assert.assertTrue(expResult.equals(queryText));

        queryText = instance.buildSearch(vendor, product, version, null, productWeightings);
        expResult = " product:(  struts^5 struts2^5 2 core )  AND  vendor:( apache software foundation )  AND version:(2.1.2^0.2 )";
        Assert.assertTrue(expResult.equals(queryText));

        queryText = instance.buildSearch(vendor, product, version, vendorWeightings, null);
        expResult = " product:( struts 2 core )  AND  vendor:(  apache^5 software foundation )  AND version:(2.1.2^0.2 )";
        Assert.assertTrue(expResult.equals(queryText));

        queryText = instance.buildSearch(vendor, product, version, vendorWeightings, productWeightings);
        expResult = " product:(  struts^5 struts2^5 2 core )  AND  vendor:(  apache^5 software foundation )  AND version:(2.1.2^0.2 )";
        Assert.assertTrue(expResult.equals(queryText));
    }