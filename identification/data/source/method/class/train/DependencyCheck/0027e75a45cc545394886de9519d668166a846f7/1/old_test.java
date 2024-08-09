@Test
    public void testDetermineCPE() throws Exception {
        System.out.println("determineCPE");
        File file = new File(this.getClass().getClassLoader().getResource("struts2-core-2.1.2.jar").getPath());
        JarAnalyzer jarAnalyzer = new JarAnalyzer();
        Dependency depends = new Dependency(file);
        jarAnalyzer.analyze(depends);
        
        CPEAnalyzer instance = new CPEAnalyzer();
        instance.open();
        String expResult = "cpe:/a:apache:struts:2.1.2";
        instance.determineCPE(depends);
        instance.close();
        assertTrue("Incorrect match", depends.getIdentifiers().size() == 1);
        assertTrue("Incorrect match", depends.getIdentifiers().get(0).getValue().equals(expResult));
    }