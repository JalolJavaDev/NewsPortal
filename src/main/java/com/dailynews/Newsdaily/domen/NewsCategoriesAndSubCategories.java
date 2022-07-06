package com.dailynews.Newsdaily.domen;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class NewsCategoriesAndSubCategories implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId; // SHU YERNI QARAB QO'Y
    private String categoryName;
    private Date creationDTM;
    private Date updationDTM;
    private boolean isActive;

    @OneToMany(mappedBy = "newsCategoriesAndSubCategories")
    @JsonIgnore
    private List<News> newsList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private NewsCategoriesAndSubCategories parentId;


    public NewsCategoriesAndSubCategories() {

    }
    public NewsCategoriesAndSubCategories(String categoryName, Date creationDTM, Date updationDTM, boolean isActive) {
        this.categoryName = categoryName;
        this.creationDTM = creationDTM;
        this.updationDTM = updationDTM;
        this.isActive = isActive;
    }

    public NewsCategoriesAndSubCategories(String categoryName, Date creationDTM, Date updationDTM, boolean isActive, List<News> newsList) {
        this.categoryName = categoryName;
        this.creationDTM = creationDTM;
        this.updationDTM = updationDTM;
        this.isActive = isActive;
        this.newsList = newsList;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getCreationDTM() {
        return creationDTM;
    }

    public void setCreationDTM(Date creationDTM) {
        this.creationDTM = creationDTM;
    }

    public Date getUpdationDTM() {
        return updationDTM;
    }

    public void setUpdationDTM(Date updationDTM) {
        this.updationDTM = updationDTM;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public NewsCategoriesAndSubCategories getParentId() {
        return parentId;
    }

    public void setParentId(NewsCategoriesAndSubCategories parentId) {
        this.parentId = parentId;
    }
}