public void updateWits() {
    getWitnesses().clear();
    witnessStore.getAllWitnesses().forEach(witnessCapsule -> {
      if (witnessCapsule.getIsJobs()) {
        addWitness(witnessCapsule);
      }
    });
    sortWitness();
  }