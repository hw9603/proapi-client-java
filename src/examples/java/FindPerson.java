import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.FindException;

import com.whitepages.proapi.api.query.PersonQuery;

import com.whitepages.proapi.api.response.Response;

import com.whitepages.proapi.data.entity.Person;

import java.util.List;


public class FindPerson {
    private static final String
        _FNAME       = "Jane",
        _MNAME       = null,
        _LNAME       = "Smith",
        _CITY        = "Seattle",
        _STATE_CODE  = "WA",
        _POSTAL_CODE = null;


    public static void main( String[] args ) {
        String apiKey = ExampleUtils.getAPIKey( args );
        Client client = new Client( apiKey );
        PersonQuery query = new PersonQuery( _FNAME, _MNAME, _LNAME, _CITY, _STATE_CODE, _POSTAL_CODE );

        Response<Person> response = null;

        try {
            response = client.findPeople( query );
        } catch ( FindException e ) {
            System.err.format( "FindPerson lookup for %s; %s; %s; %s; %s; %s failed\n",
                               _FNAME, _MNAME, _LNAME, _CITY, _STATE_CODE, _POSTAL_CODE );
            e.printStackTrace();
        }


        if ( null != response && response.isSuccess() ) {
            List<Person> results = response.getResults();

            System.out.format( "FindPerson lookup for %s; %s; %s; %s; %s; %s was successful, returning %d root people objects\n\n",
                               _FNAME, _MNAME, _LNAME, _CITY, _STATE_CODE, _POSTAL_CODE, results.size() );

            for ( Person person : results ) {
                ExampleUtils.dumpPerson( person, 2 );
                System.out.println();
            }
        }
    }
}
