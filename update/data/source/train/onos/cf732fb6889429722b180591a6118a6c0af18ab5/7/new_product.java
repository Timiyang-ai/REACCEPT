public static Criterion matchSctpSrc(TpPort sctpPort) {
        return new SctpPortCriterion(sctpPort, Type.SCTP_SRC);
    }