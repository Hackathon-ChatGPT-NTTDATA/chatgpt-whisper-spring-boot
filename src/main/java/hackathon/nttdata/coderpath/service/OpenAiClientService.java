package hackathon.nttdata.coderpath.service;

import hackathon.nttdata.coderpath.model.ChatGptResponse;
import hackathon.nttdata.coderpath.model.ChatRequest;
import hackathon.nttdata.coderpath.model.TranscriptionRequest;
import hackathon.nttdata.coderpath.model.WhisperTranscriptionResponse;

public interface OpenAiClientService {

	ChatGptResponse chat(ChatRequest chatRequest);

	WhisperTranscriptionResponse createTranscription(TranscriptionRequest transcriptionRequest);
}