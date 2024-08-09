@Test
    public void testAnalyze() throws Exception {
        System.out.println("analyze");
        File file = new File(this.getClass().getClassLoader().getResource("struts2-core-2.1.2.jar").getPath());
        Dependency result = new Dependency(file);
        FileNameAnalyzer instance = new FileNameAnalyzer();
        instance.analyze(result, null);
        assertTrue(result.getVendorEvidence().toString().toLowerCase().contains("struts"));
    }