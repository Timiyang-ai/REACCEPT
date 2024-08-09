public static long createBlockID(long containerID, long sequenceNumber) {
    // TODO(gene): Check for valid IDs here?
    return ((containerID & CONTAINER_ID_MASK) << SEQUENCE_NUMBER_BITS)
        | (sequenceNumber & SEQUENCE_NUMBER_MASK);
  }