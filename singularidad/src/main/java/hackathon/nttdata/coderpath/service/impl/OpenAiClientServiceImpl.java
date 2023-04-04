package hackathon.nttdata.coderpath.service.impl;

import hackathon.nttdata.coderpath.service.OpenAiClientService;
import hackathon.nttdata.coderpath.client.OpenAiClient;
import hackathon.nttdata.coderpath.client.OpenAiClientConfig;
import hackathon.nttdata.coderpath.model.ChatGptRequest;
import hackathon.nttdata.coderpath.model.ChatGptResponse;
import hackathon.nttdata.coderpath.model.ChatRequest;
import hackathon.nttdata.coderpath.model.Message;
import hackathon.nttdata.coderpath.model.TranscriptionRequest;
import hackathon.nttdata.coderpath.model.WhisperTranscriptionRequest;
import hackathon.nttdata.coderpath.model.WhisperTranscriptionResponse;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAiClientServiceImpl implements OpenAiClientService {

	private final OpenAiClient openAIClient;
	private final OpenAiClientConfig openAiClientConfig;

	private final static String ROLE_USER = "user";

	@Override
	public ChatGptResponse chat(ChatRequest chatRequest) {
		Message message = Message.builder().role(ROLE_USER).content(chatRequest.getQuestion()).build();
		ChatGptRequest chatGPTRequest = ChatGptRequest.builder().model(openAiClientConfig.getModel())
				.messages(Collections.singletonList(message)).build();
		return openAIClient.chat(chatGPTRequest);
	}

	@Override
	public WhisperTranscriptionResponse createTranscription(TranscriptionRequest transcriptionRequest) {
		WhisperTranscriptionRequest whisperTranscriptionRequest = WhisperTranscriptionRequest.builder()
				.model(openAiClientConfig.getAudioModel()).file(transcriptionRequest.getFile()).build();
		return openAIClient.createTranscription(whisperTranscriptionRequest);
	}
}
