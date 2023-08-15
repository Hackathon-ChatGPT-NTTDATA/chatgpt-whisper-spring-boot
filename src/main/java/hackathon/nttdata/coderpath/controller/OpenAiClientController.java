package hackathon.nttdata.coderpath.controller;

import hackathon.nttdata.coderpath.service.OpenAiClientService;
import hackathon.nttdata.coderpath.model.ChatGptResponse;
import hackathon.nttdata.coderpath.model.ChatRequest;
import hackathon.nttdata.coderpath.model.TranscriptionRequest;
import hackathon.nttdata.coderpath.model.WhisperTranscriptionResponse;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@RequestMapping(value = "/api/v1")
@Slf4j
public class OpenAiClientController {

	private final OpenAiClientService openAiClientService;

	@PostMapping(value = "/chat", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ChatGptResponse chat(@RequestBody ChatRequest chatRequest) {
		return openAiClientService.chat(chatRequest);
	}

	@PostMapping(value = "/transcription", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public WhisperTranscriptionResponse createTranscription(@ModelAttribute TranscriptionRequest transcriptionRequest) {
		return openAiClientService.createTranscription(transcriptionRequest);
	}

	@ExceptionHandler({ SocketTimeoutException.class })
	public ResponseEntity<Map<String, String>> handleException() {
		log.error("Socket TimeOut, Response Time is too long.");
		Map<String, String> resultException = new HashMap<>();
		resultException.put("cod", "42201");
		resultException.put("msg", "Socket TimeOut, Response Time is too long.");
		return ResponseEntity.status(422).body(resultException);
	}

}
