@Override
    public boolean canHandle(PhysicalNetwork physicalNetwork) {
        if (physicalNetwork != null) {
            if (fetchSspClients(physicalNetwork.getId(), physicalNetwork.getDataCenterId(), true).size() > 0) {
                return true;
            }
            logger.warn("enabled Ssp api endpoint not found. " + physicalNetwork.toString());
        } else {
            logger.warn("PhysicalNetwork is NULL.");
        }
        return false;
    }