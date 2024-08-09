public static List<InetSocketAddress> getMasterRpcAddresses(AlluxioConfiguration conf) {
    // First check whether rpc addresses are explicitly configured.
    if (conf.isSet(PropertyKey.MASTER_RPC_ADDRESSES)) {
      return parseInetSocketAddresses(conf.getList(PropertyKey.MASTER_RPC_ADDRESSES, ","));
    }

    // Fall back on server-side journal configuration.
    int rpcPort = NetworkAddressUtils.getPort(NetworkAddressUtils.ServiceType.MASTER_RPC, conf);
    return overridePort(getEmbeddedJournalAddresses(conf, ServiceType.MASTER_RAFT), rpcPort);
  }