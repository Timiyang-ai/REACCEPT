@ExperimentalApi("https://github.com/grpc/grpc-java/issues/492")
  public static Set<String> getAdvertisedMessageEncodings() {
    return INSTANCE.internalGetAdvertisedMessageEncodings();
  }