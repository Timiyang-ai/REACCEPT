public Item[] finish() {
    assert size > 0 : "List is empty.";
    
    Item[] its = new Item[size];
    System.arraycopy(values, 0, its, 0, size);
    values = new Item[CAP];
    size = 0;
    return its;
  }