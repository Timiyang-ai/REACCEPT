public boolean isSPNegTokenInitMessage() {

        if (this.isNull()) {
            return false;
        }

        final byte[] tokenBytes = this.getTokenBytes();
        return SPNegoMessage.isNegTokenInit(tokenBytes);
    }