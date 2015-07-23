package io.sphere.sdk.categories.queries;

import io.sphere.sdk.categories.Category;
import io.sphere.sdk.queries.*;

/**
 * {@doc.gen summary categories}
 */
public final class CategoryQueryModel extends DefaultModelQueryModelImpl<Category> {

    public static CategoryQueryModel of() {
        return new CategoryQueryModel(null, null);
    }

    private CategoryQueryModel(QueryModel<Category> parent, String pathSegment) {
        super(parent, pathSegment);
    }

    public LocalizedStringsQuerySortingModel<Category> slug() {
        return LocalizedStringsQuerySortingModel.of(this, "slug");
    }

    public LocalizedStringsQuerySortingModel<Category> name() {
        return LocalizedStringsQuerySortingModel.of(this, "name");
    }

    public StringQuerySortingModel<Category> externalId() {
        return stringModel("externalId");
    }

    public ReferenceOptionalQueryModel<Category, Category> parent() {
        return referenceOptionalModel("parent");
    }
}
