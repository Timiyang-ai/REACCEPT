@Test
    public void testDetermineCPE_full() throws Exception {
        CPEAnalyzer cpeAnalyzer = new CPEAnalyzer();
        try (Engine e = new Engine(getSettings())) {
            //update needs to be performed so that xtream can be tested
            e.doUpdates(true);
            cpeAnalyzer.initialize(getSettings());
            cpeAnalyzer.prepare(e);
            FileNameAnalyzer fnAnalyzer = new FileNameAnalyzer();
            fnAnalyzer.initialize(getSettings());
            fnAnalyzer.prepare(e);
            JarAnalyzer jarAnalyzer = new JarAnalyzer();
            jarAnalyzer.initialize(getSettings());
            jarAnalyzer.accept(new File("test.jar"));//trick analyzer into "thinking it is active"
            jarAnalyzer.prepare(e);
            HintAnalyzer hAnalyzer = new HintAnalyzer();
            hAnalyzer.initialize(getSettings());
            hAnalyzer.prepare(e);
            FalsePositiveAnalyzer fp = new FalsePositiveAnalyzer();
            fp.initialize(getSettings());
            fp.prepare(e);

            CpeSuppressionAnalyzer cpeSuppression = new CpeSuppressionAnalyzer();
            cpeSuppression.initialize(getSettings());
            cpeSuppression.prepare(e);

            callDetermineCPE_full("hazelcast-2.5.jar", null, cpeAnalyzer, fnAnalyzer, jarAnalyzer, hAnalyzer, fp, cpeSuppression);
            callDetermineCPE_full("spring-context-support-2.5.5.jar", "cpe:/a:springsource:spring_framework:2.5.5", cpeAnalyzer, fnAnalyzer, jarAnalyzer, hAnalyzer, fp, cpeSuppression);
            callDetermineCPE_full("spring-core-3.0.0.RELEASE.jar", "cpe:/a:vmware:springsource_spring_framework:3.0.0", cpeAnalyzer, fnAnalyzer, jarAnalyzer, hAnalyzer, fp, cpeSuppression);
            callDetermineCPE_full("jaxb-xercesImpl-1.5.jar", null, cpeAnalyzer, fnAnalyzer, jarAnalyzer, hAnalyzer, fp, cpeSuppression);
            callDetermineCPE_full("ehcache-core-2.2.0.jar", null, cpeAnalyzer, fnAnalyzer, jarAnalyzer, hAnalyzer, fp, cpeSuppression);
            callDetermineCPE_full("org.mortbay.jetty.jar", "cpe:/a:mortbay_jetty:jetty:4.2.27", cpeAnalyzer, fnAnalyzer, jarAnalyzer, hAnalyzer, fp, cpeSuppression);
            //callDetermineCPE_full("xstream-1.4.8.jar", "cpe:/a:x-stream:xstream:1.4.8", cpeAnalyzer, fnAnalyzer, jarAnalyzer, hAnalyzer, fp, cpeSuppression);
            callDetermineCPE_full("xstream-1.4.8.jar", "cpe:/a:xstream_project:xstream:1.4.8", cpeAnalyzer, fnAnalyzer, jarAnalyzer, hAnalyzer, fp, cpeSuppression);
        } finally {
            cpeAnalyzer.close();
        }
    }