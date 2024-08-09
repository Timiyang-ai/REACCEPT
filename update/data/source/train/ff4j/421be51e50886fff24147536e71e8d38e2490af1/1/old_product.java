@ManagedOperation(description = "Enable feature")
    public void enableFeature(String featureID) {
        getFf4j().enable(featureID);
    }