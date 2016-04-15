package com.whitepages.proapi.api.response;

import com.whitepages.proapi.data.message.Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Encapsulates a list of {@link Message} instances in order to provide methods
 * for common operations.
 *
 * @see com.whitepages.proapi.api.response.Response
 * @see com.whitepages.proapi.data.message.Message
 */
public class ResponseMessages {

    private final List<Message> messageList;

    public ResponseMessages(List<Message> messageList) {
        this.messageList = messageList;
    }

    /**
     * <p>Accessor for retrieving the list of messages.</p>
     *
     * <p>One should prefer using methods on the ResponseMessages object,
     * when available. For example, rather than calling
     * <p>* {@code response.getResponseMessages().getMessageList().get(0)},</p>
     * <p>* one should call</p>
     * <p>* {@code response.getResponseMessages().get(0)}.</p>
     *
     * @return The messages in this collection as a List.
     */
    public List<Message> getMessageList() {
        return messageList;
    }

    public int size() {
        return messageList.size();
    }

    public boolean isEmpty() {
        return messageList.isEmpty();
    }

    public Iterator<Message> iterator() {
        return messageList.iterator();
    }

    public ListIterator<Message> listIterator() {
        return messageList.listIterator();
    }

    public Message get(int index) {
        return messageList.get(index);
    }

    /**
     * <p>Gets the subset of messages of the given severity.</p>
     *
     * <p>This method is particularly useful for retrieving the list of error messages.
     * via {@code response.getResponseMessages().getMessageList(Message.Severity.ERROR)}</p>
     *
     * @param severity The severity for which to filter.
     * @return The list of all messages with the given severity.
     */
    public List<Message> getMessageList(Message.Severity severity) {
        List<Message> messages = new ArrayList<>();
        for( Message message : getMessageList() ) {
            if( (message.getSeverity() != null) && message.getSeverity() == severity )
                messages.add(message);
        }
        return messages;
    }
}
