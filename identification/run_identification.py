import os
import re
import csv
from collections import Counter
import sys
import signal
from langchain.chains import ConversationChain
from langchain_openai import ChatOpenAI
from langchain_community.document_loaders import TextLoader
from langchain_community.embeddings import HuggingFaceEmbeddings
from langchain_community.vectorstores import Chroma
from pathlib import Path

from langchain.memory import ConversationBufferWindowMemory
from langchain.prompts import (
    ChatPromptTemplate
)
from langchain.schema.messages import (
    HumanMessage,
    SystemMessage
)
from langchain_core.prompts import HumanMessagePromptTemplate, MessagesPlaceholder
from lxml import html

from transformers import GPT2Tokenizer

# 环境设定
os.environ["OPENAI_API_KEY"] = "your openai api key here"
os.environ["OPENAI_API_BASE"] = "your openai api proxy here"


def set_flag(text):
    if re.search(r'\byes\b', text, re.IGNORECASE):
        return True
    elif re.search(r'\bno\b', text, re.IGNORECASE):
        return False
    return None


def read_file(file_path):
    with open(file_path, 'r', encoding='utf-8') as file:
        return file.read()


def extract_core_code(content):
    content = re.sub(r'\/\*.*?\*\/', '', content, flags=re.DOTALL)
    content = re.sub(r'^\s*import\s+.*;', '', content, flags=re.MULTILINE)
    return content


def predict(old_product_core, new_product_core, old_test_core):
    model_name = "gpt-3.5-turbo-0125"
    # model_name = "gpt-4o-2024-05-13"

    llm = ChatOpenAI(temperature=0, model_name=model_name)
    print(f"LLM model: {model_name}")

    memory = ConversationBufferWindowMemory(return_messages=True, k=3)

    # 初始的提示词
    experience = "<experience>\n" \
                    "1. Abstraction Level: The test code focuses on starting the server and does not directly interact with the low-level details of port handling or server address creation. Therefore, the changes in the production code from returning a ServerPort to a ServerAddress do not impact the high-level behavior being tested in the test code." \
                    "2. Parameter Independence: The test code does not heavily rely on specific parameters passed to the production code method. It mainly focuses on creating the server and starting it with the necessary components. The addition of the address parameter in the new production code method does not affect the test code as it does not directly interact with this parameter." \
                    "3. Mocking and Stubbing: The test code effectively uses mocks and stubs for creating the server context and handling client interactions. This decoupling from the actual production code implementation allows the test code to remain stable even when there are changes in the internal workings of the production code method." \
                    "4. Abstraction Level: Test code focusing on high-level behavior and interactions rather than low-level implementation details is less impacted by changes in the production code." \
                    "5. Parameter Independence: Test code that does not rely heavily on specific parameters or types passed to production code methods can remain stable across code changes." \
                    "6. Mocking and Stubbing: Leveraging mocks and stubs for external dependencies in test code can decouple it from changes in the internal workings of the production code." \
                    "7. Consistency in Production Code Changes: In this scenario, the changes in the production code method signature from returning a ServerPort to a ServerAddress and the adjustments in parameter handling do not directly affect the test code. The test code remains unaffected as it continues to focus on the high-level behavior of starting the server and interacting with it, without being tightly coupled to the specific details of the production code changes." \
                    "8. Consistency in Production Code Changes: The modifications in the production code method, such as the version requirement message and the check for the existing errorReporterSingleton instance, do not directly affect the test code. The test code remains unaffected as it continues to focus on the high-level behavior of initializing ACRA and handling exceptions, without being tightly coupled to the specific details of the production code changes." \
                    "\n</experience>"


    system_message = (
        "You are an expert software testing engineer specializing in analyzing production code changes and updating corresponding test cases. "
        "You will be given the old and new versions of production code along with the old test code. "
        "Your task is to determine if the old test code needs updates based on the changes in the production code. "
        "Only if the production code changes affect the functionality or logic covered by the test code should the test code be updated. "
        "I will give you some experience to tell you when the modification of production code does not require updating the test code. "
        f"\n{experience}"
        "If the test code needs updating, please output 'yes', otherwise output 'no'. "
        # "Only output 'yes' or 'no'，"
        "Provide clear reasons for your decision, focusing on code functionality and logic changes."
    )

    human_message_1 = (
        "Below are the old and new versions of the production code and the old test code. "
        "The old production code is marked between <old_prod> and </old_prod>. "
        "The new production code is marked between <new_prod> and </new_prod>. "
        "The old test code is marked between <old_test> and </old_test>.\n\n"
        "Carefully compare the old and new production code to identify any changes in functionality or logic. "
        "Then determine if the old test code still adequately tests the new production code, considering if the changes impact the covered functionality or logic.\n\n"
        "For example, if a parameter is added that doesn't affect the core functionality being tested, the test code may not need updating. "
        "Similarly, if the return type changes but doesn't affect the test assertions, the test code may not need updating."
    )

    human_message_2 = (
        "Please read the <old_prod>, <new_prod>, and <old_test> sections carefully, and decide if the old test code needs to be updated to reflect changes in the new production code. "
        "Focus on whether the changes in the production code affect the functionality or logic that the test code is intended to cover. "
        "If the test code needs to be updated, please just output 'yes'. Otherwise, please just output 'no'. "
        # "Only output 'yes' or 'no'."
        "Additionally, explain the reasons for your decision, focusing on the changes in code functionality and logic."

    )


    prompts = ChatPromptTemplate.from_messages([
        SystemMessage(content=system_message),
        HumanMessage(content=human_message_1),
        HumanMessage(content=human_message_2),
        MessagesPlaceholder(variable_name="history"),
        HumanMessagePromptTemplate.from_template("{input}")
    ])

    conversation = ConversationChain(llm=llm, prompt=prompts, memory=memory, verbose=True)

    input_text = (
        "\n<old_prod>\n" + old_product_core + "\n</old_prod>\n\n"
        "<new_prod>\n" + new_product_core + "\n</new_prod>\n\n"
        "<old_test>\n" + old_test_core + "\n</old_test>"
    )

    output = conversation.predict(input=input_text)
    print(output)

    flag = set_flag(output)
    return flag, output




def process_sample(project_name, commit_id, sample_id, commit_sample_path):
    try:
        old_product = read_file(os.path.join(commit_sample_path, 'old_product.java'))
        new_product = read_file(os.path.join(commit_sample_path, 'new_product.java'))
        old_test = read_file(os.path.join(commit_sample_path, 'old_test.java'))
        new_test = read_file(os.path.join(commit_sample_path, 'new_test.java'))
        label = read_file(os.path.join(commit_sample_path, 'label.txt')).strip()

        diff_prod = read_file(os.path.join(commit_sample_path, 'diff_product.diff').replace('source', 'diff'))

        old_product_core = extract_core_code(old_product)
        new_product_core = extract_core_code(new_product)
        old_test_core = extract_core_code(old_test)

        flag, output = predict(old_product_core, new_product_core, old_test_core)
        if flag and label == '1':
            print(f"flag={flag} label={label}")
            predict_label = True
            print(f"预测正确")
        elif not flag and label == '0':
            print(f"flag={flag} label={label}")
            predict_label = True
            print(f"预测正确")
        else:
            print(f"flag={flag} label={label}")
            predict_label = False
            print(f"预测错误！！！")

        return project_name, commit_id, sample_id, label, flag, predict_label, output

    except Exception as e:
        print(f"Error processing {commit_sample_path}: {e}")
        return project_name, commit_id, sample_id, label, None, False, str(e)


def process_commit(project_name, commit_id, commit_path, results, processed_samples):
    for sample_id in os.listdir(commit_path):
        sample_path = os.path.join(commit_path, sample_id)
        if os.path.isdir(sample_path) and (project_name, commit_id, sample_id) not in processed_samples:
            result = process_sample(project_name, commit_id, sample_id, sample_path)
            if result:
                results.append(result)


def process_project(project_name, project_path, results, processed_samples):
    for commit_id in os.listdir(project_path):
        commit_path = os.path.join(project_path, commit_id)
        if os.path.isdir(commit_path):
            process_commit(project_name, commit_id, commit_path, results, processed_samples)


def process_source(source_folder, results, processed_samples):
    for project in os.listdir(source_folder):
        project_path = os.path.join(source_folder, project)
        if os.path.isdir(project_path):
            process_project(project, project_path, results, processed_samples)

    return results


def read_existing_results(output_file):
    processed_samples = set()
    if os.path.exists(output_file):
        with open(output_file, 'r', newline='', encoding='utf-8') as csvfile:
            reader = csv.reader(csvfile)
            next(reader)  # Skip header row
            for row in reader:
                if len(row) == 7:
                    project_name, commit_id, sample_id = row[:3]
                    processed_samples.add((project_name, commit_id, sample_id))
    return processed_samples


def write_results_to_file(results, output_file):
    with open(output_file, 'a', newline='', encoding='utf-8') as csvfile:
        writer = csv.writer(csvfile)
        if os.path.getsize(output_file) == 0:
            writer.writerow(['Project Name', 'Commit ID', 'Sample ID', 'Label', 'Update Flag', 'Predict Label', 'LLM Output'])
        for result in results:
            writer.writerow(result)


# ===============================================
def extract_labels(source_folder):
    labels = []
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
                                labels.append(label)
    return labels

def calculate_label_distribution(labels):
    total_count = len(labels)
    label_counts = Counter(labels)
    distribution = []
    for label, count in label_counts.items():
        percentage = (count / total_count) * 100
        distribution.append((label, count, percentage))
    return distribution

def print_label_distribution(distribution):
    print("Label Distribution:")
    print(f"{'Label':<10} {'Count':<10} {'Percentage':<10}")
    for label, count, percentage in distribution:
        print(f"{label:<10} {count:<10} {percentage:.2f}%")

def signal_handler(sig, frame):
    global results, output_file
    print("Interrupted! Writing results to file...")
    write_results_to_file(results, output_file)
    print(f"Results written to {output_file}")
    sys.exit(0)


if __name__ == "__main__":
    source_folder = 'E:/desktop/XUgraduation_project/RAG_agent_codellama/chain/data/source/method/class/test/'
    # source_folder = 'E:/desktop/XUgraduation_project/RAG_agent_codellama/chain/data/source/method/gen/train/'
    output_file = 'E:/desktop/result/results.csv'

    # 统计label分布
    # labels = extract_labels(source_folder)
    # distribution = calculate_label_distribution(labels)
    # print_label_distribution(distribution)


    processed_samples = read_existing_results(output_file)
    results = []

    # 注册信号处理函数
    signal.signal(signal.SIGINT, signal_handler)

    process_source(source_folder, results, processed_samples)
    write_results_to_file(results, output_file)
    print(f"Results written to {output_file}")
