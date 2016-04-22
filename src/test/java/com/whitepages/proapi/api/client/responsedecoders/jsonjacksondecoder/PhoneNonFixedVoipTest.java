package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.whitepages.proapi.api.client.ResponseDecoderException;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.EntityId;
import com.whitepages.proapi.data.entity.Phone;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhoneNonFixedVoipTest {

    static Response<Phone> response;
    static Phone phone;

    @BeforeClass
    public static void setUp() throws ResponseDecoderException {
        response = new ResponseDecoderHelper().getPhoneNonFixedVoipResponse();
        phone = response.getResults().get(0);
    }

    @Test
    public void hasResults() {
        assertThat(response.getResults().size(), is(1));
    }

    @Test
    public void hasNumber() {
        assertThat(phone.getPhoneNumber(), is("3213524103"));
    }

    @Test
    public void hasLineType() {
        assertThat(phone.getLineType(), is(Phone.LineType.NON_FIXED_VOIP));
    }
}
