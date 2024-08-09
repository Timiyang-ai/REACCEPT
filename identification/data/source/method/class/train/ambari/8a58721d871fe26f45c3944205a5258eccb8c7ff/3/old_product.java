public boolean isSupportedStack(String stackName, String version) {
        boolean exist = false;
        StackInfo stack = getStackInfo(stackName, version);
        if (stack == null)
            exist = true;
        return exist;
    }