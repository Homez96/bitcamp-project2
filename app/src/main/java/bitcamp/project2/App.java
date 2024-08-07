/*
 * This source file was generated by the Gradle 'init' task
 */
package bitcamp.project2;

import bitcamp.project2.command.CategoryCommand;
import bitcamp.project2.command.ToDoCommand;
import bitcamp.project2.util.Highlight;
import bitcamp.project2.util.Prompt;

public class App {

    CategoryCommand categoryCommand = new CategoryCommand();
    ToDoCommand toDoCommand = new ToDoCommand(categoryCommand.getCategoryList());


    String[] mainMenus = new String[]{"할 일", "카테고리", "종료"};
    String[][] subMenus = {
        {"등록", "목록", "조회", "변경", "삭제", "완료 항목"},
        {"등록", "목록", "조회", "변경", "삭제"}
    };

    public static void main(String[] args) {
        new App().execute();
    }

    void execute() {
        printMenu();

        String command;
        while (true) {
            try {
                command = Prompt.input("메인>");

                if (command.equals("menu")) {
                    printMenu();

                } else {
                    int menuNo = Integer.parseInt(command);
                    String menuTitle = getMenuTitle(menuNo, mainMenus);
                    if (menuTitle == null) {
                        System.out.println("유효한 메뉴 번호가 아닙니다.");
                    } else if (menuTitle.equals("종료")) {
                        break;
                    } else {
                        processMenu(menuTitle, subMenus[menuNo - 1]);
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }

        System.out.println("종료합니다.");

        Prompt.close();
    }

    void printMenu() {
        String boldAnsi = "\033[1m";
        String resetAnsi = "\033[0m";
        String purpleAnsi = "\033[35m";

        String line = boldAnsi + purpleAnsi + "─────────────────────────────────────────────────" + resetAnsi;

        System.out.println(boldAnsi + purpleAnsi + "┌───────────────────────────────────────────────┐" + resetAnsi);
        System.out.println(boldAnsi + purpleAnsi + "│                  To Do List                   │" + resetAnsi);
        System.out.println(boldAnsi + purpleAnsi + "└───────────────────────────────────────────────┘" + resetAnsi);

        for (int i = 0; i < mainMenus.length; i++) {
                System.out.printf("%d. %s\n", (i + 1), mainMenus[i]);
        }

        System.out.println(boldAnsi + line + resetAnsi);
    }



    void printSubMenu(String menuTitle, String[] menus) {
        Highlight.menuHighlight(menuTitle, "yellow");
        for (int i = 0; i < menus.length; i++) {
            System.out.printf("%d. %s   ", (i + 1), menus[i]);
        }
        System.out.println("\n9. 이전");
    }

    boolean isValidateMenu(int menuNo, String[] menus) {
        return menuNo >= 1 && menuNo <= menus.length;
    }

    String getMenuTitle(int menuNo, String[] menus) {
        return isValidateMenu(menuNo, menus) ? menus[menuNo - 1] : null;
    }

    void processMenu(String menuTitle, String[] menus) {
        printSubMenu(menuTitle, menus);
        while (true) {
            String command = Prompt.input(String.format("메인/%s>", menuTitle));
            if (command.equals("menu")) {
                printSubMenu(menuTitle, menus);
                continue;
            } else if (command.equals("9")) {
                break;
            }

            try {
                int menuNo = Integer.parseInt(command);
                String subMenuTitle = getMenuTitle(menuNo, menus);
                if (subMenuTitle == null) {
                    System.out.println("유효한 메뉴 번호가 아닙니다.");
                } else {
                    switch (menuTitle) {
                        case "할 일":
                            toDoCommand.executeToDoCommand(subMenuTitle);
                            break;
                        case "카테고리":
                            categoryCommand.executeCategoryCommand(subMenuTitle);
                            break;
                        default:
                            System.out.printf("%s 메뉴의 명령을 처리할 수 없습니다.\n", menuTitle);
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }
    }

}
