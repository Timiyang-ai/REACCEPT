public boolean deactivate() {
        return stateChangeReq != null && stateChangeReq.activeChanged() && !active(stateChangeReq.state());
    }