public Coin getMinNonDustValue(Coin feePerKb) {
        // A typical output is 33 bytes (pubkey hash + opcodes) and requires an input of 148 bytes to spend so we add
        // that together to find out the total amount of data used to transfer this amount of value. Note that this
        // formula is wrong for anything that's not a P2PKH output, unfortunately, we must follow Bitcoin Core's
        // wrongness in order to ensure we're considered standard. A better formula would either estimate the
        // size of data needed to satisfy all different script types, or just hard code 33 below.
        final long size = this.unsafeBitcoinSerialize().length + 148;
        return feePerKb.multiply(size).divide(1000);
    }