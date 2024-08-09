@Override
    public void adjustTLSContext(ExtendedMasterSecretExtensionMessage message) {
        context.setIsExtendedMasterSecretExtension(true);
    }