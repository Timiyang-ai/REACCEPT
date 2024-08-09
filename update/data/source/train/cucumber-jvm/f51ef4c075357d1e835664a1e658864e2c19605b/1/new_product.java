public void runCukes() {
        System.err.println("WARNING: The TestNGCucumberRunner.runCukes() is deprecated. Please create a runner class by subclassing AbstractTestNGCucumberTest.");
        for (CucumberFeature cucumberFeature : getFeatures()) {
            reporter.uri(cucumberFeature.getPath());
            runtime.runFeature(cucumberFeature);
        }
        finish();
        if (!resultListener.isPassed()) {
            throw new CucumberException(resultListener.getFirstError());
        }
    }