package bitcamp.project2.command;

import bitcamp.project2.util.Highlight;
import bitcamp.project2.util.LinkedList;
import bitcamp.project2.util.Prompt;
import bitcamp.project2.vo.Category;
import bitcamp.project2.vo.ToDo;

import java.util.Calendar;
import java.util.Date;

public class ToDoCommand {

  LinkedList allToDoList = new LinkedList();
  LinkedList completeToDoList = new LinkedList();
  LinkedList categoryList;

  public ToDoCommand(LinkedList categoryList) {
    this.categoryList = categoryList;
  }

  public void executeToDoCommand(String command) {
    Highlight.menuHighlight(command, "blue");

    switch (command) {
      case "등록":
        this.addToDo();
        break;
      case "조회":
        this.viewToDo();
        break;
      case "목록":
        this.choice();
        break;
      case "변경":
        this.updateToDo();
        break;
      case "삭제":
        this.deleteToDo();
        break;
      case "완료 항목":
        this.completedListToDo();
        break;
    }
  }

  private void deleteToDo() {
    int toDoNo = Prompt.inputInt("삭제할 항목?");
    ToDo deletedToDo = (ToDo) allToDoList.get(allToDoList.indexOf(new ToDo(toDoNo)));
    if (deletedToDo != null) {
      allToDoList.remove(allToDoList.indexOf(deletedToDo));
      System.out.printf("%d번 할 일을 삭제했습니다.\n", deletedToDo.getNo());
    } else {
      System.out.println("없는 항목입니다.");
    }
  }

  private void updateToDo() {
    int toDoNo = Prompt.inputInt("변경할 항목?");
    ToDo toDo = (ToDo) allToDoList.get(allToDoList.indexOf(new ToDo(toDoNo)));
    if (toDo == null) {
      System.out.println("없는 항목입니다.");
      return;
    }

    toDo.setTitle(Prompt.input("항목명(%s)?", toDo.getTitle()));
    toDo.setMemo(Prompt.input("메모(%s)?", toDo.getMemo()));
    printCategory();
    int categoryIndex = Prompt.inputInt("분류?") - 1;
    Category category = (Category) categoryList.get(categoryIndex);
    if(category == null) {
      System.out.println("없는 카테고리입니다.");
      return;
    }
    toDo.setCategory(category);
    levelSet(toDo);
    System.out.println("변경했습니다.");
  }

  private void viewToDo() {
    int toDoNo = Prompt.inputInt("조회할 항목?");
    ToDo toDo = (ToDo) allToDoList.get(allToDoList.indexOf(new ToDo(toDoNo)));
    if (toDo == null) {
      System.out.println("없는 항목입니다.");
      return;
    }

    System.out.printf("항목명: %s\n", toDo.getTitle());
    System.out.printf("메모: %s\n", toDo.getMemo());
    System.out.printf("카테고리: %s\n", toDo.getCategory().getTitle());
    System.out.printf("중요도: %s\n", toDo.getLevel());
    System.out.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", toDo.getCreatedDate());

  }

  private void choice() {
    String answer = Prompt.input("완료 목록 보시겠습니까?(y/n)");
    if (answer.equalsIgnoreCase("y")) {
      listToDo(allToDoList);
    } else {
      listToDo(getCurrentList(allToDoList));
    }
  }


  private void listToDo(LinkedList allToDoList) {

    while (true) {
      viewList(allToDoList);

      String command = Prompt.input("[V] 표시를 변경할 번호(0:이전 / C:달력)?");
      if (command.equalsIgnoreCase("C")) {
        printCalender(new Date());
        continue;
      } else if (command.equals("0")) {
        break;
      }

      try {
        int toDoNo = Integer.parseInt(command);
        ToDo toDo = (ToDo) this.allToDoList.get(this.allToDoList.indexOf(new ToDo(toDoNo)));
        if (toDo == null) {
          System.out.println("없는 항목입니다.");
          return;
        }
        toggleToDo(toDo);
        if (isChecked(toDo)) {
          completeToDoList.add(toDo);
          System.out.printf("'%s' 완료\n", toDo.getTitle());
        } else {
          completeToDoList.remove(completeToDoList.indexOf(toDo));
          System.out.printf("'%s' 완료 해제\n", toDo.getTitle());
        }

      } catch (NumberFormatException ex) {
        System.out.println("숫자로 항목 번호를 입력하세요.");
      }
    }

  }



  public LinkedList getCurrentList(LinkedList allToDoList) {
    LinkedList currentList = new LinkedList();
    for (int i = 0; i < allToDoList.size(); i++) {
      ToDo todo = (ToDo) allToDoList.get(i);
      if (!todo.getComplete()) {
        currentList.add(allToDoList.get(i));
      }
    }
    return currentList;
  }


  public void completedListToDo() {
    while (true) {
      viewList(completeToDoList);

      String command = Prompt.input("복구할 항목 번호(0:이전 / C:달력)?");
      if (command.equalsIgnoreCase("C")) {
        printCalender(new Date());
        continue;
      } else if (command.equals("0")) {
        break;
      }

      try {
        int toDoNo = Integer.parseInt(command);
        ToDo toDo = (ToDo) completeToDoList.get(completeToDoList.indexOf(new ToDo(toDoNo)));
        if (toDo == null) {
          System.out.println("없는 항목입니다.");
          return;
        }
        toggleToDo(toDo);
        if (!toDo.getComplete()) {
          completeToDoList.remove(completeToDoList.indexOf(toDo));
          System.out.printf("'%s' 완료 해제\n", toDo.getTitle());
        } else {
          System.out.printf("'%s' 완료\n", toDo.getTitle());
        }

      } catch (NumberFormatException ex) {
        System.out.println("숫자로 항목 번호를 입력하세요.");
      }
    }
  }

  private void addToDo() {
    if(categoryList.size() == 0) {
      System.out.println("카테고리 등록이 필요합니다.");
      return;
    }

    ToDo toDo = new ToDo();
    toDo.setTitle(Prompt.input("항목명?"));
    toDo.setNo(ToDo.getNextSeqNo());
    toDo.setMemo(Prompt.input("메모?"));
    printCategory();
    int categoryIndex = Prompt.inputInt("분류?") - 1;
    Category category = (Category) categoryList.get(categoryIndex);
    if(category == null) {
      System.out.println("없는 카테고리입니다.");
      return;
    }
    toDo.setCategory(category);

    levelSet(toDo);

    toDo.setCreatedDate(new Date());

    allToDoList.add(toDo);
    System.out.println("할 일이 등록되었습니다.");
  }

  public boolean isChecked(ToDo toDo) {
    return toDo.getComplete();
  }

  public void toggleToDo(ToDo toDo) {
    toDo.setComplete(!toDo.getComplete());
  }

  public LinkedList getAllToDoList() {
    return this.allToDoList;
  }

  public LinkedList getCompleteToDoList() {
    return this.completeToDoList;
  }


  public void printCalender(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);

    System.out.println();
    System.out.println("\t\t" +year + "년 " + (month + 1) + "월 달력");

    calendar.set(Calendar.DAY_OF_MONTH, 1);

    System.out.println(" 일  월  화  수  목  금  토");


    int startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);


    for (int i = 1; i < startDayOfWeek; i++) {
      System.out.print("    ");
    }


    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

    for (int day = 1; day <= daysInMonth; day++) {
      System.out.printf("%3d ", day);


      if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
        System.out.println();
      }

      calendar.add(Calendar.DAY_OF_MONTH, 1);
    }

    if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
      System.out.println();
    }
  }

  public void viewList(LinkedList toDoList) {
    String boldAnsi = "\033[1m";
    String redAnsi = "\033[31m";
    String blueAnsi = "\033[34m";
    String yellowAnsi = "\033[33m";
    String pinkAnsi = "\033[35m";
    String greenAnsi = "\033[38;5;2m";


    String resetAnsi = "\033[0m";

    System.out.println(boldAnsi + yellowAnsi + "┌──────┬─────┬──────────────────────┬──────────────────────┬────────────┬────────────┬────────────┐" + resetAnsi);
    System.out.println(boldAnsi + yellowAnsi + "│ 번호 │ [V] │        항목명        │         메모         │  카테고리  │   중요도   │   작성일   │"+ resetAnsi);
    System.out.println(boldAnsi + yellowAnsi + "└──────┴─────┴──────────────────────┴──────────────────────┴────────────┴────────────┴────────────┘"+ resetAnsi);
    String complete;
    for (Object obj : toDoList.toArray()) {
      ToDo toDo = (ToDo) obj;
      if (toDo.getComplete()) {
        complete = "[V]";
      } else {
        complete = "[ ]";
      }
      System.out.printf("\033[33m│\033[0m %-4d \033[33m│\033[0m %-4s\033[33m│\033[0m %-20s \033[33m│\033[0m %-20s \033[33m│\033[0m %-10s \033[33m│\033[0m %-10s \033[33m│\033[0m %tY-%7$tm-%7$td \033[33m│\033[0m\n",
          toDo.getNo(), complete, toDo.getTitle(), toDo.getMemo(), toDo.getCategory().getTitle(), toDo.getLevel(), toDo.getCreatedDate());
      System.out.println(yellowAnsi + "└──────┴─────┴──────────────────────┴──────────────────────┴────────────┴────────────┴────────────┘"+ resetAnsi);
    }
    System.out.println();
  }

  private void printCategory() {
    int count = 1;
    for (Object obj : categoryList.toArray()) {
      Category category = (Category) obj;
      System.out.printf("%d. %s\n", count++, category.getTitle());
    }
  }

  private void levelSet(ToDo toDo) {
    int answer;
    while (true) {
      answer = Prompt.inputInt("중요도?(1~5)");
      if (answer >= 1 && answer <= 5) {
        switch (answer) {
          case 1:
            toDo.setLevel("★");
            break;
          case 2:
            toDo.setLevel("★★");
            break;
          case 3:
            toDo.setLevel("★★★");
            break;
          case 4:
            toDo.setLevel("★★★★");
            break;
          case 5:
            toDo.setLevel("★★★★★");
            break;
        }
        break;
      } else {
        System.out.println("1 ~ 5 중에서 다시 입력해주세요");
      }
    }
  }

}
