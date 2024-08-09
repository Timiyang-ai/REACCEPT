public MutableSparseVector alwaysAddChannel(Symbol channelSymbol) {
        MutableSparseVector chan = channelMap.get(channelSymbol);
        if (chan == null) {
            chan = addChannel(channelSymbol);
        }
        return chan;
    }