package interpreter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Parser {
  private LinkedList<Pair<String, String>> tokens;

  public Parser() {
    tokens = new LinkedList<Pair<String, String>>();
  }

  public LinkedList<Pair<String, String>> parse(String inputFile) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(inputFile));
      try {
        String line;
        // read input line by line and create tokens
        while ((line = br.readLine()) != null) {
          Pair<String, String> token = getToken(line);
          if (token != null) {
            tokens.add(token);
          }
        }
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return tokens;
  }

  private Pair<String, String> getToken(String line) {
    Construction cc = getConstructionToken(line);
    if (cc == null) return null;
    String c = cc.name();
    String a = getArgumentToken(line);
    return new Pair<String, String>(c, a);

  }

  private Construction getConstructionToken(String line) {
    String matchSpacesRgx = "[ ]+";
    // remove whitespace
    String arnoldCInstruction = line.replaceAll(matchSpacesRgx, " ");
    arnoldCInstruction = removeFirstSpace(arnoldCInstruction);
    // check if there is a valid construction
    for (Construction c : Construction.values()) {
      if (arnoldCInstruction.startsWith(c.toString())
          && (arnoldCInstruction.length() == c.toString().length()
              || (arnoldCInstruction.charAt(c.toString().length()) == ' '))) {
        return c;
      }
    }
    return null;
  }

  private String getArgumentToken(String line) {
    if (line.trim().endsWith("\"")) {
      return line.trim().substring(line.trim().indexOf("\""));
    }
    String matchSpacesRgx = "[ ]+";
    Construction c = getConstructionToken(line);
    // remove whitespace
    String arnoldCInstruction = line.replaceAll(matchSpacesRgx, " ");
    arnoldCInstruction = removeFirstSpace(arnoldCInstruction);
    if (arnoldCInstruction.length() == c.toString().length()) {
      return null;
    }
    // find index of argument in initial line
    int index = c.toString().length() + 1;
    if (arnoldCInstruction.substring(index)
        .equals(Construction.True.toString())) {
      return "1";
    }
    if (arnoldCInstruction.substring(index)
        .equals(Construction.False.toString())) {
      return "0";
    }
    return arnoldCInstruction.substring(index).trim();
  }

  private String removeFirstSpace(String s) {
    if (s != null && s.length() != 0) {
      if (s.charAt(0) == ' ') {
        s = s.replaceFirst(" ", "");
      }
    }
    return s;
  }

  public LinkedList<Pair<String, String>> getTokens() {
    return tokens;
  }
}
