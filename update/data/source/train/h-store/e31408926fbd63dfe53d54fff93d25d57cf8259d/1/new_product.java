public static DependencyGraph generate(CatalogContext catalogContext) {
        DependencyGraph dgraph = new DependencyGraph(catalogContext.database);
        DesignerInfo info = new DesignerInfo(catalogContext, new Workload(catalogContext.catalog));
        try {
            new DependencyGraphGenerator(info).generate(dgraph);
        } catch (Exception ex) {
            ex.printStackTrace();
            return (null);
        }
        return (dgraph);
    }