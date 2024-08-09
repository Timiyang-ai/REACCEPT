public NetworkLsa buildNetworkLsa(Ip4Address interfaceIp, Ip4Address mask) throws Exception {
        // generate the Router-LSA for this Area.
        NetworkLsa networkLsa = new NetworkLsa();
        networkLsa.setAdvertisingRouter(routerId);
        networkLsa.setLinkStateId(interfaceIp.toString());
        networkLsa.setLsType(OspfLsaType.NETWORK.value());
        networkLsa.setAge(1);
        networkLsa.setOptions(2);
        networkLsa.setNetworkMask(mask);
        //Adding our own router.
        networkLsa.addAttachedRouter(routerId());
        Iterator iter = interfacesLst.iterator();
        OspfInterfaceImpl ospfInterface = null;
        while (iter.hasNext()) {
            ospfInterface = (OspfInterfaceImpl) iter.next();
            if (ospfInterface.ipAddress().equals(interfaceIp)) {
                break;
            }
        }
        if (ospfInterface != null) {
            List<OspfNbr> neighborsInFullState = getNeighborsInFullState(ospfInterface);
            if (neighborsInFullState != null) {
                for (OspfNbr ospfnbr : neighborsInFullState) {
                    networkLsa.addAttachedRouter(ospfnbr.neighborId());
                    log.debug("Adding attached neighbor:: {}", ospfnbr.neighborId());
                }
            }
        }
        networkLsa.setLsSequenceNo(database.getLsSequenceNumber(OspfLsaType.NETWORK));
        //Find the byte length and add it in lsa object
        ChecksumCalculator checksum = new ChecksumCalculator();
        byte[] lsaBytes = networkLsa.asBytes();
        networkLsa.setLsPacketLen(lsaBytes.length);
        //Convert lsa object to byte again to reflect the packet length which we added.
        lsaBytes = networkLsa.asBytes();
        //find the checksum
        byte[] twoByteChecksum = checksum.calculateLsaChecksum(lsaBytes,
                                                               OspfUtil.LSAPACKET_CHECKSUM_POS1,
                                                               OspfUtil.LSAPACKET_CHECKSUM_POS2);
        int checkSumVal = OspfUtil.byteToInteger(twoByteChecksum);
        networkLsa.setLsCheckSum(checkSumVal);
        return networkLsa;
    }