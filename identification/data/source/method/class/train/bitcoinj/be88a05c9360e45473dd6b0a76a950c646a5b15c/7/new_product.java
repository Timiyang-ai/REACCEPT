public Coin getMinNonDustValue(Coin feePerKb) {
        // "Dust" is defined in terms of dustRelayFee,
        // which has units satoshis-per-kilobyte.
        // If you'd pay more in fees than the value of the output
        // to spend something, then we consider it dust.
        // A typical spendable non-segwit txout is 34 bytes big, and will
        // need a CTxIn of at least 148 bytes to spend:
        // so dust is a spendable txout less than
        // 182*dustRelayFee/1000 (in satoshis).
        // 546 satoshis at the default rate of 3000 sat/kB.
        // A typical spendable segwit txout is 31 bytes big, and will
        // need a CTxIn of at least 67 bytes to spend:
        // so dust is a spendable txout less than
        // 98*dustRelayFee/1000 (in satoshis).
        // 294 satoshis at the default rate of 3000 sat/kB.
        long size = this.unsafeBitcoinSerialize().length;
        final Script script = getScriptPubKey();
        if (ScriptPattern.isP2PKH(script) || ScriptPattern.isP2PK(script) || ScriptPattern.isP2SH(script))
            size += 32 + 4 + 1 + 107 + 4; // 148
        else if (ScriptPattern.isP2WH(script))
            size += 32 + 4 + 1 + (107 / 4) + 4; // 68
        else
            return Coin.ZERO;
        return feePerKb.multiply(size).divide(1000);
    }