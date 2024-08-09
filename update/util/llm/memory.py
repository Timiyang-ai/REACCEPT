from langchain.memory import ConversationBufferWindowMemory


# from langchain.memory import ConversationTokenBufferMemory

# from model.chat import Chat


class Memory:
    # memory = ConversationBufferWindowMemory(return_messages=True, k=1)
    memory = ConversationBufferWindowMemory(return_messages=True, k=3)
    # memory = ConversationTokenBufferMemory(llm=Chat.chat, return_messages=True, max_token_limit=16000)
