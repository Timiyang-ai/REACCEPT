public List<MasterBlockLocation> getBlockLocations() {
    List<MasterBlockLocation> ret = new ArrayList<MasterBlockLocation>(mWorkerIdToAlias.size());
    for (Map.Entry<Long, String> entry : mWorkerIdToAlias.entrySet()) {
      ret.add(new MasterBlockLocation(entry.getKey(), entry.getValue()));
    }
    return ret;
  }