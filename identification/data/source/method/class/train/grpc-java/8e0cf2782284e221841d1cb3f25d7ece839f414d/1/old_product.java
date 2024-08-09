public static ConnectivityStateInfo forNonError(ConnectivityState state) {
    Preconditions.checkArgument(state != ConnectivityState.TRANSIENT_FAILURE,
        "state is TRANSIENT_ERROR. Use forError() instead");
    return new ConnectivityStateInfo(state, Status.OK);
  }