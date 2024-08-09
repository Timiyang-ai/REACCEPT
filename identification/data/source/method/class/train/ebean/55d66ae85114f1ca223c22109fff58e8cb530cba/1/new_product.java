@Override
  public ProfileStream createProfileStream(int profileId) {

    if (profileId < 1) {
      // not this transaction
      return null;
    }

    if (includeIds.length == 0) {
      return new DefaultProfileStream(profileId, verbose);
    }

    // check if we are profiling this specific transaction profileId, just
    // perform linear search as this is expected to be a small array
    for (int includeId : includeIds) {
      if (includeId == profileId) {
        return new DefaultProfileStream(profileId, verbose);
      }
    }
    return null;
  }