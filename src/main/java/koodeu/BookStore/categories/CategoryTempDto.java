package koodeu.BookStore.categories;

import lombok.Getter;

@Getter
public class CategoryTempDto {
    private Long id;
    private String categoryName;
    private Long parentId;
    private Long depth;
    private static Long counter = 1L;

    public static CategoryTempDto fromLine(String line) {
        String[] split = line.split("\\w+");
        int depth = split.length == 0 ? 0 : split[0].length();
        CategoryTempDto categoryTempDto = new CategoryTempDto();
        categoryTempDto.depth = Long.valueOf(depth);
        categoryTempDto.categoryName = line.trim();
        categoryTempDto.id = counter++;
        return categoryTempDto;
    }

    public void applyParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Category toCategory() {

        return new Category(id, categoryName, parentId);
    }

}
