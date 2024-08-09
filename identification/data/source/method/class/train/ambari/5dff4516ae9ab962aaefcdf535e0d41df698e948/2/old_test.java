  @Test
  public void testisOperationAllowed() throws Exception {
    // Tests that isOperationAllowed() falls
    // back to guessing req op level if operation level is not specified
    // explicitly
    Injector injector = createStrictMock(Injector.class);
    Cluster cluster = createMock(Cluster.class);
    Method isOperationAllowed = MaintenanceStateHelper.class.getDeclaredMethod(
            "isOperationAllowed", new Class[]{Cluster.class, Type.class,
            String.class, String.class, String.class});
    MaintenanceStateHelper maintenanceStateHelper =
            createMockBuilder(MaintenanceStateHelper.class)
                    .withConstructor(injector)
                    .addMockedMethod(isOperationAllowed)
                    .createNiceMock();

    RequestResourceFilter filter = createMock(RequestResourceFilter.class);
    RequestOperationLevel level = createMock(RequestOperationLevel.class);
    expect(level.getLevel()).andReturn(Type.Cluster);
    expect(maintenanceStateHelper.isOperationAllowed(
            anyObject(Cluster.class), anyObject(Type.class),
            anyObject(String.class),
            anyObject(String.class), anyObject(String.class))).andStubReturn(true);
    // Case when level is defined
    replay(cluster, maintenanceStateHelper, level);
    maintenanceStateHelper.isOperationAllowed(cluster, level, filter, "service", "component", "hostname");
    verify(maintenanceStateHelper, level);

    maintenanceStateHelper =
            createMockBuilder(MaintenanceStateHelper.class)
                    .withConstructor(injector)
                    .addMockedMethod(isOperationAllowed)
                    .addMockedMethod("guessOperationLevel")
                    .createNiceMock();

    expect(maintenanceStateHelper.guessOperationLevel(anyObject(RequestResourceFilter.class))).andReturn(Type.Cluster);
    expect(maintenanceStateHelper.isOperationAllowed(
            anyObject(Cluster.class), anyObject(Type.class),
            anyObject(String.class),
            anyObject(String.class), anyObject(String.class))).andStubReturn(true);
    // Case when level is not defined
    replay(maintenanceStateHelper);
    maintenanceStateHelper.isOperationAllowed(cluster, null, filter, "service", "component", "hostname");
    verify(maintenanceStateHelper);
  }