public void delete(final String pkg) throws QueryException {
    boolean found = false;
    final TokenMap dict = repo.pkgDict();
    for(final byte[] nextPkg : dict) {
      if(nextPkg == null) continue;
      final byte[] dir = dict.get(nextPkg);

      // a package can be deleted either by its name or by its directory name
      if(eq(Package.name(nextPkg), token(pkg)) || eq(dir, token(pkg))) {
        // check if package to be deleted participates in a dependency
        final byte[] primPkg = primary(nextPkg);
        if(primPkg != null) PKGDEP.thrw(info, string(primPkg), pkg);

        // clean package repository
        final IOFile f = repo.path(string(dir));
        final IOFile desc = new IOFile(f, DESCRIPTOR);
        repo.remove(new PkgParser(repo, info).parse(desc));
        // package does not participate in a dependency => delete it
        if(!f.delete()) PKGDEL.thrw(info, f);
        found = true;
      }
    }

    // traverse all files
    final IOFile file = file(pkg, repo);
    if(file != null) {
      if(!file.delete()) PKGDEL.thrw(info, file);
      return;
    }

    if(!found) PKGNOTEXIST.thrw(info, pkg);
  }