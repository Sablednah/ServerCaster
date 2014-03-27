package broadcaster;

import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class BroadcastConverter {
    public BroadcastConverter() {
    }

    String getProperMessage(String message, String prefix) {
        if (!prefix.equals("")) {
            prefix = prefix + " ";
        }
        FancyMessage fm = new FancyMessage(prefix);
        final int normalText = 0;
        final int bracketText = 1;
        final int codeText = 2;
        final int urlText = 3;
        final int waitForInput = 4;
        final int commandText = 5;
        String saver = "";
        BroadcastMessage currentMessage = null;
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
                            currentMessage = new BroadcastMessage();
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
