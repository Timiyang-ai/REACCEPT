public void remove(int key) {
    checkKey(key);
    int loc = locate(key);
    if (loc >= 0) {
      entries[loc] = DELETED;
      keyCount--;
    }
  }