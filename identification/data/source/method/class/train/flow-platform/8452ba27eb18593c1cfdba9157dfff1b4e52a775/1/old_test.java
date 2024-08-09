    private Cmd send(CmdInfo info) {
        Cmd cmd = cmdService.create(info);
        cmdDispatchService.dispatch(cmd);
        return cmdService.find(cmd.getId());
    }