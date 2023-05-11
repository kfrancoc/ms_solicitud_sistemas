package pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.payload;


import lombok.Data;

@Data
public class JwAuthenticationResponse {

    private String accessToken;
    private String tokenType = "Bearer";

    public JwAuthenticationResponse(String accessToken){
    this.accessToken = accessToken;

    }

}
