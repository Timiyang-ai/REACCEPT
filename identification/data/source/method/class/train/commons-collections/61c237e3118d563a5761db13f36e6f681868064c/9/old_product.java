public boolean containsKey( final Object key )
    {
        int hash = getHash( key );

        synchronized( m_locks[ hash ] )
        {
            Node n = m_buckets[ hash ];

            while( n != null )
            {
                if( n.key == null || ( n.key != null && n.key.equals( key ) ) )
                {
                    return true;
                }

                n = n.next;
            }
        }

        return false;
    }