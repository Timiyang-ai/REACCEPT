public void remove(int key) {
    checkKey(key);
    int loc = locate(key);
    if (loc >= 0) {
      setKey(loc, DELETED);
      removedKeyCount++;
      keyCount--;
    }
  }