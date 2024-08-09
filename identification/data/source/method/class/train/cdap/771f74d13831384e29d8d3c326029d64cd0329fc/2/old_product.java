void usage(boolean error) {
    PrintStream out = (error ? System.err : System.out);
    String name = "data-client";
    if (System.getProperty("script")!=null) name = System.getProperty("script").replaceAll("[./]", "");
    Copyright.print(out);
    out.println("Usage: ");
    out.println("  " + name + " clear ( --all | --queues | --streams | --tables | --meta)");
    out.println("Additional options:");
    out.println("  --host <name>           To specify the hostname to send to");
    out.println("  --port <number>         To specify the port to use");
    out.println("  --apikey <apikey>       To specify an API key for authentication");
    out.println("  --all                   To clear all data");
    out.println("  --data                  To clear all table data");
    out.println("  --streams               To clear all event streams");
    out.println("  --queues                To clear all intra-flow queues");
    out.println("  --verbose               To see more verbose output");
    out.println("  --help                  To print this message");
    if (error) {
      throw new UsageException();
    }
  }