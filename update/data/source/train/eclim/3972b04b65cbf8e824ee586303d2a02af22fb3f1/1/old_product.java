public static String execute (String[] _args, long _timeout)
  {
    String[] args = new String[_args.length + 1];
    System.arraycopy(_args, 0, args, 1, _args.length);
    args[0] = ECLIM;

    CommandExecutor process = null;
    try{
      process = CommandExecutor.execute(args, _timeout);
    }catch(Exception e){
      throw new RuntimeException(e);
    }

    if(process.getReturnCode() == -1){
      process.destroy();
      throw new RuntimeException("Command timed out.");
    }

    if(process.getReturnCode() != 0){
      System.out.println("ERR: " + process.getErrorMessage());
      System.out.println("OUT: " + process.getResult());
      throw new RuntimeException("Command failed: " + process.getReturnCode());
    }

    String result = process.getResult();

    // strip off trailing newline char and return
    return result.substring(0, result.length() - 1);
  }