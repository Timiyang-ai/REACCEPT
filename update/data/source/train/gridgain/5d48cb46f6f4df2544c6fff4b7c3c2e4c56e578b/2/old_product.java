public boolean deactivate() {
        return stateChangeReq != null && stateChangeReq.activeChanged() && !stateChangeReq.activate();
    }