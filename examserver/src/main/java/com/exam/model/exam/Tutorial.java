package com.exam.model.exam;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity()
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long tutId;
    private String tutTitle;


    @NotNull
    @Column(length = 100000)
    @Lob
    private String tutContent;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;


    public  Tutorial(){

    }
    public Tutorial(int tutId, String tutTitle, String tutContent, Category category) {
        this.tutId = tutId;
        this.tutTitle = tutTitle;
        this.tutContent = tutContent;
        this.category = category;
    }

    public long getTutId() {
        return tutId;
    }

    public void setTutId(long tutId) {
        this.tutId = tutId;
    }

    public String getTutTitle() {
        return tutTitle;
    }

    public void setTutTitle(String tutTitle) {
        this.tutTitle = tutTitle;
    }

    public String  getTutContent() {
        return tutContent;
    }

    public void setTutContent(String tutContent) {
        this.tutContent = tutContent;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
