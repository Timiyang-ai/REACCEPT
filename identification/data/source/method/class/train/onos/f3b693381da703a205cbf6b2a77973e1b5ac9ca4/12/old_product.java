public static Criterion matchSctpDst(Short sctpPort) {
        return new SctpPortCriterion(sctpPort, Type.SCTP_DST);
    }