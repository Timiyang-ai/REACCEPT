@Test
    public void testSearchCPE() throws Exception {
        System.out.println("searchCPE");
        String vendor = "apache software foundation";
        String product = "struts 2 core";
        String version = "2.1.2";
        String expResult = "cpe:/a:apache:struts:2.1.2";

        CPEAnalyzer instance = new CPEAnalyzer();
        instance.open();

        //TODO - yeah, not a very good test as the results are the same with or without weighting...
        Set<String> productWeightings = new HashSet<String>(1);
        productWeightings.add("struts2");

        Set<String> vendorWeightings = new HashSet<String>(1);
        vendorWeightings.add("apache");

        List<Entry> result = instance.searchCPE(vendor, product, version, productWeightings, vendorWeightings);
        assertEquals(expResult, result.get(0).getName());


        instance.close();
    }