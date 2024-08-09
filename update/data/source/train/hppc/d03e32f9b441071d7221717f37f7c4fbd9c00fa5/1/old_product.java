public void ensureCapacity(int expectedElements) {
      int additions = expectedElements - size();
      if (additions > 0 || buffer == null) {
        ensureBufferSpace(additions);
      }
    }