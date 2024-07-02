package bitcamp.project2.command;

import bitcamp.project2.vo.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelCommand {

    private List<Level> levels = new ArrayList<>();
    private int nextId = 1; // 다음 레벨 객체에 할당할 id 값
    private Scanner scanner = new Scanner(System.in);

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
        System.out.print("레벨 이름을 입력하세요: ");
        String name = scanner.nextLine();

        Level level = new Level(nextId++, name);
        levels.add(level);
        System.out.println("레벨이 등록되었습니다.");
    }

    private void listLevels() {
        System.out.println("==== 레벨 목록 ====");
        for (Level level : levels) {
            System.out.printf("ID: %d, 이름: %s\n", level.getId(), level.getName());
        }
    }

    private void viewLevel() {
        System.out.print("조회할 레벨 ID를 입력하세요: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        Level foundLevel = findLevelById(id);
        if (foundLevel != null) {
            System.out.printf("ID: %d, 이름: %s\n", foundLevel.getId(), foundLevel.getName());
        } else {
            System.out.println("해당 ID의 레벨이 존재하지 않습니다.");
        }
    }

    private void updateLevel() {
        System.out.print("변경할 레벨 ID를 입력하세요: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        Level foundLevel = findLevelById(id);
        if (foundLevel != null) {
            System.out.printf("현재 이름: %s\n", foundLevel.getName());
            System.out.print("새로운 이름을 입력하세요: ");
            String newName = scanner.nextLine();

            foundLevel.setName(newName);
            System.out.println("레벨 정보가 변경되었습니다.");
        } else {
            System.out.println("해당 ID의 레벨이 존재하지 않습니다.");
        }
    }

    private void deleteLevel() {
        System.out.print("삭제할 레벨 ID를 입력하세요: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        Level foundLevel = findLevelById(id);
        if (foundLevel != null) {
            levels.remove(foundLevel);
            System.out.println("레벨이 삭제되었습니다.");
        } else {
            System.out.println("해당 ID의 레벨이 존재하지 않습니다.");
        }
    }

    private Level findLevelById(int id) {
        for (Level level : levels) {
            if (level.getId() == id) {
                return level;
            }
        }
        return null;
    }
}
