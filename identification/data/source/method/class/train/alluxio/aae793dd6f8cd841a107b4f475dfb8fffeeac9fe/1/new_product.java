public static synchronized long getRandomNonNegativeLong() {
    return sRandom.nextLong() & Long.MAX_VALUE;
  }