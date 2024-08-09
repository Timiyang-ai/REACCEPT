public OFPacketOut handleDHCPDiscover(@Nonnull IOFSwitch sw, @Nonnull OFPort inPort, @Nonnull DHCPInstance instance,
                                          @Nonnull IPv4Address clientIP, @Nonnull DHCP payload, @Nonnull Boolean dynamicLease) {
        /**  DHCP Discover Message
         * -- UDP src port = 68
         * -- UDP dst port = 67
         * -- IP src addr = 0.0.0.0
         * -- IP dst addr = 255.255.255.255
         * -- Opcode = 0x01
         * -- XID = transactionX
         * --
         * --	ciaddr = 0.0.0.0
         * --	yiaddr = 0.0.0.0
         * --	siaddr = 0.0.0.0
         * --	giaddr = 0.0.0.0
         * --   chaddr = Client's MAC
         * --
         * -- Options:
         * --	Option 53 = DHCP Discover
         * --	Option 50 = possible IP request
         * --	Option 55 = parameter request list
         * --		(1)  SN Mask
         * --		(3)  Router
         * --		(15) Domain Name
         * --		(6)  DNS
         **/

        DHCPReturnMessage returnMessage = new DHCPReturnMessage();

        int xid = payload.getTransactionId();
        IPv4Address giaddr = payload.getGatewayIPAddress();    // Will have GW IP if a relay agent was used
        IPv4Address yiaddr = payload.getYourIPAddress();
        MacAddress chaddr = payload.getClientHardwareAddress();
        List<Byte> requestOrder = new ArrayList<>();
        IPv4Address requestIP = null;

        for (DHCPOption option : payload.getOptions()) {
            if (option.getCode() == DHCPOptionCode.OptionCode_RequestedIP.getValue()) {
                requestIP = IPv4Address.of(option.getData());
                log.debug("Handling Discover Message: got requested IP");
            } else if (option.getCode() == DHCPOptionCode.OptionCode_RequestedParameters.getValue()) {
                requestOrder = getRequestedParameters(payload, false);
                log.debug("Handling Discover Message: got requested param list");
            }
        }

        DHCPPool dhcpPool = instance.getDHCPPool();
        long leaseTime = instance.getLeaseTimeSec();
        IPv4Address ipLease = null;

        if (!dynamicLease) {
            if (requestIP == null) {
                ipLease = dhcpPool.assignLeaseToClient(chaddr, leaseTime).get();
            } else {
                ipLease = dhcpPool.assignLeaseToClientWithRequestIP(requestIP, chaddr, leaseTime, false).get();
            }
        }else {
            if (requestIP == null) {
                ipLease = dhcpPool.assignDynamicLeaseToClient(chaddr, leaseTime).get();
            } else {
                ipLease = dhcpPool.assignLeaseToClientWithRequestIP(requestIP, chaddr, leaseTime, true).get();
            }

        }

        yiaddr = ipLease;
        DHCP dhcpOffer = buildDHCPOfferMessage(instance, chaddr, yiaddr, giaddr, xid, requestOrder);
        OFPacketOut dhcpPacketOut = buildDHCPOfferPacketOut(instance, sw, inPort, clientIP, dhcpOffer);
        return dhcpPacketOut;

    }