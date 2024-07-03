package bitcamp.project2.util;

public class Highlight {

  public static void menuHighlight(String menuTitle, String color) {
    String boldAnsi = "\033[1m";
    String yellowAnsi = "\033[33m";
    String purpleAnsi = "\033[35m";

    String resetAnsi = "\033[0m";


    System.out.println(boldAnsi + "**********" + resetAnsi);
    System.out.println(boldAnsi + menuTitle + resetAnsi);
    System.out.println(boldAnsi + "**********" + resetAnsi);
  }
}
