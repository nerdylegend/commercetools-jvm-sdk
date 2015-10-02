package io.sphere.sdk.reviews.commands;

import io.sphere.sdk.commands.UpdateAction;
import io.sphere.sdk.commands.UpdateCommandDsl;
import io.sphere.sdk.expansion.MetaModelExpansionDsl;
import io.sphere.sdk.models.Versioned;
import io.sphere.sdk.reviews.Review;
import io.sphere.sdk.reviews.expansion.ReviewExpansionModel;

import java.util.Collections;
import java.util.List;

/**
 {@doc.gen list actions}
 */
public interface ReviewUpdateCommand extends UpdateCommandDsl<Review, ReviewUpdateCommand>, MetaModelExpansionDsl<Review, ReviewUpdateCommand, ReviewExpansionModel<Review>> {
    static ReviewUpdateCommand of(final Versioned<Review> versioned, final UpdateAction<Review> updateAction) {
        return of(versioned, Collections.singletonList(updateAction));
    }

    static ReviewUpdateCommand of(final Versioned<Review> versioned, final List<? extends UpdateAction<Review>> updateActions) {
        return new ReviewUpdateCommandImpl(versioned, updateActions);
    }
}