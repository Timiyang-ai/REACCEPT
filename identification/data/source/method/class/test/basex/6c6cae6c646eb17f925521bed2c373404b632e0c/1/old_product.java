private boolean backup(final String db, final Prop pr) {
    ZipOutputStream zos = null;
    try {
      final File in = pr.dbpath(db);
      final File file = new File(pr.get(Prop.DBPATH) + '/' + db + "-" +
          DATE.format(new Date()) + IO.ZIPSUFFIX);
      final byte[] data = new byte[IO.BLOCKSIZE];

      // OutputStream for zipping
      zos = new ZipOutputStream(new BufferedOutputStream(
          new FileOutputStream(file)));
      zos.putNextEntry(new ZipEntry(in.getName() + '/'));
      zos.closeEntry();

      // Process each file
      final File[] files = in.listFiles();
      tf = files.length;
      for(final File f : files) {
        of++;
        BufferedInputStream bis = null;
        try {
          bis = new BufferedInputStream(new FileInputStream(f), IO.BLOCKSIZE);
          zos.putNextEntry(new ZipEntry(in.getName() + '/' + f.getName()));
          int c;
          while((c = bis.read(data)) != -1) zos.write(data, 0, c);
          zos.closeEntry();
        } finally {
          if(bis != null) try { bis.close(); } catch(final IOException e) { }
        }
      }
      zos.close();
      return true;
    } catch(final IOException ex) {
      return false;
    } finally {
      if(zos != null) try { zos.close(); } catch(final IOException e) { }
    }
  }