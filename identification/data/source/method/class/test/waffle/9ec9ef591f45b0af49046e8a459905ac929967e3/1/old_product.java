public boolean isSPNegoMessage() {

        if (this.isNull()) {
            return false;
        }

        final byte[] tokenBytes = this.getTokenBytes();
        return SPNegoMessage.isSPNegoMessage(tokenBytes);
    }