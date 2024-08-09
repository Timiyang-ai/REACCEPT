@Override
  public float estimatePreference(long userID, long itemID) throws TasteException {
    Integer useridx = userMap.get(userID);
    if (useridx == null) {
      throw new NoSuchUserException();
    }
    Integer itemidx = itemMap.get(itemID);
    if (itemidx == null) {
      throw new NoSuchItemException();
    }
    return predictRating(useridx, itemidx);
  }