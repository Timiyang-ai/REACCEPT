@Test
    public void testSetupPriorityOfRedundantRouter() {
        // Prepare
        this.deployment.routers = new ArrayList<>();
        final DomainRouterVO routerVO1 = mock(DomainRouterVO.class);
        this.deployment.routers.add(routerVO1);
        when(routerVO1.getId()).thenReturn(ROUTER1_ID);
        when(routerVO1.getIsRedundantRouter()).thenReturn(true);
        when(routerVO1.getState()).thenReturn(VirtualMachine.State.Stopped);
        final DomainRouterVO routerVO2 = mock(DomainRouterVO.class);
        this.deployment.routers.add(routerVO2);
        when(routerVO2.getId()).thenReturn(ROUTER2_ID);
        when(routerVO2.getIsRedundantRouter()).thenReturn(true);
        when(routerVO2.getState()).thenReturn(VirtualMachine.State.Stopped);
        // If this deployment is not redundant nothing will be executed
        this.deployment.isRedundant = true;

        // Execute
        this.deployment.setupPriorityOfRedundantRouter();

        // Assert
        verify(routerVO1, times(1)).setPriority(0);
        verify(routerVO1, times(1)).setIsPriorityBumpUp(false);
        verify(this.mockRouterDao, times(1)).update(ROUTER1_ID, routerVO1);
        verify(routerVO2, times(1)).setPriority(0);
        verify(routerVO2, times(1)).setIsPriorityBumpUp(false);
        verify(this.mockRouterDao, times(1)).update(ROUTER2_ID, routerVO2);
    }