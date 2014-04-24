package me.servercaster.main.converter.code;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class CommandConverter extends SpecialCodeConverter {

    public CommandConverter() {
        super(1);
    }    
    
    @Override
    protected String getKeyword() {
        return "COMMAND";
    }

    @Override
    public void doAction(String argument) {
        getJSONSaver().command("/" + argument);
    }
}
