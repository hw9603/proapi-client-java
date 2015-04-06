import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.FindException;

import com.whitepages.proapi.api.query.LocationQuery;

import com.whitepages.proapi.api.response.Response;

import com.whitepages.proapi.data.entity.Location;

import java.util.List;


public class ReverseAddress {
    private static final String
        _STREET_LINE1 = "906 Dexter Ave N Apt l328",
        _STREET_LINE2 = null,
        _CITY         = "Seattle",
        _STATE_CODE   = "WA",
        _POSTAL_CODE  = null;


    public static void main( String[] args ) {
        String apiKey = ExampleUtils.getAPIKey( args );
        Client client = new Client( apiKey );
        LocationQuery query = new LocationQuery( _STREET_LINE1, _STREET_LINE2, _CITY, _STATE_CODE, _POSTAL_CODE );

        Response<Location> response = null;

        try {
            response = client.findLocations( query );
        } catch ( FindException e ) {
            System.err.format( "ReverseAddress lookup for %s; %s; %s; %s; %s failed\n",
                               _STREET_LINE1, _STREET_LINE2, _CITY, _STATE_CODE, _POSTAL_CODE );
            e.printStackTrace();
        }


        if ( null != response && response.isSuccess() ) {
            List<Location> results = response.getResults();

            System.out.format( "ReverseAddress lookup for %s; %s; %s; %s; %s was successful, returning %d root location objects\n\n",
                               _STREET_LINE1, _STREET_LINE2, _CITY, _STATE_CODE, _POSTAL_CODE, results.size() );

            for ( Location location : results ) {
                ExampleUtils.dumpLocation( location, 2 );
                System.out.println();
            }
        }
    }
}
