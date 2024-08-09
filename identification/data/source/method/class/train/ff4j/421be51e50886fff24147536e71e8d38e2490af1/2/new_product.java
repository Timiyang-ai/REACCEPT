@ManagedOperation(description = "Disable feature from its identifier")
    public void disableFeature(String featureID) {
        getFf4j().disable(featureID);
    }