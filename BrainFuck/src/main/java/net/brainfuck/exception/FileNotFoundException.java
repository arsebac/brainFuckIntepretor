package net.brainfuck.exception;

/**
 * Created by Alexandre Hiltcher on 27/09/2016.
 */
public class FileNotFoundException extends Exception{

    public FileNotFoundException(){
        super("File not found.");
    }

    public FileNotFoundException(String message){
        super(message);
    }

}