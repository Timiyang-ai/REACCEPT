public T cacheItem(T cacheData)
	{
		LinkItem<T> mappedItem = null;

		synchronized (m_lock)
		{
			LinkItem<T> li = new LinkItem<T>(cacheData);
			mappedItem = m_hashMap.putIfAbsent(cacheData, li);

			if (mappedItem != null)
			{
				remove(mappedItem);
				addItem(mappedItem);
			}
			else
				addItem(li);

			if (m_hashMap.size() > m_maxSize)
			{
				LinkItem<T> last = m_back.m_prev;
				remove(last);

				m_hashMap.remove(last.m_data);
			}
		}

		return (mappedItem == null ? null : mappedItem.m_data);
	}