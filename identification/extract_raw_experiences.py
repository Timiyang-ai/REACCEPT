import argparse
import os
import re
from pathlib import Path
from typing import Dict, List, Optional, Tuple

import openai
import pandas as pd
import tiktoken
from langchain.chains import ConversationChain
from langchain.memory import ConversationBufferWindowMemory
from langchain.prompts import ChatPromptTemplate
from langchain.schema.messages import SystemMessage
from langchain_core.prompts import (HumanMessagePromptTemplate,
                                    MessagesPlaceholder)
from langchain_openai import ChatOpenAI

# ========== Constants Definition ==========

# --- Model and API Related ---
DEFAULT_MODEL_NAME = "gpt-3.5-turbo-0125"
# Adjust context token limit based on your model
DEFAULT_CONTEXT_LIMIT = 16000
# Number of conversation turns to keep in LangChain memory
CONVERSATION_MEMORY_K = 3

# --- Filename Constants ---
LABEL_FILENAME = "label.txt"
OLD_PROD_FILENAME = "old_product.java"
NEW_PROD_FILENAME = "new_product.java"
OLD_TEST_FILENAME = "old_test.java"

# --- Prompt Related Constants ---
TAG_EXPERIENCE_START = "<experience>"
TAG_EXPERIENCE_END = "</experience>"
TAG_OLD_PROD_START = "<old_prod>"
TAG_OLD_PROD_END = "</old_prod>"
TAG_NEW_PROD_START = "<new_prod>"
TAG_NEW_PROD_END = "</new_prod>"
TAG_OLD_TEST_START = "<old_test>"
TAG_OLD_TEST_END = "</old_test>"

SYSTEM_MESSAGE_NEGATIVE = (
    "You are an expert software testing engineer specializing in analyzing production code changes "
    "and corresponding test cases. Your task is to identify **unique general principles** for when "
    "changes in production code do **NOT** require updates to test code. Focus on summarizing the "
    "reasons why the existing test cases remain valid, even after production code changes.\n\n"
    "Ensure that each principle is based on the specific context of the given input, avoiding repetition "
    "across different examples.\n\n"
    f"Include the summary strictly within {TAG_EXPERIENCE_START}{TAG_EXPERIENCE_END} tags."
)

SYSTEM_MESSAGE_POSITIVE = (
    "You are an expert software testing engineer specializing in analyzing production code changes "
    "and corresponding test cases. Your task is to summarize **general principles** for when changes "
    "in production code **DO** require updates to test code.\n\n"
    "The findings must:\n"
    "1. Be concise and generalized (1-2 sentences).\n"
    "2. Highlight principles like input/output changes, dependency updates, or exception handling.\n\n"
    f"Include the summary strictly within {TAG_EXPERIENCE_START}{TAG_EXPERIENCE_END} tags."
)

HUMAN_MESSAGE_TEMPLATE_CONTENT = (
    "Below are the old and new versions of the production code and the old test code. "
    f"The old production code is marked between {TAG_OLD_PROD_START} and {TAG_OLD_PROD_END}. "
    f"The new production code is marked between {TAG_NEW_PROD_START} and {TAG_NEW_PROD_END}. "
    f"The old test code is marked between {TAG_OLD_TEST_START} and {TAG_OLD_TEST_END}.\n\n"
    "Carefully compare the old and new production code to identify any changes in functionality or logic. "
    "Then determine why the old test code still adequately tests the new production code or "
    "why updates are required. Summarize your findings strictly within "
    f"{TAG_EXPERIENCE_START}{TAG_EXPERIENCE_END} tags, ensuring they are generalized and concise."
)

# --- Output Column Names ---
COL_PROJECT = "Project Name"
COL_COMMIT = "Commit ID"
COL_SAMPLE = "Sample ID"
COL_LABEL = "Label"
COL_EXPERIENCE = "Experience"
# Marker for samples skipped due to token limits
EXPERIENCE_SKIPPED_TOKEN = "超出"
# Marker for when experience tag is not found in output
EXPERIENCE_NOT_FOUND = "No experience found"

# --- Regular Expression ---
# To extract content within <experience> tags, re.DOTALL allows '.' to match newlines
EXPERIENCE_REGEX = re.compile(rf"{TAG_EXPERIENCE_START}(.*?){TAG_EXPERIENCE_END}", re.DOTALL)


# ========== Utility Functions ==========

def check_openai_api_env_vars() -> None:
    """Checks if OpenAI API Key and Base URL environment variables are set."""
    if not os.getenv("OPENAI_API_KEY"):
        raise ValueError("Environment variable 'OPENAI_API_KEY' is not set. Please set it before running.")
    # Base URL is optional, uncomment if required by your service
    # if not os.getenv("OPENAI_API_BASE"):
    #     raise ValueError("Environment variable 'OPENAI_API_BASE' is not set. Please set it before running.")
    # print("OpenAI API Key and Base URL environment variables checked.") # Optional confirmation message

def read_file(file_path: Path) -> str:
    """
    Reads the content of a file specified by the path.

    Args:
        file_path: The path to the file (Path object).

    Returns:
        The content of the file as a string.

    Raises:
        FileNotFoundError: If the file does not exist.
        IOError: If an error occurs during file reading.
    """
    try:
        with open(file_path, 'r', encoding='utf-8') as file:
            return file.read()
    except FileNotFoundError:
        print(f"Error: File not found at {file_path}")
        raise
    except IOError as e:
        print(f"Error: IO error reading file {file_path}: {e}")
        raise

def calculate_token_length(text: str, model_name: str = DEFAULT_MODEL_NAME) -> int:
    """
    Calculates the number of tokens in a text using tiktoken.

    Args:
        text: The text to calculate tokens for.
        model_name: The name of the model to use for encoding selection.

    Returns:
        The number of tokens in the text.
    """
    try:
        encoder = tiktoken.encoding_for_model(model_name)
        return len(encoder.encode(text))
    except KeyError:
        # Fallback encoding if the specific model encoder is not found
        print(f"Warning: Tiktoken encoder for model '{model_name}' not found. Using 'cl100k_base'.")
        encoder = tiktoken.get_encoding("cl100k_base")
        return len(encoder.encode(text))


def truncate_text(text: str, max_tokens: int, model_name: str = DEFAULT_MODEL_NAME) -> str:
    """
    Truncates text if its token count exceeds the specified maximum.

    Args:
        text: The text to check and potentially truncate.
        max_tokens: The maximum allowed number of tokens.
        model_name: The model name used for token calculation.

    Returns:
        The original text if within limits, or the truncated text.
    """
    try:
        encoder = tiktoken.encoding_for_model(model_name)
    except KeyError:
        print(f"Warning: Tiktoken encoder for model '{model_name}' not found. Using 'cl100k_base' for truncation.")
        encoder = tiktoken.get_encoding("cl100k_base")

    tokens = encoder.encode(text)
    if len(tokens) > max_tokens:
        truncated_tokens = tokens[:max_tokens]
        return encoder.decode(truncated_tokens)
    return text


def extract_experience(output: str) -> str:
    """
    Extracts content enclosed within <experience> tags from the model's output string.

    Args:
        output: The string returned by the model, potentially containing experience tags.

    Returns:
        The extracted experience content, or EXPERIENCE_NOT_FOUND if the tag is not found.
    """
    match = EXPERIENCE_REGEX.search(output)
    if match:
        return match.group(1).strip()
    return EXPERIENCE_NOT_FOUND

# ========== LangChain Setup Function ==========

def create_conversation_chain(llm: ChatOpenAI, system_message: str) -> ConversationChain:
    """
    Creates a LangChain ConversationChain with a specific system message and memory.

    Args:
        llm: An initialized ChatOpenAI instance.
        system_message: The system prompt to use for the chain.

    Returns:
        A configured ConversationChain instance.
    """
    memory = ConversationBufferWindowMemory(
        return_messages=True, k=CONVERSATION_MEMORY_K
    )
    prompt = ChatPromptTemplate.from_messages([
        SystemMessage(content=system_message),
        HumanMessagePromptTemplate.from_template(HUMAN_MESSAGE_TEMPLATE_CONTENT),
        MessagesPlaceholder(variable_name="history"), # Placeholder for conversation history
        HumanMessagePromptTemplate.from_template("{input}") # Placeholder for the current user input
    ])
    return ConversationChain(llm=llm, prompt=prompt, memory=memory, verbose=True) # verbose=True logs interactions

# ========== Core Processing Function ==========

def process_sample(
    sample_path: Path,
    project_name: str,
    commit_id: str,
    sample_id: str,
    conversation_positive: ConversationChain,
    conversation_negative: ConversationChain,
    model_name: str = DEFAULT_MODEL_NAME,
    context_limit: int = DEFAULT_CONTEXT_LIMIT,
) -> Optional[Dict[str, str]]:
    """
    Processes a single sample folder: reads files, builds input, calls LLM, extracts experience.

    Args:
        sample_path: Path to the individual sample folder.
        project_name: Name of the project.
        commit_id: Commit ID.
        sample_id: Sample ID.
        conversation_positive: Chain for processing label '1' samples.
        conversation_negative: Chain for processing label '0' samples.
        model_name: Name of the LLM model being used.
        context_limit: Context token limit for the model.

    Returns:
        A dictionary containing sample info (Project Name, Commit ID, Sample ID, Label, Experience),
        or None if an unrecoverable error occurs or the label is invalid.
        If skipped due to token limit, Experience is set to EXPERIENCE_SKIPPED_TOKEN.
    """
    label_file = sample_path / LABEL_FILENAME
    old_prod_file = sample_path / OLD_PROD_FILENAME
    new_prod_file = sample_path / NEW_PROD_FILENAME
    old_test_file = sample_path / OLD_TEST_FILENAME

    # Ensure all required files exist
    required_files = [label_file, old_prod_file, new_prod_file, old_test_file]
    if not all(f.exists() for f in required_files):
        relative_path = sample_path.relative_to(sample_path.parent.parent.parent) # For clearer logging
        print(f"Warning: Sample {relative_path} is missing required files. Skipping.")
        return None

    try:
        label = read_file(label_file).strip()
        old_product = read_file(old_prod_file)
        new_product = read_file(new_prod_file)
        old_test = read_file(old_test_file)
    except (FileNotFoundError, IOError):
        # read_file function already prints error details
        return None

    # Validate the label
    if label not in ['0', '1']:
        relative_path = sample_path.relative_to(sample_path.parent.parent.parent)
        print(f"Warning: Sample {relative_path} has an invalid label '{label}'. Skipping.")
        return None

    # Construct the input text for the LLM
    input_text = (
        f"\n{TAG_OLD_PROD_START}\n{old_product}\n{TAG_OLD_PROD_END}\n\n"
        f"{TAG_NEW_PROD_START}\n{new_product}\n{TAG_NEW_PROD_END}\n\n"
        f"{TAG_OLD_TEST_START}\n{old_test}\n{TAG_OLD_TEST_END}"
    )

    # Estimate token count and handle potential overflow
    system_msg = SYSTEM_MESSAGE_POSITIVE if label == '1' else SYSTEM_MESSAGE_NEGATIVE
    system_msg_len = calculate_token_length(system_msg, model_name=model_name)
    input_len = calculate_token_length(input_text, model_name=model_name)

    # Reserve tokens for conversation history (k*2 turns approx) and model output buffer
    # This is an estimation; actual usage might vary. Adjust buffer as needed.
    history_buffer = 500 # Rough estimate for k=3 history
    output_buffer = 500  # Buffer for the model's response
    available_input_tokens = context_limit - system_msg_len - history_buffer - output_buffer

    relative_path = sample_path.relative_to(sample_path.parent.parent.parent) # For logging
    if input_len > available_input_tokens:
        print(f"Info: Input tokens ({input_len}) for sample {relative_path} might exceed limit. Attempting truncation...")
        input_text = truncate_text(input_text, available_input_tokens, model_name=model_name)
        input_len = calculate_token_length(input_text, model_name=model_name) # Recalculate after truncation

        # If still too long after truncation (very close to limit), skip it
        if input_len >= available_input_tokens: # Use >= for safety
             print(f"Warning: Sample {relative_path} remains too long after truncation ({input_len} tokens). Skipping.")
             return {
                 COL_PROJECT: project_name,
                 COL_COMMIT: commit_id,
                 COL_SAMPLE: sample_id,
                 COL_LABEL: label,
                 COL_EXPERIENCE: EXPERIENCE_SKIPPED_TOKEN
             }

    # Select the appropriate conversation chain based on the label
    conversation = conversation_positive if label == '1' else conversation_negative
    output = ""
    try:
        print(f"Processing sample: {relative_path} (Label: {label})")
        output = conversation.predict(input=input_text)

    except openai.BadRequestError as e:
        # Handle API errors, specifically context length exceeded
        err_msg = str(e).lower() # Lowercase for robust matching
        if ("context_length_exceeded" in err_msg or
            "maximum context length" in err_msg or
            "tokens exceeds the limit" in err_msg):
            print(f"Warning: Context length error for sample {relative_path}. Skipping. Error: {e}")
            return {
                COL_PROJECT: project_name,
                COL_COMMIT: commit_id,
                COL_SAMPLE: sample_id,
                COL_LABEL: label,
                COL_EXPERIENCE: EXPERIENCE_SKIPPED_TOKEN
            }
        else:
            # Re-raise other API errors (e.g., auth, rate limits)
            print(f"Error: API error processing sample {relative_path}: {e}")
            raise

    except Exception as e:
         # Catch other potential runtime errors during prediction
         print(f"Error: Unexpected error processing sample {relative_path}: {e}")
         return None # Skip sample on unexpected errors

    # Extract the experience from the successful output
    new_experience = extract_experience(output)
    if new_experience == EXPERIENCE_NOT_FOUND:
         print(f"Warning: Could not find {TAG_EXPERIENCE_START}...{TAG_EXPERIENCE_END} in output for sample {relative_path}.")

    return {
        COL_PROJECT: project_name,
        COL_COMMIT: commit_id,
        COL_SAMPLE: sample_id,
        COL_LABEL: label,
        COL_EXPERIENCE: new_experience
    }


def refine_experiences_from_folder(
    source_folder: Path,
    output_csv_path: Path,
    model_name: str = DEFAULT_MODEL_NAME,
    context_limit: int = DEFAULT_CONTEXT_LIMIT,
    max_samples: Optional[int] = None,
) -> None:
    """
    Iterates through samples in the source folder, extracts experiences, and saves results to a CSV file.

    Args:
        source_folder: Root folder path containing the project/commit/sample structure.
        output_csv_path: Full path for the output CSV file.
        model_name: Name of the LLM model to use.
        context_limit: Context token limit for the model.
        max_samples: Optional maximum number of samples to process. Processes all if None.
    """
    check_openai_api_env_vars() # Check environment variables at the start

    llm = ChatOpenAI(temperature=0, model_name=model_name)
    print(f"Using LLM model: {model_name}")
    print(f"Context token limit: {context_limit}")
    print(f"Conversation memory window (k): {CONVERSATION_MEMORY_K}")

    # Create separate chains for positive and negative labels
    conversation_positive = create_conversation_chain(llm, SYSTEM_MESSAGE_POSITIVE)
    conversation_negative = create_conversation_chain(llm, SYSTEM_MESSAGE_NEGATIVE)

    results: List[Dict[str, str]] = []
    processed_count = 0
    stop_processing = False # Flag to break out of nested loops

    print(f"Starting iteration through source folder: {source_folder}")
    # Use pathlib for cleaner path iteration
    for project_path in source_folder.iterdir():
        if project_path.is_dir():
            project_name = project_path.name
            for commit_path in project_path.iterdir():
                if commit_path.is_dir():
                    commit_id = commit_path.name
                    for sample_path in commit_path.iterdir():
                        if sample_path.is_dir():
                            sample_id = sample_path.name

                            # Check if max_samples limit is reached
                            if max_samples is not None and processed_count >= max_samples:
                                print(f"Reached maximum sample limit ({max_samples}). Stopping processing.")
                                stop_processing = True
                                break # Break sample_id loop

                            # Process the individual sample
                            result = process_sample(
                                sample_path,
                                project_name,
                                commit_id,
                                sample_id,
                                conversation_positive,
                                conversation_negative,
                                model_name,
                                context_limit,
                            )

                            if result: # Append if processing was successful (returned a dict)
                                results.append(result)

                            processed_count += 1 # Increment count regardless of success for max_samples limit

                    if stop_processing: break # Break commit_id loop
            if stop_processing: break # Break project_path loop

    print(f"\nProcessing finished. Attempted to process {processed_count} sample folders.")
    print(f"Successfully generated/extracted {len(results)} experience records.")

    # Save results to CSV
    if results:
        df = pd.DataFrame(results)
        try:
            # Ensure the output directory exists
            output_csv_path.parent.mkdir(parents=True, exist_ok=True)
            # Use utf-8-sig encoding for better compatibility with Excel (handles BOM)
            df.to_csv(output_csv_path, index=False, encoding="utf-8-sig")
            print(f"Results successfully saved to: {output_csv_path}")
        except IOError as e:
            print(f"Error: Failed to write CSV file {output_csv_path}: {e}")
        except Exception as e:
            print(f"Error: An unexpected error occurred while saving CSV: {e}")
    else:
        print("No results were generated. CSV file was not created.")


# ========== Main Entry Point ==========

def main():
    """Main function to parse arguments and start the processing workflow."""
    parser = argparse.ArgumentParser(
        description="Analyze code changes and test cases using an LLM to extract testing principles. "
                    "Requires 'OPENAI_API_KEY' and optionally 'OPENAI_API_BASE' environment variables.",
        formatter_class=argparse.ArgumentDefaultsHelpFormatter # Show default values in help
    )
    parser.add_argument(
        "source_folder",
        type=str,
        help="Path to the source folder containing sample data (e.g., path/to/data/train). "
             "Expected structure: source_folder/project_name/commit_id/sample_id/"
    )
    parser.add_argument(
        "output_csv",
        type=str,
        help="Full path for the output CSV file (e.g., path/to/results/experiences.csv)."
    )
    parser.add_argument(
        "--model",
        type=str,
        default=DEFAULT_MODEL_NAME,
        help="Name of the OpenAI model to use."
    )
    parser.add_argument(
        "--limit",
        type=int,
        default=DEFAULT_CONTEXT_LIMIT,
        help="Context token limit for the model."
    )
    parser.add_argument(
        "--max-samples",
        type=int,
        default=None,
        metavar='N', # Clearer representation in help message
        help="Maximum number of samples to process. Processes all if omitted."
    )

    args = parser.parse_args()

    # Convert string paths to Path objects
    source_path = Path(args.source_folder)
    output_path = Path(args.output_csv)

    # Validate source folder existence
    if not source_path.is_dir():
        print(f"Error: Source folder '{args.source_folder}' not found or is not a directory.")
        return # Exit if source folder is invalid

    # Start the main processing function
    refine_experiences_from_folder(
        source_folder=source_path,
        output_csv_path=output_path,
        model_name=args.model,
        context_limit=args.limit,
        max_samples=args.max_samples,
    )

if __name__ == "__main__":
    main()
