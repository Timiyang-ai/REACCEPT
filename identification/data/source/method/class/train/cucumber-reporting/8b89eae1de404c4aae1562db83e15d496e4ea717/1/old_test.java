    @Test
    public void addBuild_AddsNewResultAtTheLastPosition() {

        // given
        Trends trends = new Trends();
        // make sure that there is some data added already
        Reportable result = ReportableBuilder.buildSample();
        trends.addBuild("buildName", result);

        final String buildNumber = "this is the build!";

        // when
        trends.addBuild(buildNumber, result);

        // then
        assertThat(trends.getBuildNumbers()).hasSize(2).endsWith(buildNumber);

        assertThat(trends.getPassedFeatures()).hasSize(2).endsWith(result.getPassedFeatures());
        assertThat(trends.getFailedFeatures()).hasSize(2).endsWith(result.getFailedFeatures());
        assertThat(trends.getTotalFeatures()).hasSize(2).endsWith(result.getFeatures());

        assertThat(trends.getPassedScenarios()).hasSize(2).endsWith(result.getPassedScenarios());
        assertThat(trends.getFailedScenarios()).hasSize(2).endsWith(result.getFailedScenarios());
        assertThat(trends.getTotalScenarios()).hasSize(2).endsWith(result.getScenarios());

        assertThat(trends.getPassedSteps()).hasSize(2).endsWith(result.getPassedSteps());
        assertThat(trends.getFailedSteps()).hasSize(2).endsWith(result.getFailedSteps());
        assertThat(trends.getSkippedSteps()).hasSize(2).endsWith(result.getSkippedSteps());
        assertThat(trends.getPendingSteps()).hasSize(2).endsWith(result.getPendingSteps());
        assertThat(trends.getUndefinedSteps()).hasSize(2).endsWith(result.getUndefinedSteps());
        assertThat(trends.getTotalSteps()).hasSize(2).endsWith(result.getSteps());

        assertThat(trends.getDurations()).hasSize(2).endsWith(3206126182390L);
    }