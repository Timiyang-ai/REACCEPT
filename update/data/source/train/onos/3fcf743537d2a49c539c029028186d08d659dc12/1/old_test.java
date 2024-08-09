@Test
    public void testGetPhysicalDevices() {
        manager.registerTenantId(TenantId.tenantId(tenantIdValue1));
        manager.registerTenantId(TenantId.tenantId(tenantIdValue2));

        VirtualNetwork virtualNetwork1 =
                manager.createVirtualNetwork(TenantId.tenantId(tenantIdValue1));
        VirtualNetwork virtualNetwork2 =
                manager.createVirtualNetwork(TenantId.tenantId(tenantIdValue2));

        // two virtual device in first virtual network
        VirtualDevice vDevice1InVnet1 =
                manager.createVirtualDevice(virtualNetwork1.id(), DID1);
        VirtualDevice vDevice2InVnet1 =
                manager.createVirtualDevice(virtualNetwork1.id(), DID2);
        // Two virtual device in second virtual network
        VirtualDevice vDevice1InVnet2 =
                manager.createVirtualDevice(virtualNetwork2.id(), DID1);
        VirtualDevice vDevice2InVnet2 =
                manager.createVirtualDevice(virtualNetwork2.id(), DID2);

        // Connection Point from each physical device
        // Virtual network 1
        ConnectPoint cp1InVnet1 =
                new ConnectPoint(PHYDID1, PortNumber.portNumber(10));
        ConnectPoint cp2InVnet1 =
                new ConnectPoint(PHYDID2, PortNumber.portNumber(20));
        ConnectPoint cp3InVnet1 =
                new ConnectPoint(PHYDID3, PortNumber.portNumber(30));
        ConnectPoint cp4InVnet1 =
                new ConnectPoint(PHYDID4, PortNumber.portNumber(40));
        // Virtual network 2
        ConnectPoint cp1InVnet2 =
                new ConnectPoint(PHYDID1, PortNumber.portNumber(10));
        ConnectPoint cp2InVnet2 =
                new ConnectPoint(PHYDID2, PortNumber.portNumber(20));
        ConnectPoint cp3InVnet2 =
                new ConnectPoint(PHYDID3, PortNumber.portNumber(30));
        ConnectPoint cp4InVnet2 =
                new ConnectPoint(PHYDID4, PortNumber.portNumber(40));

        // Make simple BigSwitch by mapping two phyDevice to one vDevice
        // First vDevice in first virtual network
        manager.createVirtualPort(virtualNetwork1.id(),
                vDevice1InVnet1.id(), PortNumber.portNumber(1), cp1InVnet1);
        manager.createVirtualPort(virtualNetwork1.id(),
                vDevice1InVnet1.id(), PortNumber.portNumber(2), cp2InVnet1);
        // Second vDevice in first virtual network
        manager.createVirtualPort(virtualNetwork1.id(),
                vDevice2InVnet1.id(), PortNumber.portNumber(1), cp3InVnet1);
        manager.createVirtualPort(virtualNetwork1.id(),
                vDevice2InVnet1.id(), PortNumber.portNumber(2), cp4InVnet1);
        // First vDevice in second virtual network
        manager.createVirtualPort(virtualNetwork2.id(),
                vDevice1InVnet2.id(), PortNumber.portNumber(1), cp1InVnet2);
        manager.createVirtualPort(virtualNetwork2.id(),
                vDevice1InVnet2.id(), PortNumber.portNumber(2), cp2InVnet2);
        // Second vDevice in second virtual network
        manager.createVirtualPort(virtualNetwork2.id(),
                vDevice2InVnet2.id(), PortNumber.portNumber(1), cp3InVnet2);
        manager.createVirtualPort(virtualNetwork2.id(),
                vDevice2InVnet2.id(), PortNumber.portNumber(2), cp4InVnet2);


        Set<DeviceId> physicalDeviceSet;
        Set<DeviceId> testSet = new HashSet<>();
        physicalDeviceSet = manager.getPhysicalDevices(virtualNetwork1.id(), vDevice1InVnet1);
        testSet.add(PHYDID1);
        testSet.add(PHYDID2);
        assertEquals("The physical devices 1 did not match", testSet, physicalDeviceSet);
        physicalDeviceSet.clear();
        testSet.clear();

        physicalDeviceSet = manager.getPhysicalDevices(virtualNetwork1.id(), vDevice2InVnet1);
        testSet.add(PHYDID3);
        testSet.add(PHYDID4);
        assertEquals("The physical devices 2 did not match", testSet, physicalDeviceSet);
        physicalDeviceSet.clear();
        testSet.clear();

        physicalDeviceSet = manager.getPhysicalDevices(virtualNetwork2.id(), vDevice1InVnet2);
        testSet.add(PHYDID1);
        testSet.add(PHYDID2);
        assertEquals("The physical devices 1 did not match", testSet, physicalDeviceSet);
        physicalDeviceSet.clear();
        testSet.clear();

        physicalDeviceSet = manager.getPhysicalDevices(virtualNetwork2.id(), vDevice2InVnet2);
        testSet.add(PHYDID3);
        testSet.add(PHYDID4);
        assertEquals("The physical devices 2 did not match", testSet, physicalDeviceSet);
        physicalDeviceSet.clear();
        testSet.clear();
    }