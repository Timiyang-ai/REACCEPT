public static Criterion matchOpticalSignalType(int sigType) {
        return new OpticalSignalTypeCriterion(sigType, Type.OCH_SIGTYPE);
    }