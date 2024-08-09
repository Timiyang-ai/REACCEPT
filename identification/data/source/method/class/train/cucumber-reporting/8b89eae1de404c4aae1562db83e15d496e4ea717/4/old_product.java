public void limitItems(int limit) {
        buildNumbers = copyLastElements(buildNumbers, limit);
        failedFeatures = copyLastElements(failedFeatures, limit);
        totalFeatures = copyLastElements(totalFeatures, limit);
        failedScenarios = copyLastElements(failedScenarios, limit);
        totalScenarios = copyLastElements(totalScenarios, limit);
        failedSteps = copyLastElements(failedSteps, limit);
        totalSteps = copyLastElements(totalSteps, limit);
    }