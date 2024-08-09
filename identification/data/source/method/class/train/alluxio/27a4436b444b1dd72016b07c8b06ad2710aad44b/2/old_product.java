public static long createBlockId(long containerId, long sequenceNumber) {
    // TODO(gene): Check for valid ids here?
    return ((containerId & CONTAINER_ID_MASK) << SEQUENCE_NUMBER_BITS)
        | (sequenceNumber & SEQUENCE_NUMBER_MASK);
  }