private void set(final Options options, final Option opt, final Object val) {
   if(!options.sameAs(opt, val)) {
     final Set cmd = new Set(opt, val);
     cmd.run(context);
     info.setInfo(cmd.info(), cmd, true, false);
   }
 }