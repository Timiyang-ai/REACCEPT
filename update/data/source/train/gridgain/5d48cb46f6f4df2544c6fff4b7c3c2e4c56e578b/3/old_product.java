public boolean activate() {
        return stateChangeReq != null && stateChangeReq.activeChanged() && stateChangeReq.activate();
    }