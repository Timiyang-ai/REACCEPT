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
      // information on a critical error is output
      Performance.gc(3);
      abort();
      if(ex instanceof OutOfMemoryError) {
        Util.stack(ex);
        return error(PROCMEM +
            ((flags & User.CREATE) != 0 ? PROCMEMCREATE : ""));
      }

      Util.debug(ex);
      final Object[] st = ex.getStackTrace();
      final Object[] obj = new Object[st.length + 1];
      obj[0] = ex.toString();
      System.arraycopy(st, 0, obj, 1, st.length);
      return error(Util.bug(obj));
    } finally {
      // flushes the output
      try { if(out != null) out.flush(); } catch(final IOException ex) { }
    }
  }