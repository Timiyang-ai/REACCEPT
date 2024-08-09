@Override
    protected synchronized void analyzeDependency(Dependency ignore, Engine engine) throws AnalysisException {
        if (!analyzed) {
            analyzed = true;
            final Set<Dependency> dependenciesToRemove = new HashSet<>();

            final Dependency[] dependencies = engine.getDependencies();
            if (dependencies.length < 2) {
                return;
            }
            for (int x = 0; x < dependencies.length - 1; x++) {
                final Dependency dependency = dependencies[x];
                if (!dependenciesToRemove.contains(dependency)) {
                    for (int y = x + 1; y < dependencies.length; y++) {
                        final Dependency nextDependency = dependencies[y];
                        if (evaluateDependencies(dependency, nextDependency, dependenciesToRemove)) {
                            break;
                        }
                    }
                }
            }
            dependenciesToRemove.forEach((d) -> {
                engine.removeDependency(d);
            });
        }
    }