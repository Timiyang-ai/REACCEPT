public void updateWits() {
    wits.clear();
    witnessStore.getAllWitnesses().forEach(witnessCapsule -> {
      if (witnessCapsule.getIsJobs()) {
        wits.add(witnessCapsule);
      }
    });
    sortWitness(wits);
  }