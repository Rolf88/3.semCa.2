package rest.exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NullPointerExceptionMapper implements ExceptionMapper<NullPointerException> {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(NullPointerException ex) {
        ErrorMessage em = new ErrorMessage(ex, 400);
        return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(em)).type(MediaType.APPLICATION_JSON).build();
    }
}