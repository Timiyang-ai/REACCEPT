void usage(boolean error) {
    PrintStream out = (error ? System.err : System.out);
    String name = "data-client";
    if (System.getProperty("script")!=null) name = System.getProperty("script").replaceAll("[./]", "");
    Copyright.print(out);
    out.println("Usage: ");
    out.println("  " + name + " read --key <string> [ <options> ]");
    out.println("  " + name + " write --key <string> --value value [ <options> ]");
    out.println("  " + name + " delete --key <string> [ <options> ]");
    out.println("  " + name + " list [ <options> ]");
    out.println("  " + name + " clear ( --all | --data | --queues | --streams | --tables | --meta)");
    out.println("  " + name + " clear ( --all | --queues | --streams | --tables | --meta)");
    out.println("Additional options:");
    out.println("  --base <url>            To specify the base url to send to");
    out.println("  --host <name>           To specify the hostname to send to");
    out.println("  --connector <name>      To specify the name of the rest connector");
    out.println("  --apikey <apikey>       To specify an API key for authentication");
    out.println("  --table <string>        To specify a table to operate on");
    out.println("  --key <string>          To specify the key");
    out.println("  --key-file <path>       To read the binary key from a file");
    out.println("  --value <string>        To specify the value");
    out.println("  --value-file <path>     To read/write the binary value from/to a file");
    out.println("  --hex                   To use hexadecimal encoding for key and value");
    out.println("  --ascii                 To use ASCII encoding for key and value");
    out.println("  --url                   To use URL encoding for key and value");
    out.println("  --counter               To interpret value as a long counter");
    out.println("  --start <n>             To start at the nth element - only for list");
    out.println("  --limit <k>             To list at most k elements - only for list");
    out.println("  --all                   To clear all data");
    out.println("  --data                  To clear all table data");
    out.println("  --streams               To clear all event streams");
    out.println("  --queues                To clear all intra-flow queues");
//    out.println("  --encoding <name>       To use this encoding for key and value");
    out.println("  --verbose               To see more verbose output");
    out.println("  --help                  To print this message");
    if (error) {
      throw new UsageException();
    }
  }