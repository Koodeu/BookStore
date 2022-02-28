import com.google.common.io.Resources;
import koodeu.BookStore.categories.Category;
import koodeu.BookStore.categories.CategoryTempDto;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategoryDao {

    private List<Category> categoryList;

    private static CategoryDao INSTANCE;

    private CategoryDao() {
    }

    static CategoryDao getInstance() {
        if (INSTANCE == null) {
            synchronized (CategoryDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CategoryDao();
                }
            }
        }
        return INSTANCE;
    }

    public static List<String> readCategories() {
        URL url = CategoryDao.class.getClassLoader()
                .getResource("kategorie.txt");
        try {
            return Resources.readLines(url, Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category> createCategories() {
        List<String> lines = readCategories();
        List<CategoryTempDto> tempDtos = lines.stream()
                .map(line -> CategoryTempDto.fromLine(line))
                .collect(Collectors.toList());
        Map<Long, List<CategoryTempDto>> categoryMap = tempDtos.stream()
                .collect(Collectors.groupingBy(x -> x.getDepth()));
        lookForParent(1L, categoryMap);
        return categoryMap.values().stream()
                .flatMap(list -> list.stream())
                .map(dto -> dto.toCategory())
                .collect(Collectors.toList());
    }

    private void lookForParent(Long depth, Map<Long, List<CategoryTempDto>> categoryMap) {
        if (!categoryMap.containsKey(depth)) {
            return;
        }
        List<CategoryTempDto> children = categoryMap.get(depth);
        for (CategoryTempDto child : children) {
            Long parentId = findParentId(categoryMap.get(depth - 1), child.getId());
            child.applyParentId(parentId);
        }
        lookForParent(depth + 1, categoryMap);
    }

    private static Long findParentId(List<CategoryTempDto> potentialParents, Long childId) {
        return potentialParents.stream()
                .map(parent -> parent.getId())
                .filter(id -> id < childId)
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .orElse(null);
    }


}
