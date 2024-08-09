public boolean addWeakChild(Object c) {
      Object o = Metadata.this.owner.get();
      assert o != c;
      if(o != null && addChildInt(new WeakReference<>(c))) {
        Metadata.of(c).hierarchy().addParentInt(o);
        ResultListenerList.resultAdded(c, o);
        return true;
      }
      return false;
    }