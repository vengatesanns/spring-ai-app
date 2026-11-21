# 🧠 Spring AI App

A hands-on Spring Boot application demonstrating **Generative AI integration with Spring AI**. It covers multiple model providers and core Spring AI APIs through REST endpoints.

> 📺 Watch the complete tutorial playlist: [Spring AI Tutorials](https://youtube.com/playlist?list=PLagGeTxGWF3EWCm4HhBL-NDaaoAeu6BPT&si=aP0dW2cB-YbL572h)
>
> ⭐ Star the repository if you find it useful.

## 🚀 What This Project Covers

- Ollama, OpenAI, and Hugging Face chat integrations
- `ChatModel` and `ChatClient` APIs
- System and user prompts
- Message roles and prompt templates
- Inline and custom template delimiters
- Default system and user settings
- Logging and custom advisors
- Multimodal text/image requests
- Bean, list, and map structured output converters
- Typed structured output with Java records
- JDBC-backed conversational chat memory

## 🛠️ Technology Stack

- Java 17
- Spring Boot 3.5
- Spring AI 1.1.8
- Spring Web MVC
- Maven
- Lombok
- Ollama
- OpenAI
- Hugging Face
- MySQL / JDBC Chat Memory

## 📋 Prerequisites

- JDK 17 or later
- Maven 3.9+
- Ollama running locally with a downloaded model
- An OpenAI API key for OpenAI endpoints
- MySQL for persistent chat memory
- Hugging Face Text Generation Inference (optional) for local Hugging Face chat

## ⚙️ Configuration

Open `src/main/resources/application.yaml` and configure the required services.

### Ollama

```yaml
spring:
  ai:
    ollama:
      base-url: http://127.0.0.1:11434/
      chat:
        options:
          model: gemma3:latest
```

Download and run the configured model before calling Ollama endpoints:

```bash
ollama pull gemma3
ollama run gemma3
```

### OpenAI

Set the API key in the environment:

**Windows PowerShell**

```powershell
$env:OPENAI_API_KEY="your-api-key"
```

**macOS/Linux**

```bash
export OPENAI_API_KEY="your-api-key"
```

The default chat model is configured in `application.yaml`.

### MySQL Chat Memory

Create a database and update the datasource values in `application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/spring_ai
    username: your_username
    password: your_password
```

Chat memory schema initialization is enabled by the application configuration.

## ▶️ Run the Application

```bash
git clone https://github.com/vengatesanns/spring-ai-app.git
cd spring-ai-app
mvn spring-boot:run
```

The application starts at `http://localhost:8080`.

## 🔌 REST API Examples

| Area | Endpoint | Description |
|---|---|---|
| Ollama | `GET /ollama/chat?query=...` | Basic chat using Ollama |
| Ollama | `GET /custom/chat?query=...` | Custom Ollama chat client |
| OpenAI | `GET /openai/chat?query=...` | Basic chat using OpenAI |
| OpenAI | `GET /openai/chat-client?query=...` | OpenAI chat through `ChatClient` |
| OpenAI | `GET /openai/custom-chat?query=...` | Custom OpenAI chat client |
| Hugging Face | `GET /hugging-face/chat?query=...` | Chat through a local Hugging Face endpoint |
| API comparison | `GET /chatmodel?query=...` | Demonstrates `ChatModel` |
| API comparison | `GET /chatclient-api?query=...` | Demonstrates `ChatClient` |
| Prompts | `GET /prompt1?query=...` | Prompt and message-role example |
| Prompt templates | `POST /prompttemplate` | E-commerce support prompt |
| Prompt templates | `POST /prompttemplate/message-roles` | System and user message roles |
| Prompt templates | `POST /prompttemplate/custom-renderer` | Template using `<name>` delimiters |
| Prompt templates | `POST /prompttemplate/custom-template-file` | Templates loaded from `.st` resources |
| Defaults | `GET /chat-client/defaults/system?query=...` | Default system prompt |
| Defaults | `GET /chat-client/defaults/user?query=...` | Default user prompt |
| Advisors | `GET /chat-client/advisor/logger?query=...` | Logging advisor example |
| Multimodal | `GET /multimodal?query=...` | Chat with an image resource |
| Multimodal | `GET /multimodal/chat-model?query=...` | Multimodal `ChatModel` example |
| Structured output | `GET /structured_op/get_songs_info?query=...` | Typed output using Java records |
| Converters | `GET /converters/bean-converter?query=...` | Bean output conversion |
| Converters | `GET /converters/list-converter?query=...` | List output conversion |
| Converters | `GET /converters/map-converter?query=...` | Map output conversion |
| Converters | `GET /converters/listbean-converter?query=...` | List of bean output conversion |
| Chat memory | `GET /memory/chat?query=...` | JDBC-persisted chat memory; send `username` header |

Example prompt-template request:

```bash
curl -X POST http://localhost:8080/prompttemplate \
  -H "Content-Type: application/json" \
  -d '{
    "customerName":"Vengat",
    "orderId":1001,
    "product":"Laptop",
    "issue":"The delivery is delayed"
  }'
```

Example conversation-memory request:

```bash
curl "http://localhost:8080/memory/chat?query=My%20name%20is%20Vengat" \
  -H "username: vengat"
```

## 📚 Tutorial Topics

| SNo | Topic | Status |
|---:|---|:---:|
| 1 | Generative AI Introduction | ✅ |
| 2 | Spring AI Introduction | ✅ |
| 3 | Models and LLMs | ✅ |
| 4 | Download and Run Llama Locally | ✅ |
| 5 | IntelliJ, Java, Maven, and Spring Boot Setup | ✅ |
| 6 | Connect Ollama Locally | ✅ |
| 7 | Connect OpenAI with Spring AI | ✅ |
| 8 | Run Hugging Face Models | ✅ |
| 9 | `ChatModel` vs `ChatClient` | ✅ |
| 10 | Prompts and Message Roles | ✅ |
| 11 | Prompt Templates | ✅ |
| 12 | `ChatClient` Default System and User Settings | ✅ |
| 13 | Chat Client Advisors | ✅ |
| 14 | Multimodality | ✅ |
| 15 | Structured Outputs and Converters | ✅ |
| 16 | Chat Memory with JDBC | ✅ |
| 17–21 | Upcoming Spring AI topics | ⏳ |

## 🧪 Tests

```bash
mvn test
```

## 📂 Project Structure

```text
src/main/java/com/vengatx/myaiapp
├── config                         # ChatClient and chat memory configuration
├── modelproviders
│   ├── ollama                     # Ollama examples
│   ├── openai                     # OpenAI examples
│   └── huggingface                # Hugging Face example
├── topic9_chatmodelvschatClientapi
├── topic10_Prompt
├── topic11_prompttemplate
├── topic12_defaults
├── topic13_advisors
├── topic14_multimodal
├── topic15_structuredoutputs
└── topic16_chat_memory
```
