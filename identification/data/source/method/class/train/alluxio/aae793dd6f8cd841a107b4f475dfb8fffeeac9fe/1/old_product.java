public static synchronized long getRandomNonNegativeLong() {
    return Math.abs(sRandom.nextLong());
  }