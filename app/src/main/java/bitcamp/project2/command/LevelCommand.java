package bitcamp.project2.command;

import bitcamp.project2.vo.Level;

public class LevelCommand {

    public void executeLevelCommand(String command) {
        switch (command) {
            case "등록":
                registerLevel();
                break;
            case "목록":
                listLevels();
                break;
            case "조회":
                viewLevel();
                break;
            case "변경":
                updateLevel();
                break;
            case "삭제":
                deleteLevel();
                break;
            default:
                System.out.println("지원하지 않는 명령입니다.");
        }
    }

    private void registerLevel() {
        // Implement registration logic here
        System.out.println("레벨을 등록합니다.");
    }

    private void listLevels() {
        // Implement listing logic here
        System.out.println("레벨 목록을 출력합니다.");
    }

    private void viewLevel() {
        // Implement view logic here
        System.out.println("레벨을 조회합니다.");
    }

    private void updateLevel() {
        // Implement update logic here
        System.out.println("레벨을 변경합니다.");
    }

    private void deleteLevel() {
        // Implement delete logic here
        System.out.println("레벨을 삭제합니다.");
    }
}
