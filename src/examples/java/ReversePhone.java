import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.FindException;

import com.whitepages.proapi.api.query.PhoneQuery;

import com.whitepages.proapi.api.response.Response;

import com.whitepages.proapi.data.entity.Phone;

import java.util.List;


public class ReversePhone {
    private static final String _PHONE = "2069735100";

    public static void main( String[] args ) {
        String apiKey = ExampleUtils.getAPIKey( args );
        Client client = new Client( apiKey );
        PhoneQuery query = new PhoneQuery( _PHONE );

        Response<Phone> response = null;

        try {
            response = client.findPhones( query );
        } catch ( FindException e ) {
            System.err.format( "ReversePhone lookup for %s failed\n", _PHONE );
            e.printStackTrace();
        }


        if ( null != response && response.isSuccess() ) {
            List<Phone> results = response.getResults();

            System.out.format( "ReversePhone lookup for %s was successful, returning %d root phone objects\n\n",
                               _PHONE, results.size() );

            for ( Phone p : results ) {
                ExampleUtils.dumpPhone( p, 2 );
                System.out.println();
            }
        }
    }
}
