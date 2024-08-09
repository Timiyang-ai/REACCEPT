    @Test
    public void resultToPlainText() throws Exception {
        final List<HealthCheckExecutionResult> successResults = new ArrayList<>();
        successResults.add(successExecutionResult);
        final String actual = healthCheckStatusEmailer.resultToPlainText("HC Test", successResults);

        Matcher titleMatcher = Pattern.compile("^HC Test$", Pattern.MULTILINE).matcher(actual);
        Matcher entryMatcher = Pattern.compile("^\\[ OK \\]\\s+hc success$", Pattern.MULTILINE).matcher(actual);
        Matcher negativeMatcher = Pattern.compile("^\\[ CRTICAL \\]\\s+hc failure", Pattern.MULTILINE).matcher(actual);

        assertTrue(titleMatcher.find());
        assertTrue(entryMatcher.find());
        assertFalse(negativeMatcher.find());
    }