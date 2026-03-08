package de.nkilders;

public class Main {

  public static void main(String[] args) throws Exception {
    System.out.println("[App] main() called");

    for (var i = 0; i < 10; i++) {
      System.out.println("[App] Loop iteration: " + i);
      Thread.sleep(500);
    }
  }
}
