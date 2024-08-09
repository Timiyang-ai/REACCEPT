default Trade getLastTrade() {
    	List<Trade> trades = getTrades();
        if (!trades.isEmpty()) {
            return trades.get(trades.size() - 1);
        }
        return null;
    }