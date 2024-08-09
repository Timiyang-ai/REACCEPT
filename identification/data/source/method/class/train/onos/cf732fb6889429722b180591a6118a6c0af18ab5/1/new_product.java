public static Criterion matchSctpDst(TpPort sctpPort) {
        return new SctpPortCriterion(sctpPort, Type.SCTP_DST);
    }