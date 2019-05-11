package rbxapi.javablox.api.accountsettings;

import rbxapi.javablox.model.accountsettings.PasswordEnabled;
import rbxapi.javablox.model.common.serial.Boolean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TwoStepVerification {

    @GET("v2/twostepverification")
    Call<Boolean> is2FAEnabled();

    @POST("v2/twostepverification")
    Call set2FAEnable(@Body PasswordEnabled requestBody);
}
