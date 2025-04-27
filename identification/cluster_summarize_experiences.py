import argparse
import os
import re
from pathlib import Path
from typing import Dict, List, Optional

import numpy as np
import openai
import pandas as pd
from langchain.chains import ConversationChain
from langchain.memory import ConversationBufferWindowMemory
from langchain.prompts import ChatPromptTemplate
from langchain.schema import SystemMessage
from langchain_core.prompts import (HumanMessagePromptTemplate,
                                    MessagesPlaceholder)
from langchain_openai import ChatOpenAI
from sentence_transformers import SentenceTransformer
from sklearn.cluster import MiniBatchKMeans
from sklearn.decomposition import PCA
from sklearn.metrics.pairwise import cosine_similarity
from tqdm import tqdm

# ========== Constants Definition ==========

# --- Model and API Related ---
DEFAULT_LLM_MODEL = "gpt-3.5-turbo-0125"
DEFAULT_EMBEDDING_MODEL = 'all-MiniLM-L6-v2' # Faster option
# DEFAULT_EMBEDDING_MODEL = 'all-mpnet-base-v2' # More powerful option
DEFAULT_CONTEXT_LIMIT = 16000 # Adjust if needed for the LLM model
CONVERSATION_MEMORY_K = 3

# --- Clustering and Processing Related ---
DEFAULT_MAX_CLUSTERS = 50
DEFAULT_PCA_COMPONENTS = 50
DEFAULT_SEMANTIC_THRESHOLD = 0.85 # Threshold for duplicate removal
DEFAULT_SUMMARIZATION_CHUNK_SIZE = 8 # Batch size for first summarization pass
DEFAULT_MERGE_CHUNK_SIZE = 3 # Batch size for merging summaries

# --- Prompt Related Constants ---
TAG_EXPERIENCE_START = "<experience>"
TAG_EXPERIENCE_END = "</experience>"
EXPERIENCE_NOT_FOUND = "No experience found" # Marker for failed extraction
EXPERIENCE_REGEX = re.compile(rf"{TAG_EXPERIENCE_START}(.*?){TAG_EXPERIENCE_END}", re.DOTALL)

# --- Input/Output Related ---
INPUT_CSV_COLUMN_LABEL = "Label"
INPUT_CSV_COLUMN_EXPERIENCE = "Experience"
OUTPUT_CSV_COLUMN_LABEL_TYPE = "Label Type"
OUTPUT_CSV_COLUMN_FINAL_EXP = "Final Experience"
DEFAULT_OUTPUT_PREFIX_POS = "final_positive_experiences"
DEFAULT_OUTPUT_PREFIX_NEG = "final_negative_experiences"
DEFAULT_OUTPUT_SUFFIX = ".csv"

# --- System Prompt for Summarization ---
# Note: Keeping the original prompt structure and content
SYSTEM_MESSAGE_SUMMARIZE = (
    "You are an expert software testing engineer analyzing production code changes and corresponding test cases.\n"
    "Your task is to produce concise general principles from examples provided.\n"
    "The principles should follow the format 'Concept: Description'.\n"
    "The output should be separated into 'Positive' and 'Negative' experiences:\n"
    " - Positive Experience: These are cases where production code changes require corresponding updates in test code, i.e., the change directly impacts the test code's validity or coverage.\n"
    " - Negative Experience: These are cases where production code changes do not require updates to test code, i.e., the change does not affect test code validation or core functionality.\n"
    "In your answers, only include principles that are clearly distinct and relevant. Avoid redundant or excessively broad examples.\n"
    "Number each experience within each category sequentially (1, 2, 3...), and do not repeat the numbering.\n"
    "For each distinct 'Concept', only output one unique experience. If multiple descriptions exist for the same Concept, refine them into a single, concise principle.\n"
    f"Format your answer strictly within {TAG_EXPERIENCE_START}{TAG_EXPERIENCE_END} tags." # Added tag instruction here for clarity
)

# Prompt template for the initial summarization pass
SINGLE_PASS_PROMPT_TEMPLATE = (
    "Below are some experience statements:\n"
    "{experiences_list}\n"
    "\nPlease summarize the key principles from these examples in a structured format.\n"
    "Each principle should be clearly separated, numbered, and formatted as follows:\n"
    f"{TAG_EXPERIENCE_START}\n"
    "1. [Conceptual term]: [Detailed explanation of the experience].\n"
    "2. [Conceptual term]: [Detailed explanation of the next experience].\n"
    "...\n"
    f"{TAG_EXPERIENCE_END}\n"
    "Make sure to use conceptual terms such as 'Abstraction Level', 'Parameter Independence', 'Mocking and Stubbing', etc., followed by their explanations.\n"
    "Ensure each Concept only appears once, with distinct, refined, and non-redundant explanations.\n"
    "If multiple descriptions exist for the same Concept, refine them into a single, concise principle.\n"
    f"Format your answer strictly within {TAG_EXPERIENCE_START}{TAG_EXPERIENCE_END} tags.\n"
)

# Prompt template for merging summaries
MERGE_PASS_PROMPT_TEMPLATE = (
    "Below are some summarized experience statements:\n"
    "{summaries_list}\n"
    "\nPlease further summarize these into a concise set of principles. "
    "Ensure each Concept only appears once, with distinct, refined, and non-redundant explanations.\n"
    f"Format your answer strictly within {TAG_EXPERIENCE_START}{TAG_EXPERIENCE_END} tags.\n"
)

# Prompt template for the final merge pass
FINAL_MERGE_PROMPT_TEMPLATE = (
    "Below are summarized experience statements:\n"
    "{summaries_list}\n"
    "\nPlease provide a final, concise, and comprehensive set of principles summarizing these. "
    "Ensure each Concept only appears once, with distinct, refined, and non-redundant explanations.\n"
    f"Format your answer strictly within {TAG_EXPERIENCE_START}{TAG_EXPERIENCE_END} tags.\n"
)


# ========== Utility Functions ==========

def check_openai_api_env_vars() -> None:
    """Checks if OpenAI API Key and Base URL environment variables are set."""
    if not os.getenv("OPENAI_API_KEY"):
        raise ValueError("Environment variable 'OPENAI_API_KEY' is not set. Please set it before running.")
    # Base URL is optional
    # if not os.getenv("OPENAI_API_BASE"):
    #     raise ValueError("Environment variable 'OPENAI_API_BASE' is not set. Please set it before running.")
    # print("OpenAI API Key and Base URL environment variables checked.")

def extract_experience(output: str) -> str:
    """
    Extracts content enclosed within <experience> tags from a string.

    Args:
        output: The string potentially containing the experience tags.

    Returns:
        The extracted experience content, or EXPERIENCE_NOT_FOUND if not found.
    """
    match = EXPERIENCE_REGEX.search(output)
    if match:
        return match.group(1).strip()
    return EXPERIENCE_NOT_FOUND

def remove_semantic_duplicates(
    experiences: List[str],
    embedding_model: SentenceTransformer,
    threshold: float = DEFAULT_SEMANTIC_THRESHOLD
) -> List[str]:
    """
    Removes semantically similar experiences based on a cosine similarity threshold.

    Args:
        experiences: A list of experience strings.
        embedding_model: The SentenceTransformer model for generating embeddings.
        threshold: The cosine similarity threshold. Experiences above this are considered duplicates.

    Returns:
        A list of unique experience strings.
    """
    if not experiences:
        return []
    if len(experiences) == 1:
        return experiences

    print(f"  - Encoding {len(experiences)} items for duplicate check...")
    embeddings = embedding_model.encode(experiences, convert_to_tensor=False, show_progress_bar=False)

    print(f"  - Calculating similarity matrix (threshold={threshold})...")
    similarity_matrix = cosine_similarity(embeddings)
    np.fill_diagonal(similarity_matrix, 0) # Ignore self-similarity

    unique_indices = list(range(len(experiences)))
    indices_to_remove = set()

    for i in range(len(experiences)):
        if i in indices_to_remove:
            continue
        # Find indices of duplicates for item i
        duplicate_indices = np.where(similarity_matrix[i] >= threshold)[0]
        for dup_idx in duplicate_indices:
            if dup_idx > i: # Only remove items appearing later in the list
                 indices_to_remove.add(dup_idx)

    unique_experiences = [exp for idx, exp in enumerate(experiences) if idx not in indices_to_remove]
    print(f"  - Removed {len(indices_to_remove)} semantic duplicates.")
    return unique_experiences


def reduce_dimensions(
    embeddings: np.ndarray,
    n_components: int = DEFAULT_PCA_COMPONENTS,
    random_state: int = 42
) -> np.ndarray:
    """
    Reduces the dimensionality of embeddings using PCA.

    Args:
        embeddings: The high-dimensional embedding matrix (n_samples, n_features).
        n_components: The target number of dimensions.
        random_state: Random seed for PCA reproducibility.

    Returns:
        The reduced-dimensional embedding matrix. Returns original if reduction is not feasible.
    """
    if embeddings.shape[0] == 0: # No samples
        return embeddings
    if embeddings.shape[1] <= n_components: # Already low-dimensional
        print(f"  - Skipping PCA: Input dimension ({embeddings.shape[1]}) <= target components ({n_components}).")
        return embeddings
    if embeddings.shape[0] <= n_components: # Not enough samples for PCA
        print(f"  - Skipping PCA: Number of samples ({embeddings.shape[0]}) <= target components ({n_components}).")
        return embeddings

    # Adjust n_components dynamically if it exceeds available dimensions or samples
    actual_components = min(n_components, embeddings.shape[0], embeddings.shape[1])
    if actual_components < 2:
        print(f"  - Skipping PCA: Not enough samples or features for meaningful reduction (target components={actual_components}).")
        return embeddings

    print(f"  - Applying PCA to reduce dimensions from {embeddings.shape[1]} to {actual_components}...")
    pca = PCA(n_components=actual_components, random_state=random_state)
    reduced_embeddings = pca.fit_transform(embeddings)
    print(f"  - PCA finished. Explained variance ratio: {pca.explained_variance_ratio_.sum():.4f}")
    return reduced_embeddings

# ========== Summarization Functions ==========

def single_pass_summarize(
    conversation: ConversationChain,
    batch_experiences: List[str]
) -> str:
    """
    Performs a single summarization pass on a batch of experiences using the LLM.

    Args:
        conversation: The LangChain ConversationChain instance.
        batch_experiences: A list of experience strings for this batch.

    Returns:
        The summarized experience string extracted from the LLM output.
    """
    # Format the experiences into a numbered list for the prompt
    experiences_list_str = "\n".join(f"- {exp}" for exp in batch_experiences)
    prompt_text = SINGLE_PASS_PROMPT_TEMPLATE.format(experiences_list=experiences_list_str)

    try:
        # Call the LLM to summarize
        output = conversation.predict(input=prompt_text)
        return extract_experience(output)
    except Exception as e:
        print(f"Error during single pass summarization: {e}")
        # Return an empty string or a specific error marker if needed
        return ""


def multi_step_summarize_experiences(
    conversation: ConversationChain,
    experiences: List[str],
    label_type: str, # For logging purposes
    chunk_size: int = DEFAULT_SUMMARIZATION_CHUNK_SIZE,
    merge_chunk_size: int = DEFAULT_MERGE_CHUNK_SIZE
) -> str:
    """
    Performs multi-step summarization on a list of experiences.
    Handles potential errors during summarization passes.

    Args:
        conversation: The LangChain ConversationChain instance.
        experiences: The list of experience strings to summarize.
        label_type: The type of experiences being summarized (e.g., "Positive").
        chunk_size: Batch size for the initial summarization pass.
        merge_chunk_size: Batch size for merging intermediate summaries.

    Returns:
        The final summarized experience string.
    """
    if not experiences:
        return ""
    if len(experiences) == 1:
        return experiences[0] # No need to summarize a single item

    print(f"    - Starting multi-step summarization for {len(experiences)} {label_type} experiences...")

    # --- First Pass: Summarize in chunks ---
    sub_summaries: List[str] = []
    print(f"      - Pass 1: Summarizing in chunks of {chunk_size}...")
    for i in range(0, len(experiences), chunk_size):
        batch = experiences[i:i + chunk_size]
        if batch:
            summary = single_pass_summarize(conversation, batch)
            if summary and summary != EXPERIENCE_NOT_FOUND: # Only add valid summaries
                sub_summaries.append(summary)
            else:
                print(f"      - Warning: Failed to get summary for batch starting at index {i}")

    if not sub_summaries:
        print("      - Warning: No valid summaries generated in the first pass.")
        return "" # Return empty if first pass failed completely
    if len(sub_summaries) == 1:
        print("      - Only one sub-summary generated, returning it.")
        return sub_summaries[0]

    # --- Second Pass: Merge sub-summaries in chunks ---
    merged_summaries: List[str] = []
    print(f"      - Pass 2: Merging {len(sub_summaries)} sub-summaries in chunks of {merge_chunk_size}...")
    for i in range(0, len(sub_summaries), merge_chunk_size):
        batch = sub_summaries[i:i + merge_chunk_size]
        if batch:
            # Format the summaries list for the prompt
            summaries_list_str = "\n".join(batch) # Already formatted with <experience> tags
            prompt_text = MERGE_PASS_PROMPT_TEMPLATE.format(summaries_list=summaries_list_str)
            try:
                output = conversation.predict(input=prompt_text)
                merged_summary = extract_experience(output)
                if merged_summary and merged_summary != EXPERIENCE_NOT_FOUND:
                    merged_summaries.append(merged_summary)
                else:
                    print(f"      - Warning: Failed to merge summaries for batch starting at index {i}")
            except Exception as e:
                 print(f"      - Error during merge pass summarization: {e}")


    if not merged_summaries:
        print("      - Warning: No valid summaries generated in the merge pass.")
        # Fallback: Maybe return the joined sub-summaries? Or empty?
        return "\n".join(sub_summaries) # Return joined first-pass summaries as fallback
    if len(merged_summaries) == 1:
        print("      - Only one merged summary generated, returning it.")
        return merged_summaries[0]

    # --- Final Pass: Merge the remaining summaries ---
    print(f"      - Final Pass: Merging {len(merged_summaries)} summaries...")
    final_input_str = "\n".join(merged_summaries)
    prompt_text = FINAL_MERGE_PROMPT_TEMPLATE.format(summaries_list=final_input_str)
    final_summary = ""
    try:
        output = conversation.predict(input=prompt_text)
        final_summary = extract_experience(output)
        if not final_summary or final_summary == EXPERIENCE_NOT_FOUND:
             print("      - Warning: Failed to get final summary. Falling back to joined merged summaries.")
             final_summary = "\n".join(merged_summaries) # Fallback

    except Exception as e:
        print(f"      - Error during final merge pass summarization: {e}")
        final_summary = "\n".join(merged_summaries) # Fallback

    print("    - Multi-step summarization finished.")
    return final_summary


# ========== Clustering and Main Workflow ==========

def cluster_and_summarize(
    experiences: List[str],
    max_clusters: int,
    label_type: str, # "Positive" or "Negative"
    embedding_model: SentenceTransformer,
    conversation: ConversationChain,
    pca_components: int = DEFAULT_PCA_COMPONENTS,
    summarization_chunk_size: int = DEFAULT_SUMMARIZATION_CHUNK_SIZE,
    merge_chunk_size: int = DEFAULT_MERGE_CHUNK_SIZE,
    semantic_threshold: float = DEFAULT_SEMANTIC_THRESHOLD,
    random_state: int = 42
) -> List[str]:
    """
    Performs the full pipeline: embedding, PCA, clustering, summarization, and deduplication.

    Args:
        experiences: List of experience strings.
        max_clusters: Maximum number of clusters to create.
        label_type: Type of experience ("Positive" or "Negative").
        embedding_model: SentenceTransformer model.
        conversation: LangChain ConversationChain for summarization.
        pca_components: Number of components for PCA.
        summarization_chunk_size: Chunk size for initial summarization.
        merge_chunk_size: Chunk size for merging summaries.
        semantic_threshold: Threshold for final semantic deduplication.
        random_state: Random seed for reproducibility.

    Returns:
        A list of final, summarized, and unique experience strings.
    """
    if not experiences:
        print(f"No {label_type} experiences to process.")
        return []

    print(f"\n--- Processing {len(experiences)} {label_type} Experiences ---")

    # 1. Generate Embeddings
    print("1. Generating embeddings...")
    embeddings = embedding_model.encode(experiences, convert_to_tensor=False, show_progress_bar=True)

    # 2. Reduce Dimensions using PCA
    print("2. Reducing dimensions...")
    reduced_embeddings = reduce_dimensions(embeddings, n_components=pca_components, random_state=random_state)

    # 3. Determine Number of Clusters and Perform Clustering
    # Dynamically adjust n_clusters based on data size, capped by max_clusters
    # Aim for roughly 30-100 samples per cluster, adjust divisor as needed
    cluster_divisor = 50
    n_clusters = min(max_clusters, max(2, len(experiences) // cluster_divisor))
    print(f"3. Clustering into {n_clusters} clusters (max_clusters={max_clusters}, divisor={cluster_divisor})...")
    # Use MiniBatchKMeans for potentially large datasets
    clustering = MiniBatchKMeans(
        n_clusters=n_clusters,
        random_state=random_state,
        batch_size=256, # Adjust batch size based on memory
        n_init='auto' # Recommended for MiniBatchKMeans >= 1.1
    )
    try:
        cluster_labels = clustering.fit_predict(reduced_embeddings)
    except Exception as e:
        print(f"  - Error during clustering: {e}. Skipping clustering and summarizing all together.")
        # Fallback: Summarize all experiences without clustering if clustering fails
        final_summary = multi_step_summarize_experiences(
            conversation, experiences, label_type, summarization_chunk_size, merge_chunk_size
        )
        return [final_summary] if final_summary else []


    # 4. Group Experiences by Cluster
    print("4. Grouping experiences by cluster...")
    clusters: Dict[int, List[str]] = {i: [] for i in range(n_clusters)}
    for i, label in enumerate(cluster_labels):
        clusters[label].append(experiences[i])

    # 5. Summarize Each Cluster
    summarized_experiences: List[str] = []
    print(f"5. Summarizing each of the {n_clusters} clusters...")
    for cluster_id, cluster_exps in tqdm(clusters.items(), desc=f"Summarizing {label_type} clusters"):
        if not cluster_exps:
            continue # Skip empty clusters
        if len(cluster_exps) == 1:
            # If cluster has only one experience, keep it directly
            summarized_experiences.append(cluster_exps[0])
        else:
            # Summarize experiences within the cluster
            final_experience = multi_step_summarize_experiences(
                conversation, cluster_exps, f"{label_type} Cluster {cluster_id}",
                summarization_chunk_size, merge_chunk_size
            )
            if final_experience: # Add only if summarization was successful
                summarized_experiences.append(final_experience)

    if not summarized_experiences:
         print("  - Warning: No summaries generated after processing clusters.")
         return []

    # 6. Final Semantic Deduplication
    print("6. Performing final semantic deduplication...")
    final_list = remove_semantic_duplicates(
        summarized_experiences,
        embedding_model=embedding_model,
        threshold=semantic_threshold
    )

    print(f"--- Finished processing {label_type} experiences. Found {len(final_list)} unique principles. ---")
    return final_list


def save_final_experiences(
    output_csv_path: Path,
    experiences: List[str],
    label_type: str
) -> None:
    """
    Saves the final list of experiences to a CSV file.

    Args:
        output_csv_path: The full path to the output CSV file.
        experiences: The list of final experience strings.
        label_type: The type of experiences ("Positive" or "Negative").
    """
    if not experiences:
        print(f"No {label_type} experiences to save.")
        return

    df = pd.DataFrame({
        OUTPUT_CSV_COLUMN_LABEL_TYPE: [label_type] * len(experiences),
        OUTPUT_CSV_COLUMN_FINAL_EXP: experiences
    })

    try:
        # Ensure the output directory exists
        output_csv_path.parent.mkdir(parents=True, exist_ok=True)
        df.to_csv(output_csv_path, index=False, encoding="utf-8-sig") # utf-8-sig for Excel compatibility
        print(f"Successfully saved {len(experiences)} {label_type} experiences to {output_csv_path}")
    except IOError as e:
        print(f"Error: Failed to write CSV file {output_csv_path}: {e}")
    except Exception as e:
        print(f"Error: An unexpected error occurred while saving CSV: {e}")


# ========== Main Function ==========

def extract_and_cluster_experiences(
    input_csv_path: Path,
    output_dir: Path,
    max_clusters_positive: int = DEFAULT_MAX_CLUSTERS,
    max_clusters_negative: int = DEFAULT_MAX_CLUSTERS,
    embedding_model_name: str = DEFAULT_EMBEDDING_MODEL,
    llm_model_name: str = DEFAULT_LLM_MODEL,
    pca_components: int = DEFAULT_PCA_COMPONENTS,
    summarization_chunk_size: int = DEFAULT_SUMMARIZATION_CHUNK_SIZE,
    merge_chunk_size: int = DEFAULT_MERGE_CHUNK_SIZE,
    semantic_threshold: float = DEFAULT_SEMANTIC_THRESHOLD,
    output_prefix_positive: str = DEFAULT_OUTPUT_PREFIX_POS,
    output_prefix_negative: str = DEFAULT_OUTPUT_PREFIX_NEG,
    random_state: int = 42
) -> Tuple[List[str], List[str]]:
    """
    Main function to read experiences from CSV, cluster, summarize, and save results.

    Args:
        input_csv_path: Path to the input CSV file (output from the previous script).
        output_dir: Directory where the final positive and negative experience CSVs will be saved.
        max_clusters_positive: Max clusters for positive experiences.
        max_clusters_negative: Max clusters for negative experiences.
        embedding_model_name: Name of the SentenceTransformer model.
        llm_model_name: Name of the OpenAI LLM model for summarization.
        pca_components: Number of PCA components.
        summarization_chunk_size: Chunk size for initial summarization.
        merge_chunk_size: Chunk size for merging summaries.
        semantic_threshold: Threshold for final semantic deduplication.
        output_prefix_positive: Filename prefix for positive results CSV.
        output_prefix_negative: Filename prefix for negative results CSV.
        random_state: Random seed for reproducibility.


    Returns:
        A tuple containing two lists: (final_positive_experiences, final_negative_experiences).
    """
    check_openai_api_env_vars() # Check for API keys first

    # --- Load Data ---
    print(f"Loading experiences from: {input_csv_path}")
    try:
        df = pd.read_csv(input_csv_path)
        # Filter out potential non-string or empty experiences
        df = df.dropna(subset=[INPUT_CSV_COLUMN_EXPERIENCE])
        df = df[df[INPUT_CSV_COLUMN_EXPERIENCE].apply(lambda x: isinstance(x, str) and x.strip() != "" and x != EXPERIENCE_SKIPPED_TOKEN)] # Use constant from previous script if available
    except FileNotFoundError:
        print(f"Error: Input CSV file not found at {input_csv_path}")
        return [], []
    except KeyError as e:
         print(f"Error: Input CSV missing required column: {e}. Expected columns: '{INPUT_CSV_COLUMN_LABEL}', '{INPUT_CSV_COLUMN_EXPERIENCE}'")
         return [], []
    except Exception as e:
        print(f"Error: Failed to load or process input CSV {input_csv_path}: {e}")
        return [], []

    positive_experiences = df[df[INPUT_CSV_COLUMN_LABEL] == 1][INPUT_CSV_COLUMN_EXPERIENCE].tolist()
    negative_experiences = df[df[INPUT_CSV_COLUMN_LABEL] == 0][INPUT_CSV_COLUMN_EXPERIENCE].tolist()
    print(f"Loaded {len(positive_experiences)} positive and {len(negative_experiences)} negative experiences.")

    if not positive_experiences and not negative_experiences:
        print("No valid experiences found in the input file.")
        return [], []

    # --- Initialize Models ---
    print(f"Loading SentenceTransformer model: '{embedding_model_name}'...")
    try:
        embedding_model = SentenceTransformer(embedding_model_name)
    except Exception as e:
        print(f"Error: Failed to load embedding model '{embedding_model_name}': {e}")
        return [], []

    print(f"Initializing LLM: '{llm_model_name}'...")
    try:
        llm = ChatOpenAI(temperature=0, model_name=llm_model_name)
        # Setup ConversationChain for summarization
        memory = ConversationBufferWindowMemory(return_messages=True, k=CONVERSATION_MEMORY_K)
        prompt = ChatPromptTemplate.from_messages([
            SystemMessage(content=SYSTEM_MESSAGE_SUMMARIZE),
            MessagesPlaceholder(variable_name="history"),
            HumanMessagePromptTemplate.from_template("{input}"), # Input will be the formatted list/summary
        ])
        conversation = ConversationChain(llm=llm, prompt=prompt, memory=memory, verbose=False)
    except Exception as e:
        print(f"Error: Failed to initialize LLM or ConversationChain: {e}")
        return [], []

    # --- Process Positive Experiences ---
    final_positive = cluster_and_summarize(
        experiences=positive_experiences,
        max_clusters=max_clusters_positive,
        label_type="Positive",
        embedding_model=embedding_model,
        conversation=conversation,
        pca_components=pca_components,
        summarization_chunk_size=summarization_chunk_size,
        merge_chunk_size=merge_chunk_size,
        semantic_threshold=semantic_threshold,
        random_state=random_state
    )

    # --- Process Negative Experiences ---
    final_negative = cluster_and_summarize(
        experiences=negative_experiences,
        max_clusters=max_clusters_negative,
        label_type="Negative",
        embedding_model=embedding_model,
        conversation=conversation,
        pca_components=pca_components,
        summarization_chunk_size=summarization_chunk_size,
        merge_chunk_size=merge_chunk_size,
        semantic_threshold=semantic_threshold,
        random_state=random_state
    )

    # --- Save Results ---
    output_path_positive = output_dir / (output_prefix_positive + DEFAULT_OUTPUT_SUFFIX)
    output_path_negative = output_dir / (output_prefix_negative + DEFAULT_OUTPUT_SUFFIX)

    save_final_experiences(output_path_positive, final_positive, "Positive")
    save_final_experiences(output_path_negative, final_negative, "Negative")

    return final_positive, final_negative


# ========== Script Entry Point ==========

def main():
    """Parses command-line arguments and runs the experience clustering and summarization."""
    parser = argparse.ArgumentParser(
        description="Cluster and summarize software testing experiences from a CSV file using embeddings and LLMs.",
        formatter_class=argparse.ArgumentDefaultsHelpFormatter
    )

    # --- Input/Output Arguments ---
    parser.add_argument(
        "input_csv",
        type=str,
        help="Path to the input CSV file containing 'Label' and 'Experience' columns."
    )
    parser.add_argument(
        "output_dir",
        type=str,
        help="Directory to save the final positive and negative experience CSV files."
    )
    parser.add_argument(
        "--out-prefix-pos",
        type=str,
        default=DEFAULT_OUTPUT_PREFIX_POS,
        help="Filename prefix for the positive experiences output CSV."
    )
    parser.add_argument(
        "--out-prefix-neg",
        type=str,
        default=DEFAULT_OUTPUT_PREFIX_NEG,
        help="Filename prefix for the negative experiences output CSV."
    )

    # --- Model Arguments ---
    parser.add_argument(
        "--embed-model",
        type=str,
        default=DEFAULT_EMBEDDING_MODEL,
        help="Name of the SentenceTransformer model for embeddings."
    )
    parser.add_argument(
        "--llm-model",
        type=str,
        default=DEFAULT_LLM_MODEL,
        help="Name of the OpenAI model for summarization."
    )

    # --- Processing Arguments ---
    parser.add_argument(
        "--max-clusters-pos",
        type=int,
        default=DEFAULT_MAX_CLUSTERS,
        help="Maximum number of clusters for positive experiences."
    )
    parser.add_argument(
        "--max-clusters-neg",
        type=int,
        default=DEFAULT_MAX_CLUSTERS,
        help="Maximum number of clusters for negative experiences."
    )
    parser.add_argument(
        "--pca-components",
        type=int,
        default=DEFAULT_PCA_COMPONENTS,
        help="Number of components for PCA dimensionality reduction."
    )
    parser.add_argument(
        "--sum-chunk-size",
        type=int,
        default=DEFAULT_SUMMARIZATION_CHUNK_SIZE,
        help="Batch size for the initial summarization pass."
    )
    parser.add_argument(
        "--merge-chunk-size",
        type=int,
        default=DEFAULT_MERGE_CHUNK_SIZE,
        help="Batch size for merging intermediate summaries."
    )
    parser.add_argument(
        "--dedup-threshold",
        type=float,
        default=DEFAULT_SEMANTIC_THRESHOLD,
        help="Cosine similarity threshold for final semantic deduplication."
    )
    parser.add_argument(
        "--seed",
        type=int,
        default=42,
        help="Random seed for reproducibility of PCA and clustering."
    )

    args = parser.parse_args()

    # Convert paths to Path objects
    input_path = Path(args.input_csv)
    output_path = Path(args.output_dir)

    # Validate input path
    if not input_path.is_file():
        print(f"Error: Input file not found or is not a file: {input_path}")
        return

    # Run the main processing function
    final_pos, final_neg = extract_and_cluster_experiences(
        input_csv_path=input_path,
        output_dir=output_path,
        max_clusters_positive=args.max_clusters_pos,
        max_clusters_negative=args.max_clusters_neg,
        embedding_model_name=args.embed_model,
        llm_model_name=args.llm_model,
        pca_components=args.pca_components,
        summarization_chunk_size=args.sum_chunk_size,
        merge_chunk_size=args.merge_chunk_size,
        semantic_threshold=args.dedup_threshold,
        output_prefix_positive=args.out_prefix_pos,
        output_prefix_negative=args.out_prefix_neg,
        random_state=args.seed
    )

    # Optionally print the final results to console
    print("\n===== Final Summarized Experiences (Positive) =====")
    if final_pos:
        for i, exp in enumerate(final_pos, start=1):
            print(f"{i}. {exp}")
    else:
        print("None")

    print("\n===== Final Summarized Experiences (Negative) =====")
    if final_neg:
        for i, exp in enumerate(final_neg, start=1):
            print(f"{i}. {exp}")
    else:
        print("None")

if __name__ == "__main__":
    main()
