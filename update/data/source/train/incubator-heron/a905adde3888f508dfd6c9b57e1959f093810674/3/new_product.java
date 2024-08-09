public static Duration extractTopologyTimeout(TopologyAPI.Topology topology) {
    for (TopologyAPI.Config.KeyValue keyValue : topology.getTopologyConfig().getKvsList()) {
      if (keyValue.getKey().equals("topology.message.timeout.secs")) {
        return TypeUtils.getDuration(keyValue.getValue(), ChronoUnit.SECONDS);
      }
    }

    throw new IllegalArgumentException("topology.message.timeout.secs does not exist");
  }