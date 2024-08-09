public static Criterion matchUdpDst(Short udpPort) {
        return new UdpPortCriterion(udpPort, Type.UDP_DST);
    }