package bitcamp.project2.vo;

import java.util.Date;
import java.util.Objects;

public class ToDo {

  private static int seqNo;

  private int no;
  private String title;
  private Date createdDate;
  private String memo;
  private String level;
  private String category;

  public ToDo() {

  }

  public ToDo(int no) {
    this.no = no;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ToDo toDo = (ToDo) o;
    return no == toDo.no;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(no);
  }

  public static int getNextSeqNo() {
    return ++seqNo;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}
