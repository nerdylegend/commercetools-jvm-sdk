package io.sphere.sdk.client;

import java.util.List;

import static io.sphere.sdk.client.ClientPackage.*;

/**
 * Contains the configuration to fetch access keys for the commercetools platform.
 */
public interface SphereAuthConfig {
    /**
     * The url of the OAuth server including http protocol and ports.
     * @return url
     */
    String getAuthUrl();

    String getClientId();

    String getClientSecret();

    String getProjectKey();

    /**
     * Gets the scopes which are permitted.
     *
     * {@include.example io.sphere.sdk.client.SphereAuthConfigBuilderTest#scopes()}
     *
     * @return scopes
     */
    default List<String> getScopes() {
        return DEFAULT_SCOPES;
    }

    static SphereAuthConfig of(final String projectKey, final String clientId, final String clientSecret) {
        return of(projectKey, clientId, clientSecret, AUTH_URL);
    }

    static SphereAuthConfig of(final String projectKey, final String clientId, final String clientSecret, final String authUrl) {
        return new SphereAuthConfigImpl(projectKey, clientId, clientSecret, authUrl, DEFAULT_SCOPES);
    }
}
