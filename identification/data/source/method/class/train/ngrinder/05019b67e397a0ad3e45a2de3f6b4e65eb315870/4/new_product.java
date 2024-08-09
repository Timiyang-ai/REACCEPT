public void shutdown() {
		if (m_timer != null) {
			m_timer.cancel();
		}
		if (m_fanOutStreamSender != null) {
			m_fanOutStreamSender.shutdown();
		}
		m_agentControllerServerListener.shutdown();
		LOGGER.info("finished");
	}