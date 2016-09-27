package brainfuck.common.exception;

/**
 * Created by Alexandre Hiltcher on 27/09/2016.
 */
public class Exception extends Throwable{

    public Exception(){
        super();
    }

    public Exception(String message){
        super(message);
    }

    public Exception(String message, Throwable cause){
        super(message, cause);
    }

    public Exception(Throwable cause){
        super(cause);
    }
}
