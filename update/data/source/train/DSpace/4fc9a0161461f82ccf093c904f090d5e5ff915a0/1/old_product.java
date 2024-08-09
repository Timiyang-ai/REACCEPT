static public void setAgentPatterns(List<String> agentPatterns)
    {
        if (null == agents)
            agents = new ArrayList<Pattern>(agentPatterns.size());

        int nPatterns = 0;
        for (String agentPattern : agentPatterns)
        {
            Pattern newPattern = Pattern.compile(agentPattern);
            agents.add(newPattern);
            nPatterns++;
        }
        log.info("Received " + String.valueOf(nPatterns) + " agent patterns.");
    }