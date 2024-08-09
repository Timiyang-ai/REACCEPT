public void delete(final byte[] pkg) throws QueryException {
    boolean found = false;
    final TokenMap dict = repo.pkgDict();
    for(final byte[] nextPkg : dict) {
      if(nextPkg == null) continue;
      // a package can be deleted by its name or the name suffixed with its version
      if(eq(nextPkg, pkg) || eq(Package.name(nextPkg), pkg)) {
        // check if package to be deleted participates in a dependency
        final byte[] primPkg = primary(nextPkg);
        if(primPkg != null) BXRE_DEP.thrw(info, string(primPkg), pkg);

        // clean package repository
        final IOFile f = repo.path(string(dict.get(nextPkg)));
        repo.delete(new PkgParser(repo, info).parse(new IOFile(f, DESCRIPTOR)));
        // package does not participate in a dependency => delete it
        if(!f.delete()) BXRE_DELETE.thrw(info, f);
        found = true;
      }
    }

    // traverse all files
    final IOFile file = file(pkg, repo);
    if(file != null) {
      if(!file.delete()) BXRE_DELETE.thrw(info, file);
      return;
    }

    if(!found) BXRE_WHICH.thrw(info, pkg);
  }