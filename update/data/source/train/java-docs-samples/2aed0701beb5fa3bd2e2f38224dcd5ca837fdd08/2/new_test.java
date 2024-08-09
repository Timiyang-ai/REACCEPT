@Test
  public void testCreateTimeOfDay() throws Exception {
    TimeOfDay time = TransferJobUtils.createTimeOfDay("17:00:42");
    assertThat(time).isEqualTo(new TimeOfDay().setHours(17).setMinutes(0).setSeconds(42));

    time = TransferJobUtils.createTimeOfDay("08:09:08");
    assertThat(time).isEqualTo(new TimeOfDay().setHours(8).setMinutes(9).setSeconds(8));
  }