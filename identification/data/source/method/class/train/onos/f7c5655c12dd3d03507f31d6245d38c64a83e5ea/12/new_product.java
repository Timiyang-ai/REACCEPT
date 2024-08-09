public void adjOk(Channel ch) {
        log.debug("OSPFNbr::adjOk...!!!");
        if (ospfInterface.interfaceType() != OspfInterfaceType.POINT_TO_POINT.value()) {
            if (state == OspfNeighborState.TWOWAY) {
                if (formAdjacencyOrNot()) {
                    state = OspfNeighborState.EXSTART;
                    //check for sequence number in lsdb
                    ddSeqNum++;

                    DdPacket ddPacket = new DdPacket();
                    // seting OSPF Header
                    ddPacket.setOspfVer(OspfUtil.OSPF_VERSION);
                    ddPacket.setOspftype(OspfPacketType.DD.value());
                    ddPacket.setRouterId(ospfArea.routerId());
                    ddPacket.setAreaId(ospfArea.areaId());
                    ddPacket.setAuthType(OspfUtil.NOT_ASSIGNED);
                    ddPacket.setAuthentication(OspfUtil.NOT_ASSIGNED);
                    ddPacket.setOspfPacLength(OspfUtil.NOT_ASSIGNED);
                    ddPacket.setChecksum(OspfUtil.NOT_ASSIGNED);

                    // setting DD Body
                    boolean isOpaqueEnabled = ospfArea.isOpaqueEnabled();
                    if (isOpaqueEnabled && this.isOpaqueCapable) {
                        ddPacket.setOptions(ospfArea.opaqueEnabledOptions());
                    } else {
                        ddPacket.setOptions(ospfArea.options());
                    }
                    ddPacket.setIsInitialize(OspfUtil.INITIALIZE_SET);
                    ddPacket.setIsMore(OspfUtil.MORE_SET);
                    ddPacket.setIsMaster(OspfUtil.IS_MASTER);
                    ddPacket.setImtu(ospfInterface.mtu());
                    ddPacket.setSequenceNo(ddSeqNum);
                    rxmtDdPacketTask = new InternalRxmtDdPacket(ch);
                    startRxMtDdTimer(ch);
                    //setting destination ip
                    ddPacket.setDestinationIp(neighborIpAddr());
                    setLastSentDdPacket(ddPacket);
                    byte[] messageToWrite = getMessage(ddPacket);
                    ch.write(messageToWrite);
                }
            } else if (state.getValue() >= OspfNeighborState.EXSTART.getValue()) {
                if (!formAdjacencyOrNot()) {
                    state = OspfNeighborState.TWOWAY;
                    lsReqList.clear();
                    ddSummaryList.clear();
                    reTxList.clear();
                }
            }
        }
    }