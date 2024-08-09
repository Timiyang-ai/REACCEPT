  public void testUTC() {

    // /////////////////////////////
    // Current
    // /////////////////////////////
    Date accum0 = create();
    int arg10 = 0;
    int arg11 = 0;
    int arg12 = 0;
    int arg13 = 0;
    int arg14 = 0;
    int arg15 = 0;
    long a0 = accum0.UTC(arg10, arg11, arg12, arg13, arg14, arg15);

    // /////////////////////////////
    // Past
    // /////////////////////////////
    Date accum1 = create(PAST);
    int arg20 = 0;
    int arg21 = 0;
    int arg22 = 0;
    int arg23 = 0;
    int arg24 = 0;
    int arg25 = 0;
    long a1 = accum1.UTC(arg20, arg21, arg22, arg23, arg24, arg25);

    // /////////////////////////////
    // Future
    // /////////////////////////////
    Date accum2 = create(FUTURE);
    int arg30 = 0;
    int arg31 = 0;
    int arg32 = 0;
    int arg33 = 0;
    int arg34 = 0;
    int arg35 = 0;
    long a2 = accum2.UTC(arg30, arg31, arg32, arg33, arg34, arg35);
  }