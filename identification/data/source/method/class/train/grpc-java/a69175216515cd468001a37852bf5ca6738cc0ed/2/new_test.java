  @Test
  public void createNegotiator_isAndroid() {
    ClassLoader cl = new ClassLoader(this.getClass().getClassLoader()) {
      @Override
      protected Class<?> findClass(String name) throws ClassNotFoundException {
        // Just don't throw.
        if ("com.android.org.conscrypt.OpenSSLSocketImpl".equals(name)) {
          return null;
        }
        return super.findClass(name);
      }
    };

    OkHttpProtocolNegotiator negotiator = OkHttpProtocolNegotiator.createNegotiator(cl);
    assertEquals(AndroidNegotiator.class, negotiator.getClass());
  }