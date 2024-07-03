package bitcamp.project2.vo;

import java.util.Objects;

public class Level {

    private static int nextSeqNo = 0;

    private int no;
    private String title;
    private String transactionType;

    public Level() {

    }

    public Level(int no) {
        this.no = no;
    }

    public Level(int no, String title) {
        this.no = no;
        this.title = title;
        this.transactionType = transactionType;
    }

    // Getters and setters
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public static int getNextSeqNo() {
        return nextSeqNo;
    }

    public static void setNextSeqNo(int nextSeqNo) {
        Level.nextSeqNo = nextSeqNo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Level level = (Level) obj;
        return no == level.no;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(no);
    }
}
