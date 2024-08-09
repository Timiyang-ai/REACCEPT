public static DependencyGraph generate(Database catalog_db) {
        DependencyGraph dgraph = new DependencyGraph(catalog_db);
        DesignerInfo info = new DesignerInfo(catalog_db, new Workload(catalog_db.getCatalog()));
        try {
            new DependencyGraphGenerator(info).generate(dgraph);
        } catch (Exception ex) {
            ex.printStackTrace();
            return (null);
        }
        return (dgraph);
    }