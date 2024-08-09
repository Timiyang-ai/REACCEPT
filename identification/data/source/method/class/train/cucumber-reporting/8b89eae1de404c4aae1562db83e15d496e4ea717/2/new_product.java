public void limitItems(int limit) {
        buildNumbers = copyLastElements(buildNumbers, limit);

        passedFeatures = copyLastElements(passedFeatures, limit);
        failedFeatures = copyLastElements(failedFeatures, limit);
        totalFeatures = copyLastElements(totalFeatures, limit);

        passedScenarios = copyLastElements(passedScenarios, limit);
        failedScenarios = copyLastElements(failedScenarios, limit);
        totalScenarios = copyLastElements(totalScenarios, limit);

        passedSteps = copyLastElements(passedSteps, limit);
        failedSteps = copyLastElements(failedSteps, limit);
        skippedSteps = copyLastElements(skippedSteps, limit);
        pendingSteps = copyLastElements(pendingSteps, limit);
        undefinedSteps = copyLastElements(undefinedSteps, limit);
        totalSteps = copyLastElements(totalSteps, limit);

        durations = copyLastElements(durations, limit);
    }