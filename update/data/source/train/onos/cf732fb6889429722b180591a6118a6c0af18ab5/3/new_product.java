public static Criterion matchTcpDst(TpPort tcpPort) {
        return new TcpPortCriterion(tcpPort, Type.TCP_DST);
    }