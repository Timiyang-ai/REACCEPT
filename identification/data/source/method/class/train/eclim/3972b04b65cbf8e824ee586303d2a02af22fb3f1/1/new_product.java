public static String execute (String[] _args, long _timeout)
  {
    String[] args = new String[_args.length + 2];
    System.arraycopy(_args, 0, args, 2, _args.length);
    args[0] = ECLIM;
    args[1] = COMMAND;

    System.out.println("Command: " + StringUtils.join(args, ' '));

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
      System.out.println("OUT: " + process.getResult());
      System.out.println("ERR: " + process.getErrorMessage());
      throw new RuntimeException("Command failed: " + process.getReturnCode());
    }

    String result = process.getResult();

    // strip off trailing newline char and return
    return result.substring(0, result.length() - 1);
  }