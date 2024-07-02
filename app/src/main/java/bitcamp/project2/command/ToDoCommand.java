package bitcamp.project2.command;

import bitcamp.project2.util.Highlight;
import bitcamp.project2.util.LinkedList;
import bitcamp.project2.util.Prompt;
import bitcamp.project2.vo.ToDo;

public class ToDoCommand {

  LinkedList toDoList = new LinkedList();


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
        this.listToDo();
        break;
      case "변경":
        this.updateToDo();
        break;
      case "삭제":
        this.deleteToDo();
        break;
    }
  }

  private void deleteToDo() {
    int toDoNo = Prompt.inputInt("삭제할 항목?");
    ToDo deletedToDo = (ToDo) toDoList.get(toDoList.indexOf(new ToDo(toDoNo)));
    if (deletedToDo != null) {
      toDoList.remove(toDoList.indexOf(deletedToDo));
      System.out.printf("%d번 카테고리을 삭제 했습니다.\n", deletedToDo.getNo());
    } else {
      System.out.println("없는 카테고리입니다.");
    }
  }

  private void updateToDo() {
    int toDoNo = Prompt.inputInt("변경할 항목?");
    ToDo toDo = (ToDo) toDoList.get(toDoList.indexOf(new ToDo(toDoNo)));
    if (toDo == null) {
      System.out.println("없는 항목입니다.");
      return;
    }

    toDo.setTitle(Prompt.input("항목명(%s)?", toDo.getTitle()));
    toDo.setMemo(Prompt.input("메모(%s)?", toDo.getMemo()));
    System.out.println("변경 했습니다.");
  }

  private void viewToDo() {
    int toDoNo = Prompt.inputInt("조회할 항목?");
    ToDo toDo = (ToDo) toDoList.get(toDoList.indexOf(new ToDo(toDoNo)));
    if (toDo == null) {
      System.out.println("없는 항목입니다.");
      return;
    }

    System.out.printf("항목명 : %s\n", toDo.getTitle());
    System.out.printf("메모 : %s\n", toDo.getMemo());
  }

  private void listToDo() {
    System.out.println("번호\t항목명\t\t\t메모");
    for (Object obj : toDoList.toArray()) {
      ToDo toDo = (ToDo) obj;
      System.out.printf("%d\t\t%s\t\t%s\n",
          toDo.getNo(), toDo.getTitle(), toDo.getMemo());
    }
  }

  private void addToDo() {
    ToDo toDo = new ToDo();
    toDo.setTitle(Prompt.input("항목명?"));
    toDo.setNo(ToDo.getNextSeqNo());
    toDo.setMemo(Prompt.input("메모?"));

    toDoList.add(toDo);
  }

  public LinkedList getToDoList() {
    return this.toDoList;
  }

}
