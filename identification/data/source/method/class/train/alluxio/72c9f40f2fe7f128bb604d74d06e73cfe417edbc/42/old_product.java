@GET
  @Path(GET_UFS_FREE_BYTES)
  @ReturnType("java.lang.Long")
  @Deprecated
  public Response getUfsFreeBytes() {
    return RestUtils.call(() -> {
      Capacity capacity = getUfsCapacityInternal();
      if (capacity.getTotal() >= 0 && capacity.getUsed() >= 0 && capacity.getTotal() >= capacity
          .getUsed()) {
        return capacity.getTotal() - capacity.getUsed();
      }
      return -1;
    });
  }