@Override
    public void analyze(Dependency dependency, Engine engine) throws AnalysisException {
        removeJreEntries(dependency);
        removeBadMatches(dependency);
        removeWrongVersionMatches(dependency);
        removeSpuriousCPE(dependency);
        addFalseNegativeCPEs(dependency);
    }