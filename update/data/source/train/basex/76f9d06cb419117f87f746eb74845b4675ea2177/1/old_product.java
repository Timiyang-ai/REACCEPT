private boolean run(final Context ctx, final OutputStream os) {
    perf = new Performance();
    context = ctx;
    prop = ctx.prop;
    mprop = ctx.mprop;
    out = PrintOutput.get(os);

    try {
      return run();
    } catch(final ProgressException ex) {
      // process was interrupted by the user or server
      abort();
      return error(INTERRUPTED);
    } catch(final Throwable ex) {
      // unexpected error
      Performance.gc(2);
      abort();
      if(ex instanceof OutOfMemoryError) {
        Util.debug(ex);
        return error(OUT_OF_MEM + (createWrite() ? H_OUT_OF_MEM : ""));
      }
      return error(Util.bug(ex) + NL + info.toString());
    } finally {
      // flushes the output
      try { if(out != null) out.flush(); } catch(final IOException ex) { }
    }
  }