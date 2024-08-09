public static Ethernet buildNdpSolicit(byte[] targetIp,
                                           byte[] sourceIp,
                                           byte[] destinationIp,
                                           byte[] sourceMac,
                                           byte[] destinationMac,
                                           VlanId vlan) {

        if (targetIp.length != Ip6Address.BYTE_LENGTH ||
                sourceIp.length != Ip6Address.BYTE_LENGTH ||
                destinationIp.length != Ip6Address.BYTE_LENGTH ||
                sourceMac.length != MacAddress.MAC_ADDRESS_LENGTH ||
                destinationMac.length != MacAddress.MAC_ADDRESS_LENGTH) {
            return null;
        }

        // Here we craft the Ethernet packet.
        Ethernet ethernet = new Ethernet();
        ethernet.setEtherType(Ethernet.TYPE_IPV6)
                .setDestinationMACAddress(destinationMac)
                .setSourceMACAddress(sourceMac);
        ethernet.setVlanID(vlan.id());
        // IPv6 packet is created.
        IPv6 ipv6 = new IPv6();
        ipv6.setSourceAddress(sourceIp);
        ipv6.setDestinationAddress(destinationIp);
        ipv6.setHopLimit((byte) 255);
        // Create the ICMPv6 packet.
        ICMP6 icmp6 = new ICMP6();
        icmp6.setIcmpType(ICMP6.NEIGHBOR_SOLICITATION);
        icmp6.setIcmpCode((byte) 0);
        // Create the Neighbor Solicitation packet.
        NeighborSolicitation ns = new NeighborSolicitation();
        ns.setTargetAddress(targetIp);
        // DAD packets should not contain SRC_LL_ADDR option
        if (!Arrays.equals(sourceIp, Ip6Address.ZERO.toOctets())) {
            ns.addOption(NeighborDiscoveryOptions.TYPE_SOURCE_LL_ADDRESS, sourceMac);
        }
        // Set the payloads
        icmp6.setPayload(ns);
        ipv6.setPayload(icmp6);
        ethernet.setPayload(ipv6);

        return ethernet;
    }