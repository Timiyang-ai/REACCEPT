import os
import re
from langchain.chains import ConversationChain
from langchain_openai import ChatOpenAI
from langchain_community.document_loaders import TextLoader
from langchain_community.embeddings import HuggingFaceEmbeddings
from langchain_community.vectorstores import Chroma
from pathlib import Path
from langchain.memory import ConversationBufferWindowMemory
from langchain.prompts import ChatPromptTemplate
from langchain.schema.messages import HumanMessage, SystemMessage
from langchain_core.prompts import HumanMessagePromptTemplate, MessagesPlaceholder
from collections import OrderedDict
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

# 环境设定
os.environ["OPENAI_API_KEY"] = "your openai api key here"
os.environ["OPENAI_API_BASE"] = "your openai api proxy here"


def read_file(file_path):
    with open(file_path, 'r', encoding='utf-8') as file:
        return file.read()


def extract_core_code(content):
    content = re.sub(r'\/\*.*?\*\/', '', content, flags=re.DOTALL)
    content = re.sub(r'^\s*import\s+.*;', '', content, flags=re.MULTILINE)
    return content


def refine_prompt_with_samples(source_folder, model_name="gpt-3.5-turbo-0125"):
    llm = ChatOpenAI(temperature=0, model_name=model_name)
    print(f"LLM model: {model_name}")

    memory = ConversationBufferWindowMemory(return_messages=True, k=3)

    # 初始的提示词
    initial_label_negative = "<experience>\n" \
                             "1. Abstraction Level: Test code focusing on high-level behavior and interactions rather than low-level implementation details is less impacted by changes in the production code.\n" \
                             "2. Parameter Independence: Test code that does not rely heavily on specific parameters or types passed to production code methods can remain stable across code changes.\n" \
                             "3. Mocking and Stubbing: Leveraging mocks and stubs for external dependencies in test code can decouple it from changes in the internal workings of the production code." \
                             "\n</experience>"

    initial_label_positive = "<experience>\n" \
                             "1. Abstraction Level: Changes in the production code that introduce new features or modify existing functionalities usually require updates to test code to ensure coverage.\n" \
                             "2. Parameter Dependence: If the production code changes involve modifications to method parameters or their types, the test code should be updated accordingly to match the new method signatures.\n" \
                             "3. Direct Impact: Any direct changes in the core logic or functionality that the test code is intended to validate should prompt an update in the test code." \
                             "\n</experience>"

    system_message_negative = (
        "You are an expert software testing engineer specializing in analyzing production code changes and updating corresponding test cases. "
        "Your task is to summarize and learn when changes in production code do not require updates to test code. "
        "You will be given pairs of old and new production code along with the old test code. "
        "Analyze the differences and summarize the reasons why the test code does not need updates based on the changes in the production code. "
        "Use these insights to refine and improve the decision criteria. "
        "Summarize your findings within the <experience></experience> tags. If you encounter a similar situation, integrate it into one experience; if you make a new discovery, add it as a new key point of experience."
        f"\n{initial_label_negative}"
    )

    system_message_positive = (
        "You are an expert software testing engineer specializing in analyzing production code changes and updating corresponding test cases. "
        "Your task is to summarize and learn when changes in production code require updates to test code. "
        "You will be given pairs of old and new production code along with the old test code. "
        "Analyze the differences and summarize the reasons why the test code needs updates based on the changes in the production code. "
        "Use these insights to refine and improve the decision criteria. "
        "Summarize your findings within the <experience></experience> tags. If you encounter a similar situation, integrate it into one experience; if you make a new discovery, add it as a new key point of experience."
        f"\n{initial_label_positive}"
    )

    human_message_template = (
        "Below are the old and new versions of the production code and the old test code. "
        "The old production code is marked between <old_prod> and </old_prod>. "
        "The new production code is marked between <new_prod> and </new_prod>. "
        "The old test code is marked between <old_test> and </old_test>.\n\n"
        "Carefully compare the old and new production code to identify any changes in functionality or logic. "
        "Then determine why the old test code still adequately tests the new production code."
        "Summarize your findings within the <experience></experience> tags. If you encounter a similar situation, integrate it into one experience; if you make a new discovery, add it as a new key point of experience."
        # "Output in plain text format with numbering."
    )

    prompts_negative = ChatPromptTemplate.from_messages([
        SystemMessage(content=system_message_negative),
        HumanMessagePromptTemplate.from_template(human_message_template),
        MessagesPlaceholder(variable_name="history"),
        HumanMessagePromptTemplate.from_template("{input}")
    ])

    prompts_positive = ChatPromptTemplate.from_messages([
        SystemMessage(content=system_message_positive),
        HumanMessagePromptTemplate.from_template(human_message_template),
        MessagesPlaceholder(variable_name="history"),
        HumanMessagePromptTemplate.from_template("{input}")
    ])

    conversation_negative = ConversationChain(llm=llm, prompt=prompts_negative, memory=memory, verbose=True)
    conversation_positive = ConversationChain(llm=llm, prompt=prompts_positive, memory=memory, verbose=True)

    thres = 50
    count_negative = 0
    count_positive = 0

    label_content_negative = ""
    label_content_positive = ""

    for project in os.listdir(source_folder):
        project_path = os.path.join(source_folder, project)
        if os.path.isdir(project_path):
            for commit_id in os.listdir(project_path):
                commit_path = os.path.join(project_path, commit_id)
                if os.path.isdir(commit_path):
                    for sample_id in os.listdir(commit_path):
                        sample_path = os.path.join(commit_path, sample_id)
                        if os.path.isdir(sample_path):
                            label_file = os.path.join(sample_path, 'label.txt')
                            if os.path.exists(label_file):
                                label = read_file(label_file).strip()
                                if label == '0' and count_negative < thres:  # 只处理label为0的样本
                                    count_negative += 1
                                    old_product = read_file(os.path.join(sample_path, 'old_product.java'))
                                    new_product = read_file(os.path.join(sample_path, 'new_product.java'))
                                    old_test = read_file(os.path.join(sample_path, 'old_test.java'))

                                    old_product_core = extract_core_code(old_product)
                                    new_product_core = extract_core_code(new_product)
                                    old_test_core = extract_core_code(old_test)

                                    input_text = (
                                            "\n<old_prod>\n" + old_product_core + "\n</old_prod>\n\n"
                                                                                  "<new_prod>\n" + new_product_core + "\n</new_prod>\n\n"
                                                                                                                      "<old_test>\n" + old_test_core + "\n</old_test>"
                                    )

                                    # 更新提示词中的标签内容
                                    # refined_system_message = system_message_negative.replace("<experience></experience>",
                                    #                                                          f"<experience>{label_content_negative}</experience>")
                                    refined_system_message = re.sub(r"<experience>.*?</experience>",
                                                                    f"<experience>{label_content_negative}</experience>",
                                                                    system_message_negative)


                                    prompts_negative = ChatPromptTemplate.from_messages([
                                        SystemMessage(content=refined_system_message),
                                        MessagesPlaceholder(variable_name="history"),
                                        HumanMessagePromptTemplate.from_template("{input}")
                                    ])
                                    conversation_negative = ConversationChain(llm=llm, prompt=prompts_negative, memory=memory,
                                                                              verbose=True)

                                    output = conversation_negative.predict(input=input_text)
                                    print(f"LLM Output for {project}/{commit_id}/{sample_id} (Negative): {output}")

                                    # 提取和总结标签内容
                                    experience_tag_start = output.find("<experience>")
                                    experience_tag_end = output.find("</experience>")
                                    if experience_tag_start != -1 and experience_tag_end != -1:
                                        new_experience = output[experience_tag_start + len(
                                            "<experience>"):experience_tag_end].strip()
                                        if new_experience not in label_content_negative:
                                            label_content_negative += "\n" + new_experience

                                elif label == '1' and count_positive < thres:  # 只处理label为1的样本
                                    count_positive += 1
                                    old_product = read_file(os.path.join(sample_path, 'old_product.java'))
                                    new_product = read_file(os.path.join(sample_path, 'new_product.java'))
                                    old_test = read_file(os.path.join(sample_path, 'old_test.java'))

                                    old_product_core = extract_core_code(old_product)
                                    new_product_core = extract_core_code(new_product)
                                    old_test_core = extract_core_code(old_test)

                                    input_text = (
                                            "\n<old_prod>\n" + old_product_core + "\n</old_prod>\n\n"
                                                                                  "<new_prod>\n" + new_product_core + "\n</new_prod>\n\n"
                                                                                                                      "<old_test>\n" + old_test_core + "\n</old_test>"
                                    )

                                    # 更新提示词中的标签内容
                                    # refined_system_message = system_message_positive.replace("<experience></experience>",
                                    #                                                          f"<experience>{label_content_positive}</experience>")
                                    refined_system_message = re.sub(r"<experience>.*?</experience>",
                                                                    f"<experience>{label_content_positive}</experience>",
                                                                    system_message_positive)

                                    prompts_positive = ChatPromptTemplate.from_messages([
                                        SystemMessage(content=refined_system_message),
                                        MessagesPlaceholder(variable_name="history"),
                                        HumanMessagePromptTemplate.from_template("{input}")
                                    ])
                                    conversation_positive = ConversationChain(llm=llm, prompt=prompts_positive, memory=memory,
                                                                              verbose=True)

                                    output = conversation_positive.predict(input=input_text)
                                    print(f"LLM Output for {project}/{commit_id}/{sample_id} (Positive): {output}")

                                    # 提取和总结标签内容
                                    experience_tag_start = output.find("<experience>")
                                    experience_tag_end = output.find("</experience>")
                                    if experience_tag_start != -1 and experience_tag_end != -1:
                                        new_experience = output[experience_tag_start + len(
                                            "<experience>"):experience_tag_end].strip()
                                        if new_experience not in label_content_positive:
                                            label_content_positive += "\n" + new_experience

    return label_content_negative, label_content_positive


def is_similar(content1, content2, threshold=0.8):
    # 使用TF-IDF向量化文本内容
    vectorizer = TfidfVectorizer().fit_transform([content1, content2])
    vectors = vectorizer.toarray()

    # 计算余弦相似度
    cosine_sim = cosine_similarity(vectors)
    return cosine_sim[0][1] > threshold


def simple_deduplicate_and_renumber(text):
    # 正则表达式提取所有条目和内容
    pattern = re.compile(r'(\d+)\.\s([A-Za-z ]+?):\s(.*?)(?=\n\d+\.|$)', re.DOTALL)
    matches = pattern.findall(text)

    # 使用OrderedDict去重并保留顺序
    deduped_items = OrderedDict()
    for num, title, content in matches:
        key = f"{title.strip()}: {content.strip()}"
        if key not in deduped_items:
            deduped_items[key] = title.strip()

    # 重新编号
    new_numbered_list = []
    for i, (key, title) in enumerate(deduped_items.items(), 1):
        new_numbered_list.append(f"{i}. {title}: {key.split(': ', 1)[1]}")

    # 合并成单个字符串输出
    result = '\n'.join(new_numbered_list)
    return result


def deduplicate_and_renumber(text, threshold=0.9):
    # 正则表达式提取所有条目和内容
    pattern = re.compile(r'(\d+)\.\s([A-Za-z ]+?):\s(.*?)(?=\n\d+\.|$)', re.DOTALL)
    matches = pattern.findall(text)

    # 如果两个条目的标题相同且内容相似度超过某个阈值，我们就认为它们是重复的，并只保留其中一个
    # 使用OrderedDict去重并保留顺序
    deduped_items = OrderedDict()
    for num, title, content in matches:
        key = f"{title.strip()}"
        if key not in deduped_items:
            deduped_items[key] = content.strip()
        else:
            # 比较内容相似度
            existing_content = deduped_items[key]
            if not is_similar(existing_content, content.strip(), threshold):
                deduped_items[f"{key} (2)"] = content.strip()

    # 重新编号
    new_numbered_list = []
    for i, (key, content) in enumerate(deduped_items.items(), 1):
        new_numbered_list.append(f"{i}. {key}: {content}")

    # 合并成单个字符串输出
    result = '\n'.join(new_numbered_list)
    return result


if __name__ == "__main__":
    source_folder = 'E:/desktop/XUgraduation_project/RAG_agent_codellama/chain/data/source/method/class/test/'
    refined_experience_negative, refined_experience_positive = refine_prompt_with_samples(source_folder)
    print("Refined Experience (Negative):")
    print(refined_experience_negative)
    print("简单去重 (Negative):")
    final_experience1_negative = simple_deduplicate_and_renumber(refined_experience_negative)
    print(final_experience1_negative)
    print("严谨去重 (Negative):")
    final_experience2_negative = deduplicate_and_renumber(refined_experience_negative)
    print(final_experience2_negative)

    print("Refined Experience (Positive):")
    print(refined_experience_positive)
    print("简单去重 (Positive):")
    final_experience1_positive = simple_deduplicate_and_renumber(refined_experience_positive)
    print(final_experience1_positive)
    print("严谨去重 (Positive):")
    final_experience2_positive = deduplicate_and_renumber(refined_experience_positive)
    print(final_experience2_positive)
