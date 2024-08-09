public static Criterion matchTcpDst(int tcpPort) {
        return new TcpPortCriterion(tcpPort, Type.TCP_DST);
    }