    @Test
    public void setHealthy() {
        final SettableHealthChecker checker = new SettableHealthChecker();
        checker.setHealthy(true);
        assertTrue(checker.isHealthy());
    }