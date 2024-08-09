    @Test
    public void limitItems_ReducesNumberOfItems() {

        // given
        final int limit = 1;
        final String buildName = "a, e -> c";
        Trends trends = new Trends();
        Reportable result = ReportableBuilder.buildSample();
        // make sure that there is some data added already
        for (int i = 0; i < limit + 3; i++) {
            trends.addBuild(buildName, result);
        }

        // when
        trends.limitItems(limit);

        // then
        assertThat(trends.getBuildNumbers()).hasSize(limit).containsExactly(buildName);

        assertThat(trends.getPassedFeatures()).hasSize(limit).containsExactly(result.getPassedFeatures());
        assertThat(trends.getFailedFeatures()).hasSize(limit).containsExactly(result.getFailedFeatures());
        assertThat(trends.getTotalFeatures()).hasSize(limit).containsExactly(result.getFeatures());

        assertThat(trends.getPassedScenarios()).hasSize(limit).containsExactly(result.getPassedScenarios());
        assertThat(trends.getFailedScenarios()).hasSize(limit).containsExactly(result.getFailedScenarios());
        assertThat(trends.getTotalScenarios()).hasSize(limit).containsExactly(result.getScenarios());

        assertThat(trends.getPassedSteps()).hasSize(limit).containsExactly(result.getPassedSteps());
        assertThat(trends.getFailedSteps()).hasSize(limit).containsExactly(result.getFailedSteps());
        assertThat(trends.getPendingSteps()).hasSize(limit).containsExactly(result.getPendingSteps());
        assertThat(trends.getSkippedSteps()).hasSize(limit).containsExactly(result.getSkippedSteps());
        assertThat(trends.getUndefinedSteps()).hasSize(limit).containsExactly(result.getUndefinedSteps());
        assertThat(trends.getTotalSteps()).hasSize(limit).containsExactly(result.getSteps());

        assertThat(trends.getDurations()).hasSize(limit).containsExactly(result.getDuration());
    }