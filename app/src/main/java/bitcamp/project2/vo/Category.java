package bitcamp.project2.vo;

import java.util.Objects;

public class Category {

    private static int seqNo = 0;

    private int no;
    private String title;


    public Category() {

    }

    public Category(int no) {
        this.no = no;
    }

    public Category(int no, String title) {
        this.no = no;
        this.title = title;

    }

    public static int getNextSeqNo() {
        return ++seqNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        return no == category.no;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(no);
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

}
