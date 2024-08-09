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
        
        
        file = new File(this.getClass().getClassLoader().getResource("org.mortbay.jetty.jar").getPath());
        
        result = instance.insepct(file);
        boolean found = false;
        for (Evidence e : result.getTitleEvidence()) {
            if (e.getName().equals("package-title") && e.getValue().equals("org.mortbay.http")) {
                found = true;
                break;
            }
        }
        assertTrue("package-title of org.mortbay.http not found in org.mortbay.jetty.jar", found);
        
        found = false;
        for (Evidence e : result.getVendorEvidence()) {
            if (e.getName().equals("implementation-url") && e.getValue().equals("http://jetty.mortbay.org")) {
                found = true;
                break;
            }
        }
        assertTrue("implementation-url of http://jetty.mortbay.org not found in org.mortbay.jetty.jar", found);
        
        found = false;
        for (Evidence e : result.getVersionEvidence()) {
            if (e.getName().equals("Implementation-Version") && e.getValue().equals("4.2.27")) {
                found = true;
                break;
            }
        }
        assertTrue("implementation-version of 4.2.27 not found in org.mortbay.jetty.jar", found);
        
        file = new File(this.getClass().getClassLoader().getResource("org.mortbay.jmx.jar").getPath());
        result = instance.insepct(file);
        assertEquals("org.mortbar,jmx.jar has version evidence?",result.getVersionEvidence().size(),0);     
    }