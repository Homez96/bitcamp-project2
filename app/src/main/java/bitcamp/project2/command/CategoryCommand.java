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
    category.setTransactionType(Prompt.input("수입/지출(%s)?", category.getTransactionType()));
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
    System.out.printf("수입/지출 : %s\n", category.getTransactionType());
  }

  private void listCategory() {
    System.out.println("번호\t카테고리명\t수입/지출");
    for (Object obj : categoryList.toArray()) {
      Category category = (Category) obj;
      System.out.printf("%d\t\t%s\t\t%s\n",
              category.getNo(), category.getTitle(), category.getTransactionType());
    }
  }

  private void addCategory() {
    Category category = new Category();
    category.setTitle(Prompt.input("카테고리명?"));
    category.setNo(Category.getNextSeqNo());
    category.setTransactionType(Prompt.input("수입/지출?"));

    System.out.println("Adding category: " + category); // 디버깅 출력
    categoryList.add(category);

    // 디버깅을 위한 리스트 출력
    for (Object obj : categoryList.toArray()) {
      Category c = (Category) obj;
      System.out.printf("카테고리: %d, %s, %s\n", c.getNo(), c.getTitle(), c.getTransactionType());
    }
  }

  public LinkedList getIncomeCategoryList() {
    LinkedList incomeCategoryList = new LinkedList();

    for (Object obj : categoryList.toArray()) {
      Category category = (Category) obj;
      if (category.getTransactionType().equals("수입")) {
        incomeCategoryList.add(category);
      }
    }
    return incomeCategoryList;
  }

  public LinkedList getExpenseCategoryList() {
    LinkedList expenseCategoryList = new LinkedList();

    for (Object obj : categoryList.toArray()) {
      Category category = (Category) obj;
      if (category.getTransactionType().equals("지출")) {
        expenseCategoryList.add(category);
      }
    }
    return expenseCategoryList;
  }

  public LinkedList getCategoryList() {
    return this.categoryList;
  }
}
