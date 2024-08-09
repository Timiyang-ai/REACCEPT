public void addToOtherNeighborLsaTxList(LsaHeader recLsa) {
        //Add the received LSA in other neighbors retransmission list.
        log.debug("OspfAreaImpl: addToOtherNeighborLsaTxList");
        List<OspfInterface> ospfInterfaces = getInterfacesLst();
        for (OspfInterface ospfInterfaceFromArea : ospfInterfaces) {
            Map neighbors = ospfInterfaceFromArea.listOfNeighbors();
            for (Object neighborIP : neighbors.keySet()) {
                OspfNbrImpl nbr = (OspfNbrImpl) neighbors.get(neighborIP);
                if (nbr.getState().getValue() < OspfNeighborState.EXCHANGE.getValue()) {
                    continue;
                }
                String key = database.getLsaKey(recLsa);
                if (nbr.getState() == OspfNeighborState.EXCHANGE || nbr.getState() == OspfNeighborState.LOADING) {
                    if (nbr.getLsReqList().containsKey(key)) {
                        LsaWrapper lsWrapper = lsaLookup(recLsa);
                        if (lsWrapper != null) {
                            LsaHeader ownLsa = (LsaHeader) lsWrapper.ospfLsa();
                            String status = isNewerOrSameLsa(recLsa, ownLsa);
                            if (status.equals("old")) {
                                continue;
                            } else if (status.equals("same")) {
                                log.debug("OspfAreaImpl: addToOtherNeighborLsaTxList: " +
                                                  "Removing lsa from reTxtList {}", key);
                                nbr.getLsReqList().remove(key);
                                continue;
                            } else {
                                log.debug("OspfAreaImpl: addToOtherNeighborLsaTxList: " +
                                                  "Removing lsa from reTxtList {}", key);
                                nbr.getLsReqList().remove(key);
                            }
                        }
                    }
                }
                if (recLsa.advertisingRouter().equals((String) neighborIP)) {
                    continue;
                }
                if ((recLsa.lsType() == OspfParameters.LINK_LOCAL_OPAQUE_LSA ||
                        recLsa.lsType() == OspfParameters.AREA_LOCAL_OPAQUE_LSA)) {
                    if (nbr.isOpaqueCapable()) {
                        log.debug("OspfAreaImpl: addToOtherNeighborLsaTxList: Adding lsa to reTxtList {}",
                                  recLsa);
                        nbr.getReTxList().put(key, recLsa);
                    }
                } else {
                    log.debug("OspfAreaImpl: addToOtherNeighborLsaTxList: Adding lsa to reTxtList {}",
                              recLsa);
                    nbr.getReTxList().put(key, recLsa);
                }
            }
        }
    }