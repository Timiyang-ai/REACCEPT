@Override
    public void analyze(Dependency dependency, Engine engine) throws AnalysisException {
        removeJreEntries(dependency);
        removeBadMatches(dependency);
        removeWrongVersionMatches(dependency);
        removeSpuriousCPE(dependency);
        removeDuplicativePOMEntries(dependency, engine);
        addFalseNegativeCPEs(dependency);
    }