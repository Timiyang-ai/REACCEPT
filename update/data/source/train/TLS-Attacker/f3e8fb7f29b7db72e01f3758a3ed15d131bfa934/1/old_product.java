public static ProtocolVersion gethighestProtocolVersion(List<ProtocolVersion> list) {
        ProtocolVersion highestProtocolVersion = list.get(0);
        for (ProtocolVersion pv : list) {
            if (ArrayConverter.bytesToInt(pv.getValue()) > ArrayConverter.bytesToInt(highestProtocolVersion.getValue())) {
                highestProtocolVersion = pv;
            }
        }
        return highestProtocolVersion;
    }