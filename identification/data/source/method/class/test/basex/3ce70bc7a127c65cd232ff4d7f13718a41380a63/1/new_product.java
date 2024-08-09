public void delete(final String pkg, final InputInfo ii)
      throws QueryException {
    boolean found = false;
    for(final byte[] nextPkg : ctx.repo.pkgDict()) {
      if(nextPkg != null) {
        final byte[] dir = ctx.repo.pkgDict().get(nextPkg);
        if(eq(Package.getName(nextPkg), token(pkg)) || eq(dir, token(pkg))) {
          // A package can be deleted either by its name or by its directory
          // name
          found = true;
          // Check if package to be deleted participates in a dependency
          final byte[] primPkg = getPrimary(nextPkg, ii);
          if(primPkg == null) {
            // Clean package repository
            final File f = new File(ctx.prop.get(Prop.REPOPATH), string(dir));
            final File desc = new File(f, DESCRIPTOR);
            ctx.repo.remove(new PkgParser(ctx, ii).parse(new IOFile(desc)));
            // Package does not participate in a dependency => delete it
            deleteFromDisc(f, ii);
          } else PKGDEP.thrw(ii, string(primPkg), pkg);
        }
      }
    }
    if(!found) PKGNOTINST.thrw(ii, pkg);
  }