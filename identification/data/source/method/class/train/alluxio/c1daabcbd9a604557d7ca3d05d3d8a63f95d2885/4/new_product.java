public static List<InetSocketAddress> getJobMasterRpcAddresses(AlluxioConfiguration conf) {
    // First check whether job rpc addresses are explicitly configured.
    if (conf.isSet(PropertyKey.JOB_MASTER_RPC_ADDRESSES)) {
      return parseInetSocketAddresses(
          conf.getList(PropertyKey.JOB_MASTER_RPC_ADDRESSES, ","));
    }

    int jobRpcPort =
        NetworkAddressUtils.getPort(NetworkAddressUtils.ServiceType.JOB_MASTER_RPC, conf);
    // Fall back on explicitly configured regular master rpc addresses.
    if (conf.isSet(PropertyKey.MASTER_RPC_ADDRESSES)) {
      List<InetSocketAddress> addrs =
          parseInetSocketAddresses(conf.getList(PropertyKey.MASTER_RPC_ADDRESSES, ","));
      return overridePort(addrs, jobRpcPort);
    }

    // Fall back on server-side journal configuration.
    return overridePort(getEmbeddedJournalAddresses(conf, ServiceType.JOB_MASTER_RAFT), jobRpcPort);
  }