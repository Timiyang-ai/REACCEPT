@Test
    public void testDetermineCPE() throws Exception {
        //File file = new File(this.getClass().getClassLoader().getResource("struts2-core-2.1.2.jar").getPath());
        File file = BaseTest.getResourceAsFile(this, "struts2-core-2.1.2.jar");
        //File file = new File(this.getClass().getClassLoader().getResource("axis2-adb-1.4.1.jar").getPath());
        Dependency struts = new Dependency(file);

        FileNameAnalyzer fnAnalyzer = new FileNameAnalyzer();
        fnAnalyzer.analyze(struts, null);

        HintAnalyzer hintAnalyzer = new HintAnalyzer();
        JarAnalyzer jarAnalyzer = new JarAnalyzer();
        jarAnalyzer.accept(new File("test.jar"));//trick analyzer into "thinking it is active"

        jarAnalyzer.analyze(struts, null);
        hintAnalyzer.analyze(struts, null);
        //File fileCommonValidator = new File(this.getClass().getClassLoader().getResource("commons-validator-1.4.0.jar").getPath());
        File fileCommonValidator = BaseTest.getResourceAsFile(this, "commons-validator-1.4.0.jar");
        Dependency commonValidator = new Dependency(fileCommonValidator);
        jarAnalyzer.analyze(commonValidator, null);
        hintAnalyzer.analyze(commonValidator, null);

        //File fileSpring = new File(this.getClass().getClassLoader().getResource("spring-core-2.5.5.jar").getPath());
        File fileSpring = BaseTest.getResourceAsFile(this, "spring-core-2.5.5.jar");
        Dependency spring = new Dependency(fileSpring);
        jarAnalyzer.analyze(spring, null);
        hintAnalyzer.analyze(spring, null);

        //File fileSpring3 = new File(this.getClass().getClassLoader().getResource("spring-core-3.0.0.RELEASE.jar").getPath());
        File fileSpring3 = BaseTest.getResourceAsFile(this, "spring-core-3.0.0.RELEASE.jar");
        Dependency spring3 = new Dependency(fileSpring3);
        jarAnalyzer.analyze(spring3, null);
        hintAnalyzer.analyze(spring3, null);

        CPEAnalyzer instance = new CPEAnalyzer();
        instance.open();
        instance.determineCPE(commonValidator);
        instance.determineCPE(struts);
        instance.determineCPE(spring);
        instance.determineCPE(spring3);
        instance.close();

        String expResult = "cpe:/a:apache:struts:2.1.2";
        Identifier expIdentifier = new Identifier("cpe", expResult, expResult);
        String expResultSpring = "cpe:/a:springsource:spring_framework:2.5.5";
        String expResultSpring3 = "cpe:/a:vmware:springsource_spring_framework:3.0.0";

        for (Identifier i : commonValidator.getIdentifiers()) {
            Assert.assertFalse("Apache Common Validator - found a CPE identifier?", "cpe".equals(i.getType()));
        }

        Assert.assertTrue("Incorrect match size - struts", struts.getIdentifiers().size() >= 1);
        Assert.assertTrue("Incorrect match - struts", struts.getIdentifiers().contains(expIdentifier));
        Assert.assertTrue("Incorrect match size - spring3 - " + spring3.getIdentifiers().size(), spring3.getIdentifiers().size() >= 1);

        //the following two only work if the HintAnalyzer is used.
        //Assert.assertTrue("Incorrect match size - spring", spring.getIdentifiers().size() == 1);
        //Assert.assertTrue("Incorrect match - spring", spring.getIdentifiers().get(0).getValue().equals(expResultSpring));
        jarAnalyzer.close();
    }