@Test
    public void testDetermineCPE_full() throws Exception {
        CPEAnalyzer instance = new CPEAnalyzer();
        instance.open();
        FileNameAnalyzer fnAnalyzer = new FileNameAnalyzer();
        JarAnalyzer jarAnalyzer = new JarAnalyzer();
        HintAnalyzer hAnalyzer = new HintAnalyzer();
        FalsePositiveAnalyzer fp = new FalsePositiveAnalyzer();

        try {
            callDetermineCPE_full("hazelcast-2.5.jar", null, instance, fnAnalyzer, jarAnalyzer, hAnalyzer, fp);
            callDetermineCPE_full("spring-context-support-2.5.5.jar", "cpe:/a:vmware:springsource_spring_framework:2.5.5", instance, fnAnalyzer, jarAnalyzer, hAnalyzer, fp);
            callDetermineCPE_full("spring-core-3.0.0.RELEASE.jar", "cpe:/a:vmware:springsource_spring_framework:3.0.0", instance, fnAnalyzer, jarAnalyzer, hAnalyzer, fp);
            callDetermineCPE_full("org.mortbay.jetty.jar", "cpe:/a:mortbay_jetty:jetty:4.2", instance, fnAnalyzer, jarAnalyzer, hAnalyzer, fp);
            callDetermineCPE_full("jaxb-xercesImpl-1.5.jar", null, instance, fnAnalyzer, jarAnalyzer, hAnalyzer, fp);
            callDetermineCPE_full("ehcache-core-2.2.0.jar", null, instance, fnAnalyzer, jarAnalyzer, hAnalyzer, fp);
        } finally {
            instance.close();
        }
    }