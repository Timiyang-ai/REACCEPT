public double normalize(double amount) {
    return isBuy() ? amount : -amount;
  }