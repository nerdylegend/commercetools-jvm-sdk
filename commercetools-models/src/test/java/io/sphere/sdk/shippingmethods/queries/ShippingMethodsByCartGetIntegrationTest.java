package io.sphere.sdk.shippingmethods.queries;

import io.sphere.sdk.carts.Cart;
import io.sphere.sdk.carts.commands.CartUpdateCommand;
import io.sphere.sdk.carts.commands.updateactions.SetShippingAddress;
import io.sphere.sdk.client.SphereRequest;
import io.sphere.sdk.shippingmethods.ShippingMethod;
import io.sphere.sdk.shippingmethods.ShippingRate;
import io.sphere.sdk.test.IntegrationTest;
import io.sphere.sdk.test.utils.VrapRequestDecorator;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.sphere.sdk.carts.CartFixtures.GERMAN_ADDRESS;
import static io.sphere.sdk.carts.CartFixtures.withCart;
import static io.sphere.sdk.shippingmethods.ShippingMethodFixtures.withShippingMethodForGermany;
import static org.assertj.core.api.Assertions.assertThat;


public class ShippingMethodsByCartGetIntegrationTest extends IntegrationTest {
    @Test
    public void execution() throws Exception {
        withShippingMethodForGermany(client(), shippingMethod -> {
            withCart(client(), cart -> {
                final Cart cartWithShippingAddress = client().executeBlocking(CartUpdateCommand.of(cart, SetShippingAddress.of(GERMAN_ADDRESS)));

                final SphereRequest<List<ShippingMethod>> sphereRequest =
                        new VrapRequestDecorator<>(ShippingMethodsByCartGet.of(cartWithShippingAddress), "response");

                final List<ShippingMethod> shippingMethods =
                        client().executeBlocking(sphereRequest);
                assertThat(shippingMethods).isNotEmpty();

                for (final ShippingMethod cartShippingMethod : shippingMethods) {
                    final List<ShippingRate> shippingRates = cartShippingMethod.getZoneRates().stream()
                            .flatMap(zoneRate -> zoneRate.getShippingRates().stream())
                            .collect(Collectors.toList());

                    assertThat(shippingRates).areExactly(1, new Condition<>(ShippingRate::isMatching, "Shipping rate is matching"));
                }
                return cartWithShippingAddress;
            });
        });
    }
}