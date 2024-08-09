@Override
    public void adjustTLSContext(ExtendedMasterSecretExtensionMessage message) {
        if (context.getTalkingConnectionEnd() == ConnectionEnd.SERVER) {
            context.setIsExtendedMasterSecretExtension(true);
        }
    }