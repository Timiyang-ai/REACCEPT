public static List<InetSocketAddress> getMasterRpcAddresses(AlluxioConfiguration conf) {
    if (conf.isSet(PropertyKey.MASTER_RPC_ADDRESSES)) {
      return parseInetSocketAddresses(conf.getList(PropertyKey.MASTER_RPC_ADDRESSES, ","));
    } else {
      int rpcPort = NetworkAddressUtils.getPort(NetworkAddressUtils.ServiceType.MASTER_RPC, conf);
      return getRpcAddresses(PropertyKey.MASTER_EMBEDDED_JOURNAL_ADDRESSES, rpcPort, conf);
    }
  }