from langchain_core.messages import SystemMessage
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.prompts import HumanMessagePromptTemplate
from langchain_core.prompts import MessagesPlaceholder


class Prompt:
    # message
    system_message = SystemMessage(
        content="You are a professional software testing engineer who is good at testing production code and writing tests to test it. "
                "In real software development, versions often change, and you need to update old test code for a production commit.")

    # message prompt template
    human_template_input = HumanMessagePromptTemplate.from_template("{input}")

    human_template_diff = HumanMessagePromptTemplate.from_template(
        "Next, you will receive the old version of the test method, as well as the change of production method. "
        "The old test method starts with the <old_test> tag and ends with the </old_test> tag; "
        "The change of production method from old version to new version starts with the <diff_product> tag and ends with the </diff_product> tag.\n\n"
        "<old_test>\n{old_test}\n</old_test>\n\n"
        "<diff_product>\n{diff_product}\n</diff_product>\n\n"
        "Please read the above method carefully and give the new test method, "
        "new method starting with the <new_test> tag and ending with the </new_test> tag.")
    human_template_source = HumanMessagePromptTemplate.from_template(
        "Next, you will receive the old version of the production method and the test method, as well as the updated production method. "
        "The old production method starts with the <old_product> tag and ends with the </old_product> tag; "
        "The old test method starts with the <old_test> tag and ends with the </old_test> tag; "
        "The new production method starts with the <new_product> tag and ends with the </new_product> tag.\n\n"
        "<old_product>\n{old_product}\n</old_product>\n\n"
        "<old_test>\n{old_test}\n</old_test>\n\n"
        "<new_product>\n{new_product}\n</new_product>\n\n"
        "Please read the above method carefully and give the new test method, "
        "new method starting with the <new_test> tag and ending with the </new_test> tag.")
    human_template_diff_sample = HumanMessagePromptTemplate.from_template(
        "Next, you will receive the old version of the test method, as well as the change of production method. "
        "The old test method starts with the <old_test> tag and ends with the </old_test> tag; "
        "The change of production method from old version to new version starts with the <diff_product> tag and ends with the </diff_product> tag.\n\n"
        "<old_test>\n{old_test}\n</old_test>\n\n"
        "<diff_product>\n{diff_product}\n</diff_product>\n\n"
        "You can refer to diff files for other production and test methods that have high similarity scores to those given above, "
        "starting and ending with <sample_diff_product>, </sample_diff_product>, and <sample_diff_test>, </sample_diff_test>, respectively.\n\n"
        "<sample_diff_product>\n{sample_diff_product}\n</sample_diff_product>\n\n"
        "<sample_diff_test>\n{sample_diff_test}\n</sample_diff_test>\n\n"
        "Please read the above method carefully and give the new test method, "
        "new method starting with the <new_test> tag and ending with the </new_test> tag.")
    human_template_source_sample = HumanMessagePromptTemplate.from_template(
        "Next, you will receive the old version of the production method and the test method, as well as the updated production method. "
        "The old production method starts with the <old_product> tag and ends with the </old_product> tag; "
        "The old test method starts with the <old_test> tag and ends with the </old_test> tag; "
        "The new production method starts with the <new_product> tag and ends with the </new_product> tag.\n\n"
        "<old_product>\n{old_product}\n</old_product>\n\n"
        "<old_test>\n{old_test}\n</old_test>\n\n"
        "<new_product>\n{new_product}\n</new_product>\n\n"
        "You can refer to diff files for other production and test methods that have high similarity scores to those given above, "
        "starting and ending with <sample_diff_product>, </sample_diff_product>, and <sample_diff_test>, </sample_diff_test>, respectively.\n\n"
        "<sample_diff_product>\n{sample_diff_product}\n</sample_diff_product>\n\n"
        "<sample_diff_test>\n{sample_diff_test}\n</sample_diff_test>\n\n"
        "Please read the above method carefully and give the new test method, "
        "new method starting with the <new_test> tag and ending with the </new_test> tag.")
    human_template_compile = HumanMessagePromptTemplate.from_template(
        "The test method you generated is incorrect and cannot be compiled by Java compiler. "
        "This is the error messages returned by the Java compiler.\n"
        "{error}\n"
        "Please generate a new test method based on the error message, "
        "new method starting with the <new_test> tag and ending with the </new_test> tag.")
    human_template_unit_test = HumanMessagePromptTemplate.from_template(
        "The test method you generated can be compiled by the Java compiler but cannot be passed by the JUnit. "
        "This is the failure messages generated by JUnit.\n"
        "{failure}\n"
        "Please generate a new test method based on the failure message, "
        "new method starting with the <new_test> tag and ending with the </new_test> tag.")
    human_template_cover_test = HumanMessagePromptTemplate.from_template(
        "The test method you generated cannot fully cover the production method. "
        "This is an uncovered element.\n"
        "{element}\n"
        "Please generate a new test method to improve test coverage, "
        "new method starting with the <new_test> tag and ending with the </new_test> tag.")

    # chat prompt template
    chat_template = ChatPromptTemplate.from_messages(
        [system_message, MessagesPlaceholder(variable_name="history"), human_template_input])


if __name__ == "__main__":
    # test
    system_message = Prompt.system_message
    human_prompt = Prompt.human_template_source_sample
    chat_prompt = Prompt.chat_template

    print(system_message)
    print(human_prompt)
    print(chat_prompt)

    print("Done")
