import os

from langchain_openai import ChatOpenAI


class Chat:
    os.environ["OPENAI_API_KEY"] = "your openai api key here"
    os.environ["OPENAI_API_BASE"] = "your openai api proxy here"

    # chat = ChatOpenAI(model_name="gpt-3.5-turbo-0125", temperature=0)
    chat = ChatOpenAI(model_name="gpt-4-0125-preview", temperature=0)
    # chat = ChatOpenAI(model_name="gpt-4-turbo-2024-04-09", temperature=0)
    # chat = ChatOpenAI(model_name="gpt-4o-2024-05-13", temperature=0)

    # chat = ChatOpenAI(model_name="gpt-4-0125-preview", temperature=0.5)
    # chat = ChatOpenAI(model_name="gpt-4-0125-preview", temperature=1)

    # chat = ChatOpenAI(model_name="gpt-4-0125-preview", temperature=0, model_kwargs={"top_p": 0})
    # chat = ChatOpenAI(model_name="gpt-4-0125-preview", temperature=0, model_kwargs={"top_p": 0.5})


if __name__ == "__main__":
    # test
    chat = Chat.chat

    print(chat)

    print("Done")
