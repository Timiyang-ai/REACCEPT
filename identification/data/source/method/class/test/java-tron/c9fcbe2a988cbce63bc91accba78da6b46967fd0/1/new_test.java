  public void updateWits() {
    int sizePrv = dbManager.getWitnessScheduleStore().getActiveWitnesses().size();
    dbManager
        .getWitnessScheduleStore().getActiveWitnesses()
        .forEach(
            witnessAddress -> {
              logger.info(
                  "witness address is {}",
                  ByteArray.toHexString(witnessAddress.toByteArray()));
            });
    logger.info("------------");
    WitnessCapsule witnessCapsulef =
        new WitnessCapsule(
            ByteString.copyFrom(ByteArray.fromHexString("0x0011")), "www.tron.net/first");
    witnessCapsulef.setIsJobs(true);
    WitnessCapsule witnessCapsules =
        new WitnessCapsule(
            ByteString.copyFrom(ByteArray.fromHexString("0x0012")),
            "www.tron.net/second");
    witnessCapsules.setIsJobs(true);
    WitnessCapsule witnessCapsulet =
        new WitnessCapsule(
            ByteString.copyFrom(ByteArray.fromHexString("0x0013")), "www.tron.net/three");
    witnessCapsulet.setIsJobs(false);

    dbManager
        .getWitnessScheduleStore().getActiveWitnesses()
        .forEach(
            witnessAddress -> {
              logger.info(
                  "witness address is {}",
                  ByteArray.toHexString(witnessAddress.toByteArray()));
            });
    logger.info("---------");
    dbManager.getWitnessStore().put(witnessCapsulef.getAddress().toByteArray(), witnessCapsulef);
    dbManager.getWitnessStore().put(witnessCapsules.getAddress().toByteArray(), witnessCapsules);
    dbManager.getWitnessStore().put(witnessCapsulet.getAddress().toByteArray(), witnessCapsulet);
    dbManager
        .getWitnesses()
        .forEach(
            witnessAddress -> {
              logger.info(
                  "witness address is {}",
                  ByteArray.toHexString(witnessAddress.toByteArray()));
            });
    int sizeTis = dbManager.getWitnesses().size();
    Assert.assertEquals("update add witness size is ",
        2, sizeTis - sizePrv);
  }