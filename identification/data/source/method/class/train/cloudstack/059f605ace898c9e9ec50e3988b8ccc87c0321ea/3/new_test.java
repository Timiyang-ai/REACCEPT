    private VolumeVO createVolume(Long templateId, long dataStoreId) {
        VolumeVO volume = new VolumeVO(Volume.Type.DATADISK, UUID.randomUUID().toString(), this.dcId, 1L, 1L, 1L, ProvisioningType.THIN, 1000, 0L, 0L, "");
        volume.setPoolId(dataStoreId);
        volume = volumeDao.persist(volume);
        return volume;
    }