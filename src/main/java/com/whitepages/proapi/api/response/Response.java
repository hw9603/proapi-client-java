package com.whitepages.proapi.api.response;

import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.data.entity.Entity;
import com.whitepages.proapi.data.message.Message;

import java.util.List;

/**
 * <p>The Response is the root object for data returned from queries performed
 * via the {@link com.whitepages.proapi.api.client.Client} find methods.</p>
 *
 * <p>The key properties of a Response are:</p>
 * <ul>
 *     <li>The results (via {@link #getResults}): The list of the {@link Entity} instances found for the corresponding query,</li>
 *     <li>The response messages (via {@link #getResponseMessages}): A collection of {@link Message} instances pertaining to the response; these may include error conditions.</li>
 * </ul>
 *
 * <p>Response success can be determined via the {@link #isSuccess} and {@link #isFailure}
 * accessors. In the event of a failure response, the cause of the failure is
 * recorded in the response messages.</p>
 *
 * @see com.whitepages.proapi.api.client.Client
 */
public class Response<T extends Entity> {

    private final List<T> results;

    private final ResponseDictionary dictionary;

    private final ResponseMessages messages;

    private final Client client;

    private List<Message> errorMessages = null;

    public Response(Client client, List<T> results, ResponseDictionary dictionary, ResponseMessages messages) {
        this.client = client;
        this.results = results;
        this.dictionary = dictionary;
        this.messages = messages;
    }

    /**
     * Gets the list of resulting {@link Entity} instances found.
     * @return The list of results.
     */
    public List<T> getResults() {
        return results;
    }

    protected ResponseDictionary getResponseDictionary() {
        return dictionary;
    }

    /**
     * <p>Returns the collection of {@link Message} instances for this response.</p>
     *
     * <p>Messages include errors and warnings encountered during execution of the
     * query.</p>
     *
     * @return The response messages.
     * @see com.whitepages.proapi.api.response.ResponseMessages
     * @see com.whitepages.proapi.data.message.Message
     */
    public ResponseMessages getResponseMessages() {
        return messages;
    }

    /**
     * Returns the {@link Client} instance used to execute the query that generated this
     * response. This allows follow-up requests with the same configuration.
     *
     * @return The client used to execute this query.
     * @see com.whitepages.proapi.api.client.Client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Returns true if the query failed.
     * @return true if not successful, otherwise false
     * @see #isSuccess
     */
    public boolean isFailure() {
        return !isSuccess();
    }

    /**
     * Returns true if the query succeeded.
     * @return true if successful, otherwise false
     * @see #isFailure
     */
    public boolean isSuccess() {
        return getResponseMessages().getMessageList(Message.Severity.ERROR).isEmpty();
    }
}
