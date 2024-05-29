package Packate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Flight(String lastUpdatedAt,String actualLandingTime){

}
