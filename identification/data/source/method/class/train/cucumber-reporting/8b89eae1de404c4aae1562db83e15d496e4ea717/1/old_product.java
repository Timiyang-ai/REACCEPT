public void addBuild(String buildNumber, int failedFeature, int totalFeature, int failedScenario,
                         int totalScenario, int failedStep, int totalStep) {

        buildNumbers = (String[]) ArrayUtils.add(buildNumbers, buildNumber);
        failedFeatures = ArrayUtils.add(failedFeatures, failedFeature);
        totalFeatures = ArrayUtils.add(totalFeatures, totalFeature);
        failedScenarios = ArrayUtils.add(failedScenarios, failedScenario);
        totalScenarios = ArrayUtils.add(totalScenarios, totalScenario);
        failedSteps = ArrayUtils.add(failedSteps, failedStep);
        totalSteps = ArrayUtils.add(totalSteps, totalStep);
    }