package io.sphere.sdk.types.queries;

import io.sphere.sdk.queries.QueryModel;
import io.sphere.sdk.queries.QueryModelImpl;
import io.sphere.sdk.queries.ReferenceQueryModel;
import io.sphere.sdk.types.Type;

import javax.annotation.Nullable;

public class CustomQueryModelImpl<T> extends QueryModelImpl<T> implements CustomQueryModel<T> {
    public CustomQueryModelImpl(@Nullable final QueryModel<T> parent, @Nullable final String pathSegment) {
        super(parent, pathSegment);
    }

    @Override
    public ReferenceQueryModel<T, Type> type() {
        return referenceModel("type");
    }
}
