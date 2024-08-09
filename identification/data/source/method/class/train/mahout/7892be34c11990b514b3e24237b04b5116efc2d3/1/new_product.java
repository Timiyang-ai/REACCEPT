@Override
  public float estimatePreference(long userID, long itemID) throws TasteException {
    double[] userFeatures = factorization.getUserFeatures(userID);
    double[] itemFeatures = factorization.getItemFeatures(itemID);
    double estimate = 0;
    for (int feature = 0; feature < userFeatures.length; feature++) {
      estimate += userFeatures[feature] * itemFeatures[feature];
    }
    return (float) estimate;
  }