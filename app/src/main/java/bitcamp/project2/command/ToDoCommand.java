package bitcamp.project2.command;

import bitcamp.project2.util.Highlight;
import bitcamp.project2.util.LinkedList;
import bitcamp.project2.util.Prompt;
import bitcamp.project2.vo.ToDo;

import java.util.Date;

public class ToDoCommand {

  LinkedList currentToDoList = new LinkedList();
  LinkedList completeToDoList = new LinkedList();


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
    ToDo deletedToDo = (ToDo) currentToDoList.get(currentToDoList.indexOf(new ToDo(toDoNo)));
    if (deletedToDo != null) {
      currentToDoList.remove(currentToDoList.indexOf(deletedToDo));
      System.out.printf("%d번 카테고리을 삭제 했습니다.\n", deletedToDo.getNo());
    } else {
      System.out.println("없는 카테고리입니다.");
    }
  }

  private void updateToDo() {
    int toDoNo = Prompt.inputInt("변경할 항목?");
    ToDo toDo = (ToDo) currentToDoList.get(currentToDoList.indexOf(new ToDo(toDoNo)));
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
    ToDo toDo = (ToDo) currentToDoList.get(currentToDoList.indexOf(new ToDo(toDoNo)));
    if (toDo == null) {
      System.out.println("없는 항목입니다.");
      return;
    }

    System.out.printf("항목명 : %s\n", toDo.getTitle());
    System.out.printf("메모 : %s\n", toDo.getMemo());
    System.out.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", toDo.getCreatedDate());
  }

  private void listToDo() {

    while (true) {
      viewList();
      int toDoNo = Prompt.inputInt("체크 상태를 변경할 번호(0 이전)?");
      if (toDoNo == 0) {
        break;
      }
      ToDo toDo = (ToDo) currentToDoList.get(currentToDoList.indexOf(new ToDo(toDoNo)));
      if (toDo == null) {
        System.out.println("없는 항목입니다.");
        return;
      }
      toggleToDo(toDo);
      if (toDo.getComplete()) {
        completeToDoList.add(toDo);
        System.out.printf("'%s' 완료\n", toDo.getTitle());
      } else {
        System.out.printf("'%s' 완료 해제\n", toDo.getTitle());
      }
    }
  }

  private void addToDo() {
    ToDo toDo = new ToDo();
    toDo.setTitle(Prompt.input("항목명?"));
    toDo.setNo(ToDo.getNextSeqNo());
    toDo.setMemo(Prompt.input("메모?"));
    toDo.setCreatedDate(new Date());

    currentToDoList.add(toDo);
  }

  public void toggleToDo(ToDo toDo) {
    toDo.setComplete(!toDo.getComplete());
  }

  public void viewList() {
    System.out.println("번호\t[V]\t\t항목명\t\t메모\t\t작성일");
    System.out.println("----------------------------------------");
    for (Object obj : currentToDoList.toArray()) {
      ToDo toDo = (ToDo) obj;
      System.out.printf("%d.\t\t%b\t\t%s\t\t\t%s\t\t%tY-%5$tm-%5$td\n",
          toDo.getNo(), toDo.getComplete(), toDo.getTitle(), toDo.getMemo(), toDo.getCreatedDate());
    }
  }


  public LinkedList getCurrentToDoList() {
    return this.currentToDoList;
  }

  public LinkedList getCompleteToDoList() {
    return this.completeToDoList;
  }


}
