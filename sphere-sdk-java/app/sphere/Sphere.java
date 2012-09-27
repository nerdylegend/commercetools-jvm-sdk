package sphere;

import de.commercetools.internal.*;
import de.commercetools.internal.oauth.ShopClientCredentials;
import de.commercetools.sphere.client.*;
import de.commercetools.sphere.client.shop.*;
import de.commercetools.sphere.client.util.*;
import de.commercetools.sphere.client.oauth.OAuthClient;
import de.commercetools.sphere.client.oauth.ClientCredentials;
import de.commercetools.sphere.client.model.SearchResult;

import com.ning.http.client.AsyncHttpClient;
import org.codehaus.jackson.type.TypeReference;

import java.util.concurrent.TimeUnit;

/**
 * Provides default configured and initialized instance of {@link ShopClient}.
 * The instance is designed to be shared by all controllers in your application.
 */
public class Sphere {
    /**
     * This is a static class.
     */
    private Sphere() {
    }

    private final static SphereClient sphereClient = createSphereClient();

    /**
     * Returns a thread-safe client for accessing the Sphere APIs.
     */
    public static SphereClient getSphereClient() {
        return sphereClient;
    }

    private static SphereClient createSphereClient() {
        try {
            final AsyncHttpClient httpClient = new AsyncHttpClient();
            ShopClientConfig config = Config.root().shopClientConfig();
            ShopClientCredentials clientCredentials = ShopClientCredentials.create(config, new OAuthClient(httpClient));
            RequestFactory requestFactory = new RequestFactoryImpl(httpClient, clientCredentials);
            ProjectEndpoints projectEndpoints =
                    Endpoints.forProject(config.getCoreHttpServiceUrl(), config.getProjectKey());
            return new SphereClient(
                    Config.root(),
                    new ShopClient(
                            config,
                            new ProductsImpl(requestFactory, projectEndpoints),
                            new CategoriesImpl(requestFactory, projectEndpoints),
                            new CartsImpl(requestFactory, projectEndpoints),
                            new OrdersImpl(requestFactory, projectEndpoints)
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
