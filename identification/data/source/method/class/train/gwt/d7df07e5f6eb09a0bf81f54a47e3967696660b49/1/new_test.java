  public void test_isDesignTime_evaluate() {
    // not design time
    {
      Beans.setDesignTime(false);
      assertEquals(false, DesignTimeUtilsImpl.isDesignTime("no.Matter"));
    }
    // not this Binder
    try {
      Beans.setDesignTime(true);
      System.setProperty("gwt.UiBinder.isDesignTime my.Binder", "true");
      assertEquals(false, DesignTimeUtilsImpl.isDesignTime("other.Binder"));
    } finally {
      Beans.setDesignTime(false);
      System.clearProperty("gwt.UiBinder.isDesignTime my.design.Binder");
    }
    // OK
    try {
      Beans.setDesignTime(true);
      System.setProperty("gwt.UiBinder.isDesignTime my.Binder", "true");
      assertEquals(true, DesignTimeUtilsImpl.isDesignTime("my.Binder"));
    } finally {
      Beans.setDesignTime(false);
      System.clearProperty("gwt.UiBinder.isDesignTime my.design.Binder");
    }
  }