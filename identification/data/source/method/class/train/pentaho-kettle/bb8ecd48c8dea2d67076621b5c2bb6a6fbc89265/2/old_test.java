  @Test
  public void testDateDiff_A_GT_B() {
    Object daysDiff =
        calculate( "2010-05-12", "2010-01-01", ValueMetaInterface.TYPE_DATE, CalculatorMetaFunction.CALC_DATE_DIFF );
    assertEquals( new Long( 131 ), daysDiff );
  }