public void updateInterfaceMap(List<IsisProcess> isisProcesses) {
        for (IsisProcess isisUpdatedProcess : isisProcesses) {
            for (IsisInterface isisUpdatedInterface : isisUpdatedProcess.isisInterfaceList()) {
                IsisInterface isisInterface = isisInterfaceMap.get(isisUpdatedInterface.interfaceIndex());
                if (isisInterface == null) {
                    isisInterfaceMap.put(isisInterface.interfaceIndex(), isisInterface);
                    interfaceIps.add(isisInterface.interfaceIpAddress());
                } else {
                    isisInterface.setReservedPacketCircuitType(isisUpdatedInterface.reservedPacketCircuitType());
                    isisInterface.setNetworkType(isisUpdatedInterface.networkType());
                    isisInterface.setHoldingTime(isisUpdatedInterface.holdingTime());
                    isisInterface.setHelloInterval(isisUpdatedInterface.helloInterval());
                    isisInterfaceMap.put(isisInterface.interfaceIndex(), isisInterface);
                }
            }
        }
    }