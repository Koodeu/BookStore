package koodeu.BookStore.categories;

public class Category {

    private Long id;
    private Long parentId;
    private String categoryName;

    public Category(Long id, String categoryName, Long parentId) {
        this.id = id;
        this.parentId = parentId;
        this.categoryName = categoryName;
    }
}
