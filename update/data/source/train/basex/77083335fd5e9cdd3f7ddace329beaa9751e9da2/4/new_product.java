private boolean run(final Context ctx, final OutputStream os) {
    perf = new Performance();
    context = ctx;
    prop = ctx.prop;
    out = PrintOutput.get(os);

    try {
      return run();
    } catch(final ProgressException ex) {
      // process was interrupted by the user or server
      abort();
      return error(PROGERR);
    } catch(final Throwable ex) {
      // critical, unexpected error
      Performance.gc(2);
      abort();
      if(ex instanceof OutOfMemoryError) {
        Util.debug(ex);
        return error(PROCMEM + ((flags & (User.CREATE | User.WRITE)) != 0 ?
            PROCMEMCREATE : ""));
      }
      return error(Util.bug(Util.toArray(ex)));
    } finally {
      // flushes the output
      try { if(out != null) out.flush(); } catch(final IOException ex) { }
    }
  }