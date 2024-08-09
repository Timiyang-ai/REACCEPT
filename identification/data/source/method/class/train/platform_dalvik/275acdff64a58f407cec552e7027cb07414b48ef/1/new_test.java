public void test_getNetworkInterface() throws IOException {
        int groupPort = Support_PortManager.getNextPortForUDP();
        if (atLeastOneInterface) {
            // validate that we get the expected response when one was not
            // set
            mss = new MulticastSocket(groupPort);
            NetworkInterface theInterface = mss.getNetworkInterface();
            assertTrue(
                    "network interface returned wrong network interface when not set:"
                            + theInterface, theInterface.getInetAddresses()
                            .hasMoreElements());
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
                assertEquals("network interface returned wrong network interface when not set:"
                             + theInterface,
                             firstAddress, InetAddress.getByName("::0"));

            } else {
                assertEquals("network interface returned wrong network interface when not set:"
                             + theInterface,
                             InetAddress.getByName("0.0.0.0"),
                             firstAddress);
            }

            mss.setNetworkInterface(networkInterface1);
            assertEquals("getNetworkInterface did not return interface set by setNeworkInterface",
                         networkInterface1, mss.getNetworkInterface());

            if (atLeastTwoInterfaces) {
                mss.setNetworkInterface(networkInterface2);
                assertEquals("getNetworkInterface did not return network interface set by second setNetworkInterface call",
                             networkInterface2, mss.getNetworkInterface());
            }

            groupPort = Support_PortManager.getNextPortForUDP();
            mss = new MulticastSocket(groupPort);
            if (IPV6networkInterface1 != null) {
                mss.setNetworkInterface(IPV6networkInterface1);
                assertEquals("getNetworkInterface did not return interface set by setNeworkInterface",
                             IPV6networkInterface1,
                             mss.getNetworkInterface());
            }

            // validate that we get the expected response when we set via
            // setInterface
            groupPort = Support_PortManager.getNextPortForUDP();
            mss = new MulticastSocket(groupPort);
            Enumeration addresses = networkInterface1.getInetAddresses();
            if (addresses.hasMoreElements()) {
                firstAddress = (InetAddress) addresses.nextElement();
                mss.setInterface(firstAddress);
                assertEquals("getNetworkInterface did not return interface set by setInterface",
                             networkInterface1,
                             mss.getNetworkInterface());
            }
        }
    }