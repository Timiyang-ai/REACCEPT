public void sendLsa(LsaHeader lsa, Ip4Address destination, Channel ch) {
        if (lsa == null) {
            return;
        }

        LsUpdate responseLsUpdate = new LsUpdate();
        // seting OSPF Header
        responseLsUpdate.setOspfVer(OspfUtil.OSPF_VERSION);
        responseLsUpdate.setOspftype(OspfPacketType.LSUPDATE.value());
        responseLsUpdate.setRouterId(ospfArea.routerId());
        responseLsUpdate.setAreaId(ospfArea.areaId());
        responseLsUpdate.setAuthType(OspfUtil.NOT_ASSIGNED);
        responseLsUpdate.setAuthentication(OspfUtil.NOT_ASSIGNED);
        responseLsUpdate.setOspfPacLength(OspfUtil.NOT_ASSIGNED); // to calculate packet length
        responseLsUpdate.setChecksum(OspfUtil.NOT_ASSIGNED);
        responseLsUpdate.setNumberOfLsa(1);
        responseLsUpdate.addLsa(lsa);

        //setting the destination.
        responseLsUpdate.setDestinationIp(destination);
        ch.write(responseLsUpdate);
    }