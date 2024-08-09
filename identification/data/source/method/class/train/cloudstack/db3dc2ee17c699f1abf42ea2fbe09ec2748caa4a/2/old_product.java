@Override
    public boolean isReady(PhysicalNetworkServiceProvider provider) {
        PhysicalNetwork physicalNetwork = _physicalNetworkDao.findById(provider.getPhysicalNetworkId());
        assert(physicalNetwork!=null);
        if(physicalNetwork != null){
            if(fetchSspClients(physicalNetwork.getId(), physicalNetwork.getDataCenterId(), false).size() > 0){
                return true;
            }
            s_logger.warn("Ssp api endpoint not found. "+physicalNetwork.toString());
        }else{
            s_logger.warn("PhysicalNetwork is NULL.");
        }
        return false;
    }