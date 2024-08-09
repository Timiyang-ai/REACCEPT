@ManagedOperation(description = "Disable feature")
    public void disableFeature(String featureID) {
        getFf4j().disable(featureID);
    }