public void shutdown() {
		if (m_timer != null) {
			m_timer.cancel();
		}
		m_fanOutStreamSender.shutdown();
		m_agentControllerServerListener.shutdown();
		m_logger.info("finished");
	}