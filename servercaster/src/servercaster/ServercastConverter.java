package servercaster;

import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje), Floris Huizinga (Flexo013)
 */
public class ServercastConverter {

    String getProperMessage(String message) {
        
        FancyMessage fm = new FancyMessage();
        final int normalText = 0;
        final int bracketText = 1;
        final int codeText = 2;
        final int urlText = 3;
        final int waitForInput = 4;
        final int commandText = 5;
        String saver = "";
        ServercastMessage currentMessage = null;
        int currentText = normalText;
        boolean url = false;
        boolean command = false;
        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            switch (currentText) {
                case normalText:
                    if (currentChar == '&') {
                        fm.then(saver);
                        saver = "";
                        currentText = codeText;
                    } else {
                        saver = saver + currentChar;
                    }
                    break;
                case bracketText:
                    if (currentChar == '}') {
                        currentMessage.setText(saver);
                        saver = "";
                        currentText = normalText;
                        currentMessage.getMessage(fm);
                        currentMessage = null;
                    } else {
                        saver = saver + currentChar;
                    }
                    break;
                case codeText:
                    if (currentChar == ';') {
                        if (currentMessage == null) {
                            currentMessage = new ServercastMessage();
                        }
                        if (saver.equals("URL")) {
                            url = true;
                            currentText = waitForInput;
                            saver = "";
                            break;
                        }
                        if (saver.equals("COMMAND")) {
                            command = true;
                            currentText = waitForInput;
                            saver = "";
                            break;
                        }
                        currentMessage.addAdition(saver);
                        saver = "";
                        currentText = waitForInput;
                    } else {
                        saver = saver + currentChar;
                    }
                    break;
                case urlText:
                    if (currentChar == '"') {
                        currentMessage.addUrl(saver);
                        saver = "";
                        currentText = waitForInput;
                    } else {
                        saver = saver + currentChar;
                    }
                    break;
                case commandText:
                    if (currentChar == '}') {
                        currentMessage.addCommand("/" + saver);
                        saver = "";
                        currentText = waitForInput;
                    } else {
                        saver = saver + currentChar;
                    }
                    break;
                case waitForInput:
                    if (currentChar == '{') {
                        if (url) {
                            currentText = urlText;
                            url = false;
                            i++;
                        } else if (command) {
                            currentText = commandText;
                            command = false;
                        } else {
                            currentText = bracketText;
                        }
                    } else if (currentChar == '&') {
                        currentText = codeText;
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }
        if (!saver.equals("")) {
            fm.then(saver);
        }
        return fm.toJSONString();
    }
}
