public void suspend() {
        if (suspendedFrom.equals(Quantum.SUSPENDED)) suspendedFrom = getInterval();
        setInterval(Quantum.SUSPENDED);
    }