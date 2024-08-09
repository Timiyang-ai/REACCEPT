@Test
    public void testDetermineCPE() throws Exception {
        //File file = new File(this.getClass().getClassLoader().getResource("struts2-core-2.1.2.jar").getPath());
        File file = BaseTest.getResourceAsFile(this, "struts2-core-2.1.2.jar");
        //File file = new File(this.getClass().getClassLoader().getResource("axis2-adb-1.4.1.jar").getPath());
        Dependency struts = new Dependency(file);

        FileNameAnalyzer fnAnalyzer = new FileNameAnalyzer();
        fnAnalyzer.analyze(struts, null);

        HintAnalyzer hintAnalyzer = new HintAnalyzer();
        hintAnalyzer.initializeSettings(getSettings());
        hintAnalyzer.initialize(null);
        JarAnalyzer jarAnalyzer = new JarAnalyzer();
        jarAnalyzer.initializeSettings(getSettings());
        jarAnalyzer.accept(new File("test.jar"));//trick analyzer into "thinking it is active"
        jarAnalyzer.initialize(null);

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
        Engine engine = new Engine(getSettings());
        engine.openDatabase();
        instance.initializeSettings(getSettings());
        instance.initialize(engine);
        instance.determineCPE(commonValidator);
        instance.determineCPE(struts);
        instance.determineCPE(spring);
        instance.determineCPE(spring3);
        instance.close();

        String expResult = "cpe:/a:apache:struts:2.1.2";

        for (Identifier i : commonValidator.getIdentifiers()) {
            assertFalse("Apache Common Validator - found a CPE identifier?", "cpe".equals(i.getType()));
        }

        assertTrue("Incorrect match size - struts", struts.getIdentifiers().size() >= 1);
        boolean found = false;
        for (Identifier i : struts.getIdentifiers()) {
            if (expResult.equals(i.getValue())) {
                found = true;
                break;
            }
        }
        assertTrue("Incorrect match - struts", found);
        assertTrue("Incorrect match size - spring3 - " + spring3.getIdentifiers().size(), spring3.getIdentifiers().size() >= 1);

        jarAnalyzer.close();
        engine.close();
    }