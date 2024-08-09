static public void setAgentPatterns(List<AgentPatternList> agentPatternLists)
    {
        clearAgentPatterns();

        for (AgentPatternList agentPatterns : agentPatternLists)
            for (String agentPattern : agentPatterns.getPatterns())
            {
                Pattern newPattern = Pattern.compile(agentPattern);
                agents.add(newPattern);
            }
        log.info("Received " + String.valueOf(agents.size()) + " agent patterns.");
    }