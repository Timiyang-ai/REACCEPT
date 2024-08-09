protected StrategyPriority internalCanHandle(Map<VolumeInfo, DataStore> volumeMap, Host srcHost, Host destHost) {
        Set<VolumeInfo> volumeInfoSet = volumeMap.keySet();

        for (VolumeInfo volumeInfo : volumeInfoSet) {
            StoragePoolVO storagePoolVO = _storagePoolDao.findById(volumeInfo.getPoolId());

            if (storagePoolVO.isManaged()) {
                return StrategyPriority.HIGHEST;
            }
        }

        Collection<DataStore> dataStores = volumeMap.values();

        for (DataStore dataStore : dataStores) {
            StoragePoolVO storagePoolVO = _storagePoolDao.findById(dataStore.getId());

            if (storagePoolVO.isManaged()) {
                return StrategyPriority.HIGHEST;
            }

        }
        return StrategyPriority.CANT_HANDLE;
    }