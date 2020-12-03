package pro.siberians.jsb.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/")
public class Controller {

	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "/health", method = RequestMethod.GET)
	public ResponseEntity getStatus() {
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
}
