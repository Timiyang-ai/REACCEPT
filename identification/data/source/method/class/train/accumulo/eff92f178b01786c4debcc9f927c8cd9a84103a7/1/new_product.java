static Durability maxDurability(Durability dur1, Durability dur2) {
    if (dur1.ordinal() > dur2.ordinal()) {
      return dur1;
    } else {
      return dur2;
    }
  }