public double normalize(double amount) {
    double normalized = Math.abs(amount);
    if (normalized == 0) {
      return 0;
    }
    return isBuy() ? normalized : -normalized;
  }