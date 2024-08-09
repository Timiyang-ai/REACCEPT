public static NbTestSuite suite() {
        NbModuleSuite.Configuration conf = NbModuleSuite.createConfiguration(
            ProfilerValidation.class
        ).clusters("profiler").enableModules(".*").honorAutoloadEager(true)
        .failOnException(Level.INFO)
        .failOnMessage(Level.SEVERE);
        
        conf.addTest("testProfilerCalibration");
        conf.addTest("testProfilerProperties");
        conf.addTest("testProfilerMenus");
        conf.addTest("testProfiler");
        conf.addTest("issue144699Hack");
        
        NbTestSuite suite = new NbTestSuite();
        suite.addTest(NbModuleSuite.create(conf));
        
        return suite;
    }