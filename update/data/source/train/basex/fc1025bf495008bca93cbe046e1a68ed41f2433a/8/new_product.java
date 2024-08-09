private static void execute(final GUI gui, final BaseXDialog dialog, final Runnable post,
                              final Command... cmds) {

    for(final Command cmd : cmds) {
      // reset views
      final boolean newData = cmd.newData(gui.context);
      if(newData) gui.notify.init();

      // create wait dialog
      final DialogProgress wait = dialog != null ? new DialogProgress(dialog, cmd) :
        new DialogProgress(gui, cmd);

      // start command thread
      new Thread() {
        @Override
        public void run() {
          // execute command
          final Performance perf = new Performance();
          gui.updating = cmd.updating(gui.context);
          boolean ok = true;
          String info;
          try {
            cmd.execute(gui.context);
            info = cmd.info();
          } catch(final BaseXException ex) {
            ok = false;
            info = Util.message(ex);
          } finally {
            gui.updating = false;
          }

          // return status information
          final String time = perf.toString();
          gui.info.setInfo(info, cmd, time, ok, true);
          gui.status.setText(Util.info(TIME_NEEDED_X, time));

          // close progress window and show error if command failed
          wait.dispose();
          if(!ok) BaseXDialog.error(gui, info.equals(INTERRUPTED) ? COMMAND_CANCELED : info);
        }
      }.start();

      // show progress windows until being disposed
      wait.setVisible(true);

      // initialize views if database was closed before
      if(newData) gui.notify.init();
      else if(cmd.updating(gui.context)) gui.notify.update();
    }
    if(dialog != null) dialog.action(dialog);
    if(post != null) post.run();
  }