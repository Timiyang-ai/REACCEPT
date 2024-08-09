public void delete(final String pkg) throws QueryException {
    boolean found = false;
    final Repo repo = context.repo;
    final TokenMap dict = repo.pkgDict();
    final byte[] pp = token(pkg);
    for(final byte[] nextPkg : dict) {
      if(nextPkg == null) continue;
      // a package can be deleted by its name or the name suffixed with its version
      if(eq(nextPkg, pp) || eq(Package.name(nextPkg), pp)) {
        // check if package to be deleted participates in a dependency
        final byte[] primPkg = primary(nextPkg);
        if(primPkg != null) throw BXRE_DEP_X_X.get(info, string(primPkg), pkg);

        // clean package repository
        final IOFile f = repo.path(string(dict.get(nextPkg)));
        repo.delete(new PkgParser(info).parse(new IOFile(f, DESCRIPTOR)));
        // package does not participate in a dependency => delete it
        if(!f.delete()) throw BXRE_DELETE_X.get(info, f);
        found = true;
      }
    }

    // traverse all files
    final IOFile file = file(pkg, repo);
    if(file != null) {
      if(!file.delete()) throw BXRE_DELETE_X.get(info, file);
      return;
    }

    if(!found) throw BXRE_WHICH_X.get(info, pkg);
  }