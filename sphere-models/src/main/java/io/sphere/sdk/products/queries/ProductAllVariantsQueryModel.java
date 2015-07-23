package io.sphere.sdk.products.queries;

import io.sphere.sdk.queries.QueryModel;
import io.sphere.sdk.queries.QueryModelImpl;
import io.sphere.sdk.queries.QueryPredicate;

import java.util.Optional;
import java.util.function.Function;

public final class ProductAllVariantsQueryModel<T> extends QueryModelImpl<T> {

    ProductAllVariantsQueryModel(final QueryModel<T> parent) {
        super(parent, null);
    }

    private QueryPredicate<T> where(final QueryPredicate<PartialProductVariantQueryModel> embeddedPredicate) {
        final ProductDataQueryModelBase<T> parent = Optional.ofNullable((ProductDataQueryModelBase<T>) getParent())
                .orElseThrow(() -> new UnsupportedOperationException("A proper parent model is required."));
        return parent.where(m -> m.masterVariant().where(embeddedPredicate).or(m.variants().where(embeddedPredicate)));
    }

    public QueryPredicate<T> where(final Function<PartialProductVariantQueryModel, QueryPredicate<PartialProductVariantQueryModel>> embeddedPredicate) {
        final PartialProductVariantQueryModel m = PartialProductVariantQueryModel.of();
        return where(embeddedPredicate.apply(m));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
