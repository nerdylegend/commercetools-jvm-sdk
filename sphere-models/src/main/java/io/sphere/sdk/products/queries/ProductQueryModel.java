package io.sphere.sdk.products.queries;

import io.sphere.sdk.products.Product;
import io.sphere.sdk.producttypes.ProductType;
import io.sphere.sdk.queries.DefaultModelQueryModelImpl;
import io.sphere.sdk.queries.QueryModel;
import io.sphere.sdk.queries.ReferenceQueryModel;

public class ProductQueryModel extends DefaultModelQueryModelImpl<Product> {

    public static ProductQueryModel of() {
        return new ProductQueryModel(null, null);
    }

    private ProductQueryModel(final QueryModel<Product> parent, final String pathSegment) {
        super(parent, pathSegment);
    }

    public ProductCatalogDataQueryModel<Product> masterData() {
        return new ProductCatalogDataQueryModel<>(this, "masterData");
    }

    public ReferenceQueryModel<Product, ProductType> productType() {
        return referenceModel("productType");
    }
}