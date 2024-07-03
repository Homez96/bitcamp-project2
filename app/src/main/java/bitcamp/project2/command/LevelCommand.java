package bitcamp.project2.command;

import bitcamp.project2.util.Highlight;
import bitcamp.project2.util.LinkedList;
import bitcamp.project2.util.Prompt;
import bitcamp.project2.vo.Level;

public class LevelCommand {

    LinkedList levelList = new LinkedList();

    public void executeLevelCommand(String command) {
        Highlight.menuHighlight(command, "blue");
        switch (command) {
            case "등록":
                this.addLevel();
                break;
            case "조회":
                this.viewLevel();
                break;
            case "목록":
                this.listLevel();
                break;
            case "변경":
                this.updateLevel();
                break;
            case "삭제":
                this.deleteLevel();
                break;
        }
    }

    private void deleteLevel() {
        int levelNo = Prompt.inputInt("레벨 번호?");
        Level deletedLevel = (Level) levelList.get(levelList.indexOf(new Level(levelNo, "")));
        if (deletedLevel != null) {
            levelList.remove(levelList.indexOf(deletedLevel));
            System.out.printf("%d번 레벨을 삭제 했습니다.\n", deletedLevel.getNo());
        } else {
            System.out.println("없는 레벨입니다.");
        }
    }


    private void updateLevel() {
        int levelNo = Prompt.inputInt("레벨 번호?");
        Level level = (Level) levelList.get(levelList.indexOf(new Level(levelNo, "")));
        if (level == null) {
            System.out.println("없는 레벨입니다.");
            return;
        }

        level.setTitle(Prompt.input("레벨명(%s)?", level.getTitle()));
        level.setTransactionType(Prompt.input("메모(%s)?", level.getTransactionType()));
        System.out.println("변경 했습니다.");
    }

    private void viewLevel() {
        int levelNo = Prompt.inputInt("레벨 번호?");
        Level level = (Level) levelList.get(levelList.indexOf(new Level(levelNo, "")));
        if (level == null) {
            System.out.println("없는 레벨입니다.");
            return;
        }

        System.out.printf("레벨명 : %s\n", level.getTitle());
        System.out.printf("메모 : %s\n", level.getTransactionType());
    }

    private void listLevel() {
        System.out.println("번호\t레벨명\t메모");
        for (Object obj : levelList.toArray()) {
            Level level = (Level) obj;
            System.out.printf("%d\t\t%s\t\t%s\n",
                    level.getNo(), level.getTitle(), level.getTransactionType());
        }
    }

    private void addLevel() {
        Level level = new Level();
        level.setTitle(Prompt.input("레벨명?"));
        level.setNo(Level.getNextSeqNo());
        level.setTransactionType(Prompt.input("메모?"));

        levelList.add(level);

        // 디버깅을 위한 리스트 출력
        for (Object obj : levelList.toArray()) {
            Level l = (Level) obj;
            System.out.printf("레벨: %d, %s, %s\n", l.getNo(), l.getTitle(), l.getTransactionType());
        }
    }

    public LinkedList getIncomeLevelList() {
        LinkedList incomeLevelList = new LinkedList();

        for (Object obj : levelList.toArray()) {
            Level level = (Level) obj;
            if (level.getTransactionType().equals("수입")) {
                incomeLevelList.add(level);
            }
        }
        return incomeLevelList;
    }

    public LinkedList getExpenseLevelList() {
        LinkedList expenseLevelList = new LinkedList();

        for (Object obj : levelList.toArray()) {
            Level level = (Level) obj;
            if (level.getTransactionType().equals("지출")) {
                expenseLevelList.add(level);
            }
        }
        return expenseLevelList;
    }

    public LinkedList getLevelList() {
        return this.levelList;
    }
}
