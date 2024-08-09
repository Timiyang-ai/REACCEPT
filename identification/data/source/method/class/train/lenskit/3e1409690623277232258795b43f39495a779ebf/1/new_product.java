public MutableSparseVector getOrAddChannel(Symbol channelSymbol) {
        MutableSparseVector chan = channelMap.get(channelSymbol);
        if (chan == null) {
            chan = addChannel(channelSymbol);
        }
        return chan;
    }