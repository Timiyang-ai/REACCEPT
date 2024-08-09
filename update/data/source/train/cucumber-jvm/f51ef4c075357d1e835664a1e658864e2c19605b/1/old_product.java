public void runCukes() {
        for (CucumberFeature cucumberFeature : getFeatures()) {
            reporter.uri(cucumberFeature.getPath());
            runtime.runFeature(cucumberFeature);
        }
        finish();
        if (!resultListener.isPassed()) {
            throw new CucumberException(resultListener.getFirstError());
        }
    }