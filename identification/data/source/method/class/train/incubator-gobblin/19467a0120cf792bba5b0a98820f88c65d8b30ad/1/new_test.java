  @Test
  public void fromEventTest() {
    String name = "TestName";
    int count = 10;
    CountEventBuilder countEventBuilder = new CountEventBuilder(name, count);
    GobblinTrackingEvent event = countEventBuilder.build();

    //Count Event
    CountEventBuilder builderFromEvent = CountEventBuilder.fromEvent(event);
    Assert.assertEquals(CountEventBuilder.isCountEvent(event), true);
    Assert.assertNotNull(builderFromEvent);
    Assert.assertEquals(builderFromEvent.getName(), name);
    Assert.assertEquals(builderFromEvent.getCount(), count);

    // General Event
    event = new GobblinTrackingEvent();
    countEventBuilder = CountEventBuilder.fromEvent(event);
    Assert.assertEquals(CountEventBuilder.isCountEvent(event), false);
    Assert.assertEquals(countEventBuilder, null);
  }