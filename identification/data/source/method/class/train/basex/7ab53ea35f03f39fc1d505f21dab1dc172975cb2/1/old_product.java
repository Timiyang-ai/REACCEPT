public void delete(final String pkg, final InputInfo ii)
      throws QueryException {
    boolean found = false;
    for(final byte[] nextPkg : repo.pkgDict()) {
      if(nextPkg != null) {
        final byte[] dir = repo.pkgDict().get(nextPkg);
        if(eq(Package.name(nextPkg), token(pkg)) || eq(dir, token(pkg))) {
          // A package can be deleted either by its name or by its directory
          // name
          found = true;
          // check if package to be deleted participates in a dependency
          final byte[] primPkg = primary(nextPkg, ii);
          if(primPkg == null) {
            // clean package repository
            final IOFile f = repo.path(string(dir));
            final IOFile desc = new IOFile(f, DESCRIPTOR);
            repo.remove(new PkgParser(repo, ii).parse(desc));
            // package does not participate in a dependency => delete it
            if(!f.delete()) CANNOTDELPKG.thrw(ii);
          } else PKGDEP.thrw(ii, string(primPkg), pkg);
        }
      }
    }
    if(!found) PKGNOTINST.thrw(ii, pkg);
  }