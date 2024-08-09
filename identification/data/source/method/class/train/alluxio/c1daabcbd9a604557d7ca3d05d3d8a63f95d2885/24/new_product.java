public static List<InetSocketAddress> getJobMasterRpcAddresses(AlluxioConfiguration conf) {
    int jobRpcPort = NetworkAddressUtils.getPort(NetworkAddressUtils.ServiceType.JOB_MASTER_RPC,
        conf);
    if (conf.isSet(PropertyKey.JOB_MASTER_RPC_ADDRESSES)) {
      return parseInetSocketAddresses(
          conf.getList(PropertyKey.JOB_MASTER_RPC_ADDRESSES, ","));
    } else if (conf.isSet(PropertyKey.JOB_MASTER_EMBEDDED_JOURNAL_ADDRESSES)) {
      return getRpcAddresses(PropertyKey.JOB_MASTER_EMBEDDED_JOURNAL_ADDRESSES, jobRpcPort, conf);
    } else if (conf.isSet(PropertyKey.MASTER_RPC_ADDRESSES)) {
      return getRpcAddresses(PropertyKey.MASTER_RPC_ADDRESSES, jobRpcPort, conf);
    } else {
      return getRpcAddresses(PropertyKey.MASTER_EMBEDDED_JOURNAL_ADDRESSES, jobRpcPort, conf);
    }
  }