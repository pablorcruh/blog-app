package ec.com.blog.domain.exceptions;

import org.springframework.http.HttpStatus;

public class BlogDomainException extends RuntimeException{

    private final HttpStatus httpsStatus;

    public BlogDomainException(String message, HttpStatus httpsStatus) {
        super(message);
        this.httpsStatus = httpsStatus;
    }

    public BlogDomainException(String message, HttpStatus httpsStatus, Exception innerException)
    {
        super(message, innerException);
        this.httpsStatus = httpsStatus;
    }

    public BlogDomainException(String message) {
        super(message);
        this.httpsStatus = HttpStatus.NOT_FOUND;
    }
}
