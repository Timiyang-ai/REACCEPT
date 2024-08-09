public void addBuild(String buildNumber, Reportable reportable) {

        buildNumbers = (String[]) ArrayUtils.add(buildNumbers, buildNumber);

        passedFeatures = ArrayUtils.add(passedFeatures, reportable.getPassedFeatures());
        failedFeatures = ArrayUtils.add(failedFeatures, reportable.getFailedFeatures());
        totalFeatures = ArrayUtils.add(totalFeatures, reportable.getFeatures());

        passedScenarios = ArrayUtils.add(passedScenarios, reportable.getPassedScenarios());
        failedScenarios = ArrayUtils.add(failedScenarios, reportable.getFailedScenarios());
        totalScenarios = ArrayUtils.add(totalScenarios, reportable.getScenarios());

        passedSteps = ArrayUtils.add(passedSteps, reportable.getPassedSteps());
        failedSteps = ArrayUtils.add(failedSteps, reportable.getFailedSteps());
        skippedSteps = ArrayUtils.add(skippedSteps, reportable.getSkippedSteps());
        pendingSteps = ArrayUtils.add(pendingSteps, reportable.getPendingSteps());
        undefinedSteps = ArrayUtils.add(undefinedSteps, reportable.getUndefinedSteps());
        totalSteps = ArrayUtils.add(totalSteps, reportable.getSteps());

        durations = ArrayUtils.add(durations, reportable.getDuration());

        // this should be removed later but for now correct features and save valid data
        applyPatchForFeatures();
        if (pendingSteps.length < buildNumbers.length) {
            fillMissingSteps();
        }
        if (durations.length < buildNumbers.length) {
            fillMissingDurations();
        }
    }