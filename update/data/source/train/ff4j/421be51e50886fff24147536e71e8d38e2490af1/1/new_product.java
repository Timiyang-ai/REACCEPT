@ManagedOperation(description = "Enable feature from its identifier")
    public void enableFeature(String featureID) {
        getFf4j().enable(featureID);
    }