import os

from langchain_community.document_loaders import TextLoader
from langchain_community.vectorstores import Chroma
from langchain_openai import OpenAIEmbeddings

from util.tool.diff import Diff


class KnowledgeBase:
    # diff = "data/diff"
    diff = "data/diff/train"

    knowledgebase = "data/knowledge"

    os.environ["OPENAI_API_KEY"] = "your openai api key here"
    os.environ["OPENAI_API_BASE"] = "your openai api proxy here"

    embedding_model = OpenAIEmbeddings()

    @classmethod
    def build(cls):
        diff_documents = []

        projects = os.listdir(Diff.diff)
        total_projects = len(projects)
        # print(total_projects)

        count_projects = 0
        for project in projects:
            commits = os.listdir("/".join([Diff.diff, project]))
            total_commits = len(commits)
            # print("\t", total_commits)

            count_commits = 0
            for commit in commits:
                numbers = os.listdir("/".join([Diff.diff, project, commit]))
                total_numbers = len(numbers)
                # print("\t\t", total_numbers)

                count_numbers = 0
                for number in numbers:
                    loader = TextLoader(
                        "/".join([Diff.diff, project, commit, number, "diff_product.diff"]),
                        "utf-8")
                    diff_documents.append(loader.load()[0])
                    # diff_documents.extend(loader.load())
                    # diff_documents += loader.load()

                    count_numbers += 1
                    print("\t\t\t", count_numbers, "/", total_numbers)
                count_commits += 1
                print("\t\t", count_commits, "/", total_commits)
            count_projects += 1
            print("\t", count_projects, "/", total_projects)
        # print(diff_documents)
        # print(len(diff_documents))
        # print(sys.getsizeof(diff_documents))

        vector_db = Chroma.from_documents(
            documents=diff_documents,
            embedding=cls.embedding_model,
            persist_directory=cls.knowledgebase)
        vector_db.persist()

    @classmethod
    def retrieve(cls, diff):
        loader = TextLoader(
            "/".join([diff.diff, diff.project, diff.commit, diff.number, "diff_product.diff"]),
            "utf-8")
        diff_document = loader.load()

        vector = cls.embedding_model.embed_query(diff_document[0].page_content)

        retriever = Chroma(
            embedding_function=cls.embedding_model,
            persist_directory=cls.knowledgebase)
        sample = retriever.similarity_search_by_vector_with_relevance_scores(vector, k=1)

        distance = sample[0][1]

        sample_diff = cls.diff

        sample_project = sample[0][0].metadata["source"].split("/")[-4]
        sample_commit = sample[0][0].metadata["source"].split("/")[-3]
        sample_number = sample[0][0].metadata["source"].split("/")[-2]

        print(distance)
        print(sample_diff)
        print(sample_project)
        print(sample_commit)
        print(sample_number)

        diff_sample = Diff()
        diff_sample.diff = sample_diff
        diff_sample.load(sample_project, sample_commit, sample_number)

        return diff_sample


if __name__ == "__main__":
    # build
    KnowledgeBase.build()
