public void remove(PropertyKey key) {
    // remove is a nop if the key doesn't already exist
    if (mUserProps.containsKey(key)) {
      mUserProps.remove(key);
    }
  }