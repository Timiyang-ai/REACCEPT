public boolean noNeighborInLsaExchangeProcess() {
        OspfInterfaceImpl nextInterface;
        OspfNeighborState nextNeighborState;
        Iterator interfaces = ospfInterfaceList.iterator();
        while (interfaces.hasNext()) {
            nextInterface = (OspfInterfaceImpl) interfaces.next();
            Iterator neighbors = nextInterface.listOfNeighbors().values().iterator();
            while (neighbors.hasNext()) {
                nextNeighborState = ((OspfNbrImpl) neighbors.next()).getState();
                if (nextNeighborState == OspfNeighborState.EXCHANGE ||
                        nextNeighborState == OspfNeighborState.LOADING) {
                    return false;
                }
            }
        }
        return true;
    }