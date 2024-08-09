public boolean activate() {
        return stateChangeReq != null && stateChangeReq.activeChanged() && active(stateChangeReq.state());
    }