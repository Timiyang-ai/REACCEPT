from langchain.chains.conversation.base import ConversationChain

from util.llm.chat import Chat
from util.llm.memory import Memory
from util.llm.prompt import Prompt


class Chain:
    conversation = ConversationChain(prompt=Prompt.chat_template, llm=Chat.chat, memory=Memory.memory, verbose=True)


if __name__ == "__main__":
    # test
    conversation = Chain.conversation
    prompt = conversation.prompt

    print(conversation)
    print(prompt)

    print("Done")
