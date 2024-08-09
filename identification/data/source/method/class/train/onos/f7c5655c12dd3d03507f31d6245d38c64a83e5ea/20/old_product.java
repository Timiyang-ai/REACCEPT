public void badLSReq(Channel ch) throws Exception {
        log.debug("OSPFNbr::badLSReq...!!!");

        if (state.getValue() >= OspfNeighborState.EXCHANGE.getValue()) {
            if (state == OspfNeighborState.FULL) {
                ospfArea.refreshArea(ospfInterface);
            }

            stopRxMtDdTimer();
            state = OspfNeighborState.EXSTART;

            lsReqList.clear();
            ddSummaryList.clear();
            reTxList.clear();
            //increment the dd sequence number
            isMaster = OspfUtil.IS_MASTER;
            ddSeqNum++;
            DdPacket ddPacket = new DdPacket();
            // seting OSPF Header
            ddPacket.setOspfVer(OspfUtil.OSPF_VERSION);
            ddPacket.setOspftype(OspfPacketType.DD.value());
            ddPacket.setRouterId(ospfArea.routerId());
            ddPacket.setAreaId(ospfArea.areaId());
            ddPacket.setAuthType(OspfUtil.NOT_ASSIGNED);
            ddPacket.setAuthentication(OspfUtil.NOT_ASSIGNED);
            ddPacket.setOspfPacLength(OspfUtil.NOT_ASSIGNED); // to calculate packet length
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
            ch.write(ddPacket);
        }
    }