public void negotiationDone(OspfMessage ospfMessage,
                                boolean neighborIsMaster, List payload, Channel ch) throws Exception {
        stopRxMtDdTimer();
        OspfPacketHeader packet = (OspfPacketHeader) ospfMessage;
        DdPacket ddPacketForCheck = (DdPacket) packet;
        if (ddPacketForCheck.isOpaqueCapable()) {
            OspfLsdb database = ospfArea.database();
            List opaqueLsas = database.getAllLsaHeaders(true, true);
            Iterator iterator = opaqueLsas.iterator();
            while (iterator.hasNext()) {
                OspfLsa ospfLsa = (OspfLsa) iterator.next();
                if (ospfLsa.getOspfLsaType() == OspfLsaType.AREA_LOCAL_OPAQUE_LSA) {
                    OpaqueLsa10 opaqueLsa10 = (OpaqueLsa10) ospfLsa;
                    topLevelTlvs = opaqueLsa10.topLevelValues();
                }
            }
        }
        if (state == OspfNeighborState.EXSTART) {
            state = OspfNeighborState.EXCHANGE;
            boolean excludeMaxAgeLsa = true;
            //list of contents of area wise LSA
            ddSummaryList = (CopyOnWriteArrayList) ospfArea.getLsaHeaders(excludeMaxAgeLsa, isOpaqueCapable);

            if (neighborIsMaster) {
                processLsas(payload);
                // ...construct new DD Packet...
                DdPacket ddPacket = new DdPacket();
                // setting OSPF Header
                ddPacket.setOspfVer(OspfUtil.OSPF_VERSION);
                ddPacket.setOspftype(OspfPacketType.DD.value());
                ddPacket.setRouterId(ospfArea.routerId());
                ddPacket.setAreaId(ospfArea.areaId());
                ddPacket.setAuthType(OspfUtil.NOT_ASSIGNED);
                ddPacket.setAuthentication(OspfUtil.NOT_ASSIGNED);
                ddPacket.setOspfPacLength(OspfUtil.NOT_ASSIGNED); // to calculate packet length
                ddPacket.setChecksum(OspfUtil.NOT_ASSIGNED);
                boolean isOpaqueEnabled = ospfArea.isOpaqueEnabled();
                if (isOpaqueEnabled && isOpaqueCapable) {
                    ddPacket.setOptions(ospfArea.opaqueEnabledOptions());
                } else {
                    ddPacket.setOptions(ospfArea.options());
                }
                ddPacket.setIsInitialize(OspfUtil.INITIALIZE_NOTSET);
                ddPacket.setIsMore(OspfUtil.MORE_NOTSET);
                ddPacket.setIsMaster(OspfUtil.NOT_MASTER);
                ddPacket.setImtu(ospfInterface.mtu());
                ddPacket.setSequenceNo(ddSeqNum);
                //setting the destination
                ddPacket.setDestinationIp(packet.sourceIp());
                setLastSentDdPacket(ddPacket);
                getIsMoreBit();

                ch.write(lastSentDdPacket());
            } else {
                // process LSA Vector's List, Add it to LSRequestList.
                processLsas(payload);
                DdPacket ddPacket = new DdPacket();
                // setting OSPF Header
                ddPacket.setOspfVer(OspfUtil.OSPF_VERSION);
                ddPacket.setOspftype(OspfPacketType.DD.value());
                ddPacket.setRouterId(ospfArea.routerId());
                ddPacket.setAreaId(ospfArea.areaId());
                ddPacket.setAuthType(OspfUtil.NOT_ASSIGNED);
                ddPacket.setAuthentication(OspfUtil.NOT_ASSIGNED);
                ddPacket.setOspfPacLength(OspfUtil.NOT_ASSIGNED); // to calculate packet length
                ddPacket.setChecksum(OspfUtil.NOT_ASSIGNED);
                boolean isOpaqueEnabled = ospfArea.isOpaqueEnabled();
                if (isOpaqueEnabled && isOpaqueCapable) {
                    ddPacket.setOptions(ospfArea.opaqueEnabledOptions());
                } else {
                    ddPacket.setOptions(ospfArea.options());
                }
                ddPacket.setIsInitialize(OspfUtil.INITIALIZE_NOTSET);
                ddPacket.setIsMore(OspfUtil.MORE_NOTSET);
                ddPacket.setIsMaster(OspfUtil.IS_MASTER);
                ddPacket.setImtu(ospfInterface.mtu());
                ddPacket.setSequenceNo(ddSeqNum);
                setLastSentDdPacket(ddPacket);
                getIsMoreBit();
                ddPacket.setDestinationIp(packet.sourceIp());
                ch.write(lastSentDdPacket());
                startRxMtDdTimer(ch);
            }
        }
    }