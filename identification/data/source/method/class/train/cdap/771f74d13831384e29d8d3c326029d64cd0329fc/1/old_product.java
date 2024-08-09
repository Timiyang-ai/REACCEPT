void usage(boolean error) {
    PrintStream out = (error ? System.err : System.out);
    String name = "stream-client";
    if (System.getProperty("script") != null) {
      name = System.getProperty("script").replaceAll("[./]", "");
    }
    Copyright.print(out);
    out.println("Usage: ");
    out.println("  " + name + " create --stream <id>");
    out.println("  " + name + " send --stream <id> --body <value> [ <option> ... ]");
    out.println("  " + name + " group --stream <id> [ <option> ... ]");
    out.println("  " + name + " fetch --stream <id> --group <id> [ <option> ... ]");
    out.println("  " + name + " view --stream <id> [ <option> ... ]");
    out.println("  " + name + " info --stream <id> [ <option> ... ]");
    out.println("Options:");
    out.println("  --base <url>            To specify the base URL to use");
    out.println("  --host <name>           To specify the hostname to send to");
    out.println("  --port <number>         To specify the port to use");
    out.println("  --connector <name>      To specify the name of the rest collector");
    out.println("  --apikey <apikey>       To specify an API key for authentication");
    out.println("  --stream <id>           To specify the destination event stream of the");
    out.println("                          form <flow> or <flow>/<stream>.");
    out.println("  --header <name> <value> To specify a header for the event to send. Can");
    out.println("                          be used multiple times");
    out.println("  --body <value>          To specify the body of the event as a string");
    out.println("  --body-file <path>      Alternative to --body, to specify a file that");
    out.println("                          contains the binary body of the event");
    out.println("  --hex                   To specify hexadecimal encoding for --body");
    out.println("  --url                   To specify url encoding for --body");
    out.println("  --group <id>            To specify a consumer group id for the stream, as ");
    out.println("                          obtained by " + name + " group command ");
    out.println("  --all                   To view the entire stream.");
    out.println("  --first <number>        To view the first N events in the stream. Default ");
    out.println("                          for view is --first 10.");
    out.println("  --last <number>         To view the last N events in the stream.");
    out.println("  --verbose               To see more verbose output");
    out.println("  --help                  To print this message");
    if (error) {
      throw new UsageException();
    }
  }