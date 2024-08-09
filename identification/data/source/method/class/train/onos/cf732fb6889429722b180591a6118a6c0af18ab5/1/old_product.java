public static Criterion matchSctpDst(int sctpPort) {
        return new SctpPortCriterion(sctpPort, Type.SCTP_DST);
    }