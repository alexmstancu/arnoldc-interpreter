package interpreter;

public enum Construction {
  BeginMain("IT'S SHOWTIME"), 
  EndMain("YOU HAVE BEEN TERMINATED"),

  False("@I LIED"), 
  True("@NO PROBLEMO"),

  If("BECAUSE I'M GOING TO SAY PLEASE"), 
  Else("BULLSHIT"), 
  EndIf("YOU HAVE NO RESPECT FOR LOGIC"),

  While("STICK AROUND"), 
  EndWhile("CHILL"),

  PlusOperator("GET UP"), 
  MinusOperator("GET DOWN"),

  MultiplicationOperator("YOU'RE FIRED"), 
  DivisionOperator("HE HAD TO SPLIT"),
  ModuloOperator("I LET HIM GO"),

  EqualTo("YOU ARE NOT YOU YOU ARE ME"), 
  GreaterThan("LET OFF SOME STEAM BENNET"),

  Or("CONSIDER THAT A DIVORCE"), 
  And("KNOCK KNOCK"),

  DeclareInt("HEY CHRISTMAS TREE"), 
  SetInitialValue("YOU SET US UP"),

  AssignVariable("GET TO THE CHOPPER"), 
  SetValue("HERE IS MY INVITATION"), 
  EndAssignVariable("ENOUGH TALK"),

  Print("TALK TO THE HAND");

  private String text;

  private Construction(String t) {
    text = t;
  }

  public String toString() {
    return text;
  }
}
