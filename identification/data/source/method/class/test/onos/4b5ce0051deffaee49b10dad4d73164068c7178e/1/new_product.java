public void updateInterfaceMap(List<IsisProcess> isisProcesses) {
        for (IsisProcess isisUpdatedProcess : isisProcesses) {
            for (IsisInterface isisUpdatedInterface : isisUpdatedProcess.isisInterfaceList()) {
                IsisInterface isisInterface = isisInterfaceMap.get(isisUpdatedInterface.interfaceIndex());
                if (isisInterface == null) {
                    isisInterfaceMap.put(isisUpdatedInterface.interfaceIndex(), isisUpdatedInterface);
                    interfaceIps.add(isisUpdatedInterface.interfaceIpAddress());
                } else {
                    if (isisInterface.intermediateSystemName() != isisUpdatedInterface.intermediateSystemName()) {
                        isisInterface.setIntermediateSystemName(isisUpdatedInterface.intermediateSystemName());
                    }
                    if (isisInterface.reservedPacketCircuitType() != isisUpdatedInterface.reservedPacketCircuitType()) {
                        isisInterface.setReservedPacketCircuitType(isisUpdatedInterface.reservedPacketCircuitType());
                        isisInterface.removeNeighbors();
                    }
                    if (isisInterface.circuitId() != isisUpdatedInterface.circuitId()) {
                        isisInterface.setCircuitId(isisUpdatedInterface.circuitId());
                    }
                    if (isisInterface.networkType() != isisUpdatedInterface.networkType()) {
                        isisInterface.setNetworkType(isisUpdatedInterface.networkType());
                        isisInterface.removeNeighbors();
                    }
                    if (isisInterface.areaAddress() != isisUpdatedInterface.areaAddress()) {
                        isisInterface.setAreaAddress(isisUpdatedInterface.areaAddress());
                    }
                    if (isisInterface.holdingTime() != isisUpdatedInterface.holdingTime()) {
                        isisInterface.setHoldingTime(isisUpdatedInterface.holdingTime());
                    }
                    if (isisInterface.helloInterval() != isisUpdatedInterface.helloInterval()) {
                        isisInterface.setHelloInterval(isisUpdatedInterface.helloInterval());
                        isisInterface.stopHelloSender();
                        isisInterface.startHelloSender(channel);
                    }

                    isisInterfaceMap.put(isisInterface.interfaceIndex(), isisInterface);
                }
            }
        }
    }