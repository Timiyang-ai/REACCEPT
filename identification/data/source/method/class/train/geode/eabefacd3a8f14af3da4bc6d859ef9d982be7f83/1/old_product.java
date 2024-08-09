@Override
  @JsonIgnore
  public String getId() {
    String id = getGroup();
    if (isCluster(id)) {
      id = AbstractConfiguration.CLUSTER;
    }
    return id;
  }