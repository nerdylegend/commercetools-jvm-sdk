package de.commercetools.sphere.client.shop.model;


import java.util.List;
import java.util.ArrayList;

import de.commercetools.sphere.client.model.*;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

/** Shopping cart that is stored persistently in the backend.
 *  Once the cart is created on the backend, it can be fetched by id
 *  and an instance of a PersistentCart is returned.
 *
 *  All the read methods of this class read from memory and all the
 *  methods making changes to this instance also persist the changes on the backend. */
@JsonIgnoreProperties(ignoreUnknown=true) // temp until this class fully matches the json returned by the backend
public class Cart {
    private String id;
    private int version;
    private List<LineItem> lineItems = new ArrayList<LineItem>();  // initialize to prevent NPEs
    private String consumerId;
    private DateTime lastModifiedAt;
    private DateTime createdAt;
    @JsonProperty("amountTotal")
    private Money totalPrice;
    private String shippingAddress;
    private String currencyCode;

    // for JSON deserializer
    private Cart() {}

    /** Unique id of this cart. */
    public String getId() {
        return id;
    }
    /** Version of this cart that increases when the cart is changed. */
    public int getVersion() {
        return version;
    }

    /** Returns the items in this cart. Does not make a query to the backend. */
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    /** Last modified timestamp of this cart. */
    public DateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    /** Creation timestamp of this cart. */
    public DateTime getCreatedAt() {
        return createdAt;
    }

    /** The shipping address of this cart. */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /** The currency of this cart. */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /** The customer id set to this cart. */
    public String getConsumerId() {
        return consumerId;
    }

    /** Number of line items. */
    public int getLineItemCount() {
        return this.getLineItems().size();
    }

    /** Sum of prices of line items. */
    public Money getTotalPrice() {
        return totalPrice;
    }

    /** Sum of quantities of line items. */
    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (LineItem lineItem: this.getLineItems()) {
            totalQuantity += lineItem.getQuantity();
        }
        return totalQuantity;
    }
}
