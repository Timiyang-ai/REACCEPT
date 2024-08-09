public static SpiTransaction get(String serverName) {
    TransactionMap map = local.get();
    State state = map.getState(serverName);
    SpiTransaction t = (state == null) ? null : state.transaction;
    if (map.isEmpty()) {
      local.remove();
    }
    return t;
  }