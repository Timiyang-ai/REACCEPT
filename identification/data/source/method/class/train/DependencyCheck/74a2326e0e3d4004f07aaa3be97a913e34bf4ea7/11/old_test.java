@Test
    public void testGetAnalyzers_SpecificPhases() throws Exception {
        AnalyzerService instance = new AnalyzerService(Thread.currentThread().getContextClassLoader());
        List<Analyzer> result = instance.getAnalyzers(INITIAL, FINAL);

        for (Analyzer a : result) {
            if (a.getAnalysisPhase() != INITIAL && a.getAnalysisPhase() != FINAL) {
                fail("Only expecting analyzers for phases " + INITIAL + " and " + FINAL);
            }
        }
    }