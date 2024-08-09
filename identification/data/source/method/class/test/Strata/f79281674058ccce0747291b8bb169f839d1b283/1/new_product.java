public double normalize(double amount) {
    double normalized = Math.abs(amount);
    return isBuy() ? normalized : -normalized;
  }