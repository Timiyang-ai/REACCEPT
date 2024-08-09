public static Criterion matchTcpDst(Short tcpPort) {
        return new TcpPortCriterion(tcpPort, Type.TCP_DST);
    }