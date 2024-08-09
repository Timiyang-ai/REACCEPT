public void deleteSecretsByName(String name) {
    checkArgument(!name.isEmpty());
    secretSeriesJooqDao.deleteSecretSeriesByName(name);
  }