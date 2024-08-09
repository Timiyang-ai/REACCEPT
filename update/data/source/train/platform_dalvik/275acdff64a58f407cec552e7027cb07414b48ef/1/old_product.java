public NetworkInterface getNetworkInterface() throws SocketException {
        checkClosedAndBind(false);

        // check if it is set at the IPV6 level. If so then use that. Otherwise
        // do it at the IPV4 level
        Integer theIndex = Integer.valueOf(0);
        try {
            theIndex = (Integer) impl.getOption(SocketOptions.IP_MULTICAST_IF2);
        } catch (SocketException e) {
            // we may get an exception if IPV6 is not enabled.
        }

        if (theIndex.intValue() != 0) {
            Enumeration<NetworkInterface> theInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (theInterfaces.hasMoreElements()) {
                NetworkInterface nextInterface = theInterfaces.nextElement();
                if (nextInterface.getIndex() == theIndex.intValue()) {
                    return nextInterface;
                }
            }
        }

        // ok it was not set at the IPV6 level so try at the IPV4 level
        InetAddress theAddress = (InetAddress) impl
                .getOption(SocketOptions.IP_MULTICAST_IF);
        if (theAddress != null) {
            if (!theAddress.isAnyLocalAddress()) {
                return NetworkInterface.getByInetAddress(theAddress);
            }

            // not set as we got the any address so return a dummy network
            // interface with only the any address. We do this to be
            // compatible
            InetAddress theAddresses[] = new InetAddress[1];
            if ((Socket.preferIPv4Stack() == false)
                    && (InetAddress.preferIPv6Addresses() == true)) {
                theAddresses[0] = Inet6Address.ANY;
            } else {
                theAddresses[0] = InetAddress.ANY;
            }
            return new NetworkInterface(null, null, theAddresses,
                    NetworkInterface.UNSET_INTERFACE_INDEX);
        }

        // ok not set at all so return null
        return null;
    }