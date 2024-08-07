package bitcamp.project2.command;

import bitcamp.project2.util.Highlight;
import bitcamp.project2.util.LinkedList;
import bitcamp.project2.util.Prompt;
import bitcamp.project2.vo.Category;

public class CategoryCommand {

  LinkedList categoryList = new LinkedList();

  public void executeCategoryCommand(String command) {
    Highlight.menuHighlight(command, "blue");
    switch (command) {
      case "등록":
        this.addCategory();
        break;
      case "조회":
        this.viewCategory();
        break;
      case "목록":
        this.listCategory();
        break;
      case "변경":
        this.updateCategory();
        break;
      case "삭제":
        this.deleteCategory();
        break;
    }
  }

  private void deleteCategory() {
    int categoryNo = Prompt.inputInt("카테고리 번호?");
    Category deletedCategory = (Category) categoryList.get(categoryList.indexOf(new Category(categoryNo)));
    if (deletedCategory != null) {
      categoryList.remove(categoryList.indexOf(deletedCategory));
      System.out.printf("%d번 카테고리을 삭제 했습니다.\n", deletedCategory.getNo());
    } else {
      System.out.println("없는 카테고리입니다.");
    }
  }

  private void updateCategory() {
    int categoryNo = Prompt.inputInt("카테고리 번호?");
    Category category = (Category) categoryList.get(categoryList.indexOf(new Category(categoryNo)));
    if (category == null) {
      System.out.println("없는 카테고리입니다.");
      return;
    }

    category.setTitle(Prompt.input("카테고리명(%s)?", category.getTitle()));
    System.out.println("변경 했습니다.");
  }

  private void viewCategory() {
    int categoryNo = Prompt.inputInt("카테고리 번호?");
    Category category = (Category) categoryList.get(categoryList.indexOf(new Category(categoryNo)));
    if (category == null) {
      System.out.println("없는 카테고리입니다.");
      return;
    }

    System.out.printf("카테고리명 : %s\n", category.getTitle());
  }

  private void listCategory() {
    System.out.println("번호\t카테고리명");
    for (Object obj : categoryList.toArray()) {
      Category category = (Category) obj;
      System.out.printf("%d\t\t%s\n",
              category.getNo(), category.getTitle());
    }
  }

  private void addCategory() {
    Category category = new Category();
    category.setTitle(Prompt.input("카테고리명?"));
    category.setNo(Category.getNextSeqNo());


    categoryList.add(category);

  }

  public LinkedList getCategoryList() {
    return this.categoryList;
  }
}
