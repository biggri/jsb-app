package pro.siberians.jsb.app;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@org.springframework.stereotype.Controller
@RequestMapping(path = "/api")
public class Controller {

	String currentFile = "";
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/image")
	@ResponseBody
	public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file) {
		try {
			List<String> result = ImageToASCII.getImage(file.getBytes());
			return new ResponseEntity<List<String>>(result, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Failure", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	

}
