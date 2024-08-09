public void deleteSecretsByName(String name) {
    checkArgument(!name.isEmpty());
    secretSeriesDAO.deleteSecretSeriesByName(name);
  }