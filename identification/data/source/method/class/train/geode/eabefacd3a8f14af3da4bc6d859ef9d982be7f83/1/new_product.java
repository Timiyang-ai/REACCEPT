@Override
  @JsonIgnore
  public String getId() {
    String id = getGroup();
    if (isCluster(id)) {
      id = CLUSTER;
    }
    return id;
  }