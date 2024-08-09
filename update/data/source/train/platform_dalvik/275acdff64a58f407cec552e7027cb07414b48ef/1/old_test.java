@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "getNetworkInterface",
        args = {}
    )
    @KnownFailure("No interfaces if there's no debugger connected")
    public void test_getNetworkInterface() throws IOException {
        int groupPort = Support_PortManager.getNextPortForUDP();
        if (atLeastOneInterface) {
            // validate that we get the expected response when one was not
            // set
            mss = new MulticastSocket(groupPort);
            NetworkInterface theInterface = mss.getNetworkInterface();
            assertNotNull(
                    "network interface returned wrong network interface when " +
                    "not set:"
                            + theInterface, theInterface.getInetAddresses());
            InetAddress firstAddress = (InetAddress) theInterface
                    .getInetAddresses().nextElement();
            // validate we the first address in the network interface is the
            // ANY address
            String preferIPv4StackValue = System
                    .getProperty("java.net.preferIPv4Stack");
            String preferIPv6AddressesValue = System
                    .getProperty("java.net.preferIPv6Addresses");
            if (((preferIPv4StackValue == null) || preferIPv4StackValue
                    .equalsIgnoreCase("false"))
                    && (preferIPv6AddressesValue != null)
                    && (preferIPv6AddressesValue.equals("true"))) {
                assertTrue(
                        "network interface returned wrong network interface " +
                        "when not set:"
                                + theInterface, InetAddress.getByName("::0")
                                .equals(firstAddress));

            } else {
                assertTrue(
                        "network interface returned wrong network interface " +
                        "when not set:"
                                + theInterface, InetAddress
                                .getByName("0.0.0.0").equals(firstAddress));
            }

            mss.setNetworkInterface(networkInterface1);
            assertTrue(
                    "getNetworkInterface did not return interface set by " +
                    "setNeworkInterface",
                    networkInterface1.equals(mss.getNetworkInterface()));

            if (atLeastTwoInterfaces) {
                mss.setNetworkInterface(networkInterface2);
                assertTrue(
                        "getNetworkInterface did not return network interface " +
                        "set by second setNetworkInterface call",
                        networkInterface2.equals(mss.getNetworkInterface()));
            }

            groupPort = Support_PortManager.getNextPortForUDP();
            mss = new MulticastSocket(groupPort);
            if (IPV6networkInterface1 != null) {
                mss.setNetworkInterface(IPV6networkInterface1);
                assertTrue(
                        "getNetworkInterface did not return interface set " +
                        "by setNeworkInterface",
                        IPV6networkInterface1.equals(mss.getNetworkInterface()));
            }

            // validate that we get the expected response when we set via
            // setInterface
            groupPort = Support_PortManager.getNextPortForUDP();
            mss = new MulticastSocket(groupPort);
            Enumeration addresses = networkInterface1.getInetAddresses();
            if (addresses != null) {
                firstAddress = (InetAddress) addresses.nextElement();
                mss.setInterface(firstAddress);
                assertTrue(
                        "getNetworkInterface did not return interface set " +
                        "by setInterface",
                        networkInterface1.equals(mss.getNetworkInterface()));
            }
            
            mss.close();
            try {
                mss.getNetworkInterface();
                fail("SocketException was not thrown.");
            } catch(SocketException ioe) {
                //expected
            }
        }
    }