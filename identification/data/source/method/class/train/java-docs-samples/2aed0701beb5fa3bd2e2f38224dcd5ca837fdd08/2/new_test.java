@Test
  public void testCreateDate() throws Exception {
    Date date = TransferJobUtils.createDate("2000-12-30");
    assertThat(date).isEqualTo(new Date().setYear(2000).setMonth(12).setDay(30));

    date = TransferJobUtils.createDate("2016-09-08");
    assertThat(date).isEqualTo(new Date().setYear(2016).setMonth(9).setDay(8));
  }