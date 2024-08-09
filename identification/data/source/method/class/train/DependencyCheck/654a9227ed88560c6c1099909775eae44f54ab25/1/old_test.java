@Test
    public void testInsepct() throws Exception {
        System.out.println("insepct");
        File file = new File(this.getClass().getClassLoader().getResource("struts2-core-2.1.2.jar").getPath());
        JarAnalyzer instance = new JarAnalyzer();
        Dependency result = instance.insepct(file);
        assertEquals("C30B57142E1CCBC1EFD5CD15F307358F", result.getMd5sum());
        assertEquals("89CE9E36AA9A9E03F1450936D2F4F8DD0F961F8B", result.getSha1sum());
        assertTrue(result.getVendorEvidence().toString().toLowerCase().contains("apache"));
        assertTrue(result.getVendorEvidence().getWeighting().contains("apache"));
    }