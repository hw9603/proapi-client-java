package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.whitepages.proapi.api.client.ResponseDecoderException;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.EntityId;
import com.whitepages.proapi.data.entity.Phone;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhoneProAPI20JSONStreamResponseDecoderTest {

    static Response<Phone> response;
    static Phone phone;

    @BeforeClass
    public static void setUp() throws ResponseDecoderException {
        response = new ResponseDecoderHelper().getPhoneResponse();
        phone = response.getResults().get(0);
    }

    @Test
    public void hasResults() {
        assertThat(response.getResults().size(), is(1));
    }

    @Test
    public void hasNumber() {
        assertThat(phone.getPhoneNumber(), is("8003361327"));
    }

    @Test
    public void hasLocationAssociations() {
        assertThat(phone.getLocationAssociations().size(), is(1));
    }

    @Test
    public void hasLocations() {
        assertThat(phone.getLocations().get(0).getId(), is(EntityId.fromString("Location.0a48926c-b02c-468e-ba80-18cc77dfa3fc.Durable")));
    }

    @Test
    public void hasNoPeopleAssociations() {
        assertThat(phone.getPersonAssociations(), is(nullValue()));
    }

    @Test
    public void hasNoPeople() {
        assertThat(phone.getPeople(), is(nullValue()));
    }

    @Test
    public void hasBusinessAssociations() {
        assertThat(phone.getBusinessAssociations().size(), is(1));
    }

    @Test
    public void hasBusinesses() {
        assertThat(phone.getBusinesses().get(0).getId(), is(EntityId.fromString("Business.545ac847-02be-4f1c-8139-9e7b97b18003.Durable")));
    }

    @Test
    public void hasNoConnected() {
    	assertThat(phone.getConnected(), is(nullValue()));
    }
    
    @Test
    public void hasReputationLevel() {
    	assertThat(phone.getReputation().getLevel(), is(not(nullValue())));
    }
    
    @Test
    public void hasReputationDetails() {
    	assertThat(phone.getReputation().getDetails().size(), is(1));
    	assertThat((phone.getReputation().getDetails()).get(0).getScore(), is(2));
    }
    
    @Test
    public void hasNoPhoneAssociations() {
        assertThat(phone.getPhoneAssociations(), is(nullValue()));
    }

    @Test
    public void hasLineType() {
        assertThat(phone.getLineType(), is(Phone.LineType.TOLL_FREE));
    }

    @Test
    public void hasDoNotCall() {
        assertThat(phone.getDoNotCall(), is(false));
    }

    @Test
    public void hasSpamScore() {
        assertThat(phone.getReputation().getSpamScore(), is(0));
    }
}
