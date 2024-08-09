public void delete(final String name) throws QueryException {
    // find registered packages to be deleted
    boolean deleted = false;
    final EXPathRepo repo = context.repo;
    for(final Pkg pkg : packages()) {
      final String pkgPath = pkg.path();
      if(pkg.name().equals(name) || pkg.id().equals(name) || pkgPath.equals(name)) {
        if(pkg.type() == PkgType.EXPATH) {
          // check if package to be deleted participates in a dependency
          final String dep = dependency(pkg);
          if(dep != null) throw BXRE_DEP_X_X.get(info, dep, name);
          // delete files in main-memory repository
          repo.delete(pkg);
        }

        if(pkg.type() == PkgType.COMBINED) {
          // delete associated JAR file
          final IOFile pkgFile = repo.path(pkgPath.replaceAll("\\.[^.]+$", IO.JARSUFFIX));
          if(!pkgFile.delete()) throw BXRE_DELETE_X.get(info, pkgPath);
        }

        // delete package directory or file
        final IOFile pkgFile = repo.path(pkgPath);
        if(!pkgFile.delete()) throw BXRE_DELETE_X.get(info, pkgPath);

        // delete directory with extracted jars
        final IOFile extDir = pkgFile.parent().resolve('.' + pkg.name().replaceAll("^.*\\.", ""));
        if(!extDir.delete()) throw BXRE_DELETE_X.get(info, extDir);
        deleted = true;
      }
    }
    if(!deleted) throw BXRE_WHICH_X.get(info, name);
  }