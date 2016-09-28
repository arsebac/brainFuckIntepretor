package net.brainfuck.exception;

/**
 * Created by Alexandre Hiltcher on 27/09/2016.
 */
public class CharacterException extends Exception {

    public CharacterException(){
        super("Character exception.");
    }
    public CharacterException(String message){
        super(message);
    }

}