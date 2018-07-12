package rs.fon.inteligentni.rest.exception.mapper;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import rs.fon.inteligentni.rest.exception.SpotifyProxyRuntimeException;

@ControllerAdvice
public class ExceptionMapper {

    private final Logger logger = LoggerFactory.getLogger(ExceptionMapper.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SpotifyProxyRuntimeException.class)
    @ResponseBody
    ErrorMessage handleIvalidArguments(HttpServletRequest req, Exception ex) {
        logger.error("RuntimeException: ", ex);
        return new ErrorMessage(ex.getMessage());
    }

    class ErrorMessage {

        private String error;

        public ErrorMessage() {

        }

        ErrorMessage(String errorMessage) {
            this.error = errorMessage;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}