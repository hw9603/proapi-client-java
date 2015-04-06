import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.FindException;

import com.whitepages.proapi.api.query.BusinessQuery;

import com.whitepages.proapi.api.response.Response;

import com.whitepages.proapi.data.entity.Business;

import java.util.List;


public class FindBusiness {
    private static final String
        _NAME        = "Toyota",
        _CITY        = "Seattle",
        _STATE_CODE  = "WA",
        _POSTAL_CODE = null;


    public static void main( String[] args ) {
        String apiKey = ExampleUtils.getAPIKey( args );
        Client client = new Client( apiKey );
        BusinessQuery query = new BusinessQuery( _NAME, _CITY, _STATE_CODE, _POSTAL_CODE );

        Response<Business> response = null;

        try {
            response = client.findBusinesses( query );
        } catch ( FindException e ) {
            System.err.format( "FindBusiness lookup for %s; %s; %s; %s failed\n",
                               _NAME, _CITY, _STATE_CODE, _POSTAL_CODE );
            e.printStackTrace();
        }


        if ( null != response && response.isSuccess() ) {
            List<Business> results = response.getResults();

            System.out.format( "FindBusiness lookup for %s; %s; %s; %s was successful, returning %d root business objects\n\n",
                               _NAME, _CITY, _STATE_CODE, _POSTAL_CODE, results.size() );

            for ( Business business : results ) {
                ExampleUtils.dumpBusiness( business, 2 );
                System.out.println();
            }
        }
    }
}
