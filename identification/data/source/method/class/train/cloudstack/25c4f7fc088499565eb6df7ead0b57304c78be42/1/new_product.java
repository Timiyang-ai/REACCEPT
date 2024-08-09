protected String generateDestPath(Host destHost, StoragePoolVO destStoragePool, VolumeInfo destVolumeInfo) {
        return connectHostToVolume(destHost, destVolumeInfo.getPoolId(), destVolumeInfo.get_iScsiName());
    }