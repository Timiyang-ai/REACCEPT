public void delete(final String name) throws QueryException {
    // find registered packages to be deleted
    final EXPathRepo repo = context.repo;
    final ArrayList<Pkg> delete = new ArrayList<>();
    for(final Pkg pkg : repo.pkgDict().values()) {
      // a package can be deleted by its name or the name suffixed with its version
      if(pkg.name().equals(name) || pkg.id().equals(name)) {
        // check if package to be deleted participates in a dependency
        final String dep = primary(pkg);
        if(dep != null) throw BXRE_DEP_X_X.get(info, dep, name);
        delete.add(pkg);
      }
    }

    // delete registered packages
    for(final Pkg pkg : delete) {
      repo.delete(pkg);
      // delete files on disk
      final IOFile dir = repo.path(pkg.dir());
      if(!dir.delete()) throw BXRE_DELETE_X.get(info, dir);
    }

    // delete internal package
    final IOFile file = find(name);
    if(file != null) {
      if(!file.delete()) throw BXRE_DELETE_X.get(info, file);
      return;
    }

    if(delete.isEmpty()) throw BXRE_WHICH_X.get(info, name);
  }