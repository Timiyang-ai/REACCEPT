public void delete(final String pkg, final InputInfo ii) throws QueryException {
    boolean found = false;
    for(final byte[] nextPkg : repo.pkgDict()) {
      if(nextPkg == null) continue;

      final byte[] dir = repo.pkgDict().get(nextPkg);
      if(eq(Package.name(nextPkg), token(pkg)) || eq(dir, token(pkg))) {
        // a package can be deleted either by its name or by its directory name
        found = true;
        // check if package to be deleted participates in a dependency
        final byte[] primPkg = primary(nextPkg, ii);
        if(primPkg != null) PKGDEP.thrw(ii, string(primPkg), pkg);

        // clean package repository
        final IOFile f = repo.path(string(dir));
        final IOFile desc = new IOFile(f, DESCRIPTOR);
        repo.remove(new PkgParser(repo, ii).parse(desc));
        // package does not participate in a dependency => delete it
        if(!f.delete()) CANNOTDELPKG.thrw(ii);
      }
    }
    if(!found) PKGNOTEXIST.thrw(ii, pkg);
  }