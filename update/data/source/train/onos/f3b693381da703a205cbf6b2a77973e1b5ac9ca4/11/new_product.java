public static Criterion matchUdpDst(int udpPort) {
        return new UdpPortCriterion(udpPort, Type.UDP_DST);
    }