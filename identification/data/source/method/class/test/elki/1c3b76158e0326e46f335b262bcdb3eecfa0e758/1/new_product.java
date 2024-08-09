public boolean addWeakChild(Object c) {
      Object o = Metadata.this.get();
      assert o != c;
      if(o != null && addChildInt(new WeakReference<>(c))) {
        Metadata.of(c).hierarchy().addParentInt(Metadata.this);
        Metadata.of(o).notifyChildAdded(c);
        return true;
      }
      return false;
    }