import com.whitepages.proapi.data.association.BusinessAssociation;
import com.whitepages.proapi.data.association.LocationAssociation;
import com.whitepages.proapi.data.association.PersonAssociation;
import com.whitepages.proapi.data.association.PhoneAssociation;

import com.whitepages.proapi.data.entity.Business;
import com.whitepages.proapi.data.entity.Entity;
import com.whitepages.proapi.data.entity.Location;
import com.whitepages.proapi.data.entity.Person;
import com.whitepages.proapi.data.entity.Phone;

import com.whitepages.proapi.data.util.TimePeriod;

import java.util.List;

public class ExampleUtils {
    private static final int _INDENT = 4;

    public static String getAPIKey( String[] args ) {
        if ( 1 != args.length ) {
            System.err.println( "Usage {class} {api_key}" );
            System.exit( 1 );
        }

        return args[ 0 ];
    }


    public static void dumpPhone( Phone phone, int depth ) {
        dumpPhone( phone, depth, 0 );
    }

    public static void dumpPerson( Person person, int depth ) {
        dumpPerson( person, depth, 0 );
    }

    public static void dumpLocation( Location location, int depth ) {
        dumpLocation( location, depth, 0 );
    }

    public static void dumpBusiness( Business business, int depth ) {
        dumpBusiness( business, depth, 0 );
    }

    private static void dumpPhone( Phone phone, int depth, int indent ) {
        printName( phone, indent );
        simpleLine( indent, "Carrier:                     %s", phone.getCarrier()            );
        simpleLine( indent, "Country Calling Code:        %s", phone.getCountryCallingCode() );
        simpleLine( indent, "Do Not Call:                 %s", phone.getDoNotCall()          );
        simpleLine( indent, "Extension:                   %s", phone.getExtension()          );
        simpleLine( indent, "LineType:                    %s", phone.getLineType()           );
        simpleLine( indent, "PhoneNumber:                 %s", phone.getPhoneNumber()        );
        simpleLine( indent, "Is Prepaid:                  %s", phone.getPrepaid()            );
        simpleLine( indent, "Reputation:                  %s", getSpamScore( phone )         );
        simpleLine( indent, "Is Valid:                    %s", phone.getValid()              );

        bestLocation( phone.getBestLocation(), depth, indent );

        dumpBaseEntity( phone, depth, indent );
    }

    private static void dumpPerson( Person person, int depth, int indent ) {
        printName( person, indent );

        // Could print out names list here

        simpleLine( indent, "Age Range:                   %s", person.getAgeRange()              );
        simpleLine( indent, "Gender:                      %s", person.getGender()                );
        simpleLine( indent, "Type:                        %s", person.getType()                  );

        bestLocation( person.getBestLocation(), depth, indent );

        dumpBaseEntity( person, depth, indent );
    }

    private static void dumpLocation( Location location, int depth, int indent ) {
        printName( location, indent );
        simpleLine( indent, "Address:                     %s", location.getAddress()                 );
        simpleLine( indent, "Address Type:                %s", location.getAddressType()             );
        simpleLine( indent, "Apartment Number:            %s", location.getAptNumber()               );
        simpleLine( indent, "Box Number:                  %s", location.getBoxNumber()               );
        simpleLine( indent, "City:                        %s", location.getCity()                    );
        simpleLine( indent, "CountryCode:                 %s", location.getCountryCode()             );
        simpleLine( indent, "Deliverable:                 %s", location.getDeliverable()             );
        simpleLine( indent, "Delivery Point:              %s", location.getDeliveryPoint()           );
        simpleLine( indent, "House:                       %s", location.getHouse()                   );
        simpleLine( indent, "Latitude/Longitude:          %s", stringify( location.getLatLong() )    );
        simpleLine( indent, "Not Receiving Mail Reason:   %s", location.getNotReceivingMailReason()  );
        simpleLine( indent, "Postal Code:                 %s", location.getPostalCode()              );
        simpleLine( indent, "Post Directional:            %s", location.getPostDir()                 );
        simpleLine( indent, "Pre Directional:             %s", location.getPreDir()                  );
        simpleLine( indent, "Receiving Mail:              %s", location.getReceivingMail()           );
        simpleLine( indent, "Standard Address Line1:      %s", location.getStandardAddressLine1()    );
        simpleLine( indent, "Standard Address Line2:      %s", location.getStandardAddressLine2()    );
        simpleLine( indent, "Standard Address Location:   %s", location.getStandardAddressLocation() );
        simpleLine( indent, "State Code:                  %s", location.getStateCode()               );
        simpleLine( indent, "Street Name:                 %s", location.getStreetName()              );
        simpleLine( indent, "Street Type:                 %s", location.getStreetType()              );
        simpleLine( indent, "Usage:                       %s", location.getUsage()                   );
        simpleLine( indent, "ValidFor:                    %s", stringify( location.getValidFor() )   );
        simpleLine( indent, "Zip4:                        %s", location.getZip4()                    );

        dumpBaseEntity( location, depth, indent );
    }

    private static void dumpBusiness( Business business, int depth, int indent ) {
        printName( business, indent );
        // @@@

        dumpBaseEntity( business, depth, indent );
    }


    private static void printName( Entity entity, int indent ) {
        simpleLine( indent, "Name:                        %s", entity.getName() );
    }

    private static void dumpBaseEntity( Entity entity, int depth, int indent ) {
        if ( 1 < depth ) {
            List< BusinessAssociation > businesses = entity.getBusinessAssociations();
            if ( null != businesses && !businesses.isEmpty() ) {
                simpleLine( indent, "Businesses:" );
                for ( BusinessAssociation business : businesses )
                    dumpBusinessAssociation( business, depth - 1, indent + _INDENT );
            }

            List< LocationAssociation > locations  = entity.getLocationAssociations();
            if ( null != locations && !locations.isEmpty() ) {
                simpleLine( indent, "Locations:" );
                for ( LocationAssociation location : locations )
                    dumpLocationAssociation( location, depth - 1, indent + _INDENT );
            }

            List< PersonAssociation > people       = entity.getPersonAssociations();
            if ( null != people && !people.isEmpty() ) {
                simpleLine( indent, "People:" );
                for ( PersonAssociation person : people )
                    dumpPersonAssociation( person, depth - 1, indent + _INDENT );
            }

            List< PhoneAssociation > phones        = entity.getPhoneAssociations();
            if ( null != phones && !phones.isEmpty() ) {
                simpleLine( indent, "Phones:" );
                for ( PhoneAssociation phone : phones )
                    dumpPhoneAssociation( phone, depth - 1, indent + _INDENT );
            }

        }
    }


    private static void dumpBusinessAssociation( BusinessAssociation association, int depth, int indent ) {
        dumpBusiness( association.getBusiness(), depth, indent );
    }

    private static void dumpLocationAssociation( LocationAssociation association, int depth, int indent ) {
        simpleLine( indent, "Contact Type:                %s", association.getContactType() );
        simpleLine( indent, "Location:" );
        dumpLocation( association.getLocation(), depth, indent + _INDENT );
    }

    private static void dumpPersonAssociation( PersonAssociation association, int depth, int indent ) {
        dumpPerson( association.getPerson(), depth, indent );
    }

    private static void dumpPhoneAssociation( PhoneAssociation association, int depth, int indent ) {
        simpleLine( indent, "Contact Type:                %s", association.getContactType() );
        simpleLine( indent, "Phone:" );
        dumpPhone( association.getPhone(), depth, indent + _INDENT );
        
    }

    private static void bestLocation( Location location, int depth, int indent ) {
        if ( depth > 1 && null != location )
            simpleLine( indent, "Best Location:               %s", location.getName() );
    }

    private static Integer getSpamScore( Phone phone ) {
        Phone.Reputation reputation = phone.getReputation();

        return null == reputation ? null : reputation.getSpamScore();
    }

    private static void simpleLine( int indent, String format, Object value ) {
        indent( indent );
        System.out.format( format, value );
        System.out.println();
    }

    private static void simpleLine( int indent, String format ) {
        simpleLine( indent, format, null );
    }

    private static void indent( int indent ) {
        for ( int i = 0; i < indent; i++ )
            System.out.print( " " );
    }

    private static String stringify( Location.LatLong latLong ) {
        if ( null == latLong )
            return "null";

        return String.format( "[Lat=%f; Long=%f; Accuracy=%s]",
                              latLong.getLatitude(),
                              latLong.getLongitude(),
                              latLong.getGeoAccuracy() );
    }

    private static String stringify( TimePeriod period ) {
        if ( null == period )
            return "null";

        return String.format( "[start=%s; stop=%s; current=%s; historical=%s]",
                              period.getStart(),
                              period.getStop(),
                              period.isCurrent(),
                              period.isHistorical() );
    }
}
