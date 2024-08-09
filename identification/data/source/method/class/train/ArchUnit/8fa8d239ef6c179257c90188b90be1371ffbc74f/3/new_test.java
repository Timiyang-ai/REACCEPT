    @Test
    @UseDataProvider("implement_satisfied_rules")
    public void implement_satisfied(ArchRule rule, Class<?> satisfied) {
        EvaluationResult result = rule.evaluate(importHierarchies(satisfied));

        assertThat(singleLineFailureReportOf(result))
                .doesNotMatch(String.format(".*%s.* implement.*", quote(satisfied.getName())));
    }